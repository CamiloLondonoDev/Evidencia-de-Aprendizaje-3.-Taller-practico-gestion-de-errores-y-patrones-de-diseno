package com.ecommerce;

public class Producto {
    private int id;
    private String nombre;
    private double precio;
    private int stock;

    public Producto(int id, String nombre, double precio, int stock) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public double getPrecio() { return precio; }
    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

    @Override
    public String toString() {
        return id + " - " + nombre + " ($" + precio + ") Stock: " + stock;
    }
}

abstract class ProductoFactory {
    public abstract Producto crearProducto(String nombre, double precio, int stock);
}

class ProductoConcretoFactory extends ProductoFactory {
    private static int contador = 1;

    @Override
    public Producto crearProducto(String nombre, double precio, int stock) {
        return new Producto(contador++, nombre, precio, stock);
    }
}
