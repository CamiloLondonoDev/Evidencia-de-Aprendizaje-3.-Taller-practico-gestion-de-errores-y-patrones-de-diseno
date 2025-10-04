package com.ecommerce;

import java.util.List;

public class Pedido {
    private int id;
    private Cliente cliente;
    private List<Producto> productos;
    private double total;

    public Pedido(int id, Cliente cliente, List<Producto> productos) {
        this.id = id;
        this.cliente = cliente;
        this.productos = productos;
        this.total = calcularTotal();
    }

    private double calcularTotal() {
        return productos.stream().mapToDouble(Producto::getPrecio).sum();
    }

    public double getTotal() { return total; }
    public Cliente getCliente() { return cliente; }
    public List<Producto> getProductos() { return productos; }

    @Override
    public String toString() {
        return "Pedido #" + id + " Cliente: " + cliente.getNombre() + " Total: $" + total;
    }
}
