package com.ecommerce;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SistemaEcommerce {
    private static SistemaEcommerce instancia;
    private List<Cliente> clientes;
    private List<Producto> productos;
    private List<Pedido> pedidos;

    private SistemaEcommerce() {
        clientes = new ArrayList<>();
        productos = new ArrayList<>();
        pedidos = new ArrayList<>();
    }

    public static SistemaEcommerce getInstancia() {
        if (instancia == null) {
            instancia = new SistemaEcommerce();
        }
        return instancia;
    }

    public void agregarCliente(Cliente c) { clientes.add(c); }
    public List<Cliente> getClientes() { return clientes; }

    public void agregarProducto(Producto p) { productos.add(p); }
    public List<Producto> getProductos() { return productos; }

    public void agregarPedido(Pedido p) { pedidos.add(p); }
    public List<Pedido> getPedidos() { return pedidos; }

    public List<Producto> filtrarProductosPorPrecio(double minPrecio) {
        return productos.stream().filter(p -> p.getPrecio() >= minPrecio).collect(Collectors.toList());
    }

    public void listarProductos() {
        productos.forEach(System.out::println);
    }
}
