package com.ecommerce;

public class Usuario {
    protected int id;
    protected String nombre;
    protected String email;

    public Usuario(int id, String nombre, String email) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getEmail() { return email; }
}

class Cliente extends Usuario {
    private String direccion;

    public Cliente(int id, String nombre, String email, String direccion) {
        super(id, nombre, email);
        this.direccion = direccion;
    }

    public String getDireccion() { return direccion; }
}
