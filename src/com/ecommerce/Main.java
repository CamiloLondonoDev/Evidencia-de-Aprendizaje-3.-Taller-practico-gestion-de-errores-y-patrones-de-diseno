package com.ecommerce;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static SistemaEcommerce sistema = SistemaEcommerce.getInstancia();
    private static ProductoFactory factory = new ProductoConcretoFactory();
    private static Scanner sc = new Scanner(System.in);
    private static int clienteId = 1;
    private static int pedidoId = 1;

    public static void main(String[] args) {
        boolean salir = false;

        while (!salir) {
            mostrarMenu();
            String opcion = sc.nextLine();

            switch (opcion) {
                case "1": agregarCliente(); break;
                case "2": agregarProducto(); break;
                case "3": crearPedido(); break;
                case "4": listarProductosCaros(); break;
                case "5": salir = true; break;
                default: System.out.println("Opción inválida. Intente de nuevo."); break;
            }
        }
    }

    private static void mostrarMenu() {
        System.out.println("\n--- E-COMMERCE MENU ---");
        System.out.println("1. Agregar Cliente");
        System.out.println("2. Agregar Producto");
        System.out.println("3. Crear Pedido");
        System.out.println("4. Listar productos > $100");
        System.out.println("5. Salir");
        System.out.print("Seleccione opción: ");
    }

    // ✅ Método actualizado con try-catch-finally
    private static void agregarCliente() {
        System.out.println("\n=== Alta de Cliente ===");
        try {
            System.out.print("Nombre del cliente: ");
            String nombre = sc.nextLine();
            System.out.print("Email: ");
            String email = sc.nextLine();
            System.out.print("Dirección: ");
            String direccion = sc.nextLine();

            Cliente cliente = new Cliente(clienteId++, nombre, email, direccion);
            sistema.agregarCliente(cliente);
            System.out.println("✔ Cliente agregado correctamente.\n");
        } catch (NumberFormatException e) {
            System.out.println("❌ Error: Debe ingresar un número válido");
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Datos inválidos: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("❌ Ocurrió un error al agregar el cliente: " + e.getMessage());
        } finally {
            System.out.println("[FIN] Operación de alta de cliente finalizada.");
            System.out.println("---------------------------------------------\n");
        }
    }

    private static void agregarProducto() {
        try {
            System.out.print("Nombre del producto: ");
            String nombre = sc.nextLine();
            System.out.print("Precio: ");
            double precio = Double.parseDouble(sc.nextLine());
            if (precio <= 0) throw new IllegalArgumentException("Precio debe ser > 0");
            System.out.print("Stock: ");
            int stock = Integer.parseInt(sc.nextLine());
            if (stock < 0) throw new IllegalArgumentException("Stock no puede ser negativo");

            Producto producto = factory.crearProducto(nombre, precio, stock);
            sistema.agregarProducto(producto);
            System.out.println("Producto agregado correctamente.");
        } catch (NumberFormatException e) {
            System.out.println("Error: Debe ingresar un número válido");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void crearPedido() {
        try {
            if (sistema.getClientes().isEmpty() || sistema.getProductos().isEmpty()) {
                System.out.println("Se necesitan al menos un cliente y un producto para crear un pedido.");
                return;
            }

            System.out.println("Seleccione cliente:");
            sistema.getClientes().forEach(c -> System.out.println(c.getId() + ". " + c.getNombre()));
            int clienteSel = Integer.parseInt(sc.nextLine());
            Cliente cliente = sistema.getClientes().stream()
                    .filter(c -> c.getId() == clienteSel)
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado"));

            List<Producto> productosPedido = new ArrayList<>();
            boolean agregando = true;
            while (agregando) {
                sistema.listarProductos();
                System.out.print("ID producto a agregar (0 para terminar): ");
                int prodId = Integer.parseInt(sc.nextLine());
                if (prodId == 0) break;

                Producto prod = sistema.getProductos().stream()
                        .filter(p -> p.getId() == prodId)
                        .findFirst()
                        .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));
                productosPedido.add(prod);
            }

            if (productosPedido.isEmpty()) {
                System.out.println("Pedido cancelado: no se agregaron productos.");
                return;
            }

            Pedido pedido = new Pedido(pedidoId++, cliente, productosPedido);
            sistema.agregarPedido(pedido);
            System.out.println("Pedido creado exitosamente: Total $" + pedido.getTotal());
        } catch (NumberFormatException e) {
            System.out.println("Error: Debe ingresar un número válido");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void listarProductosCaros() {
        System.out.println("Productos con precio > $100:");
        sistema.filtrarProductosPorPrecio(100).forEach(System.out::println);
    }
}
