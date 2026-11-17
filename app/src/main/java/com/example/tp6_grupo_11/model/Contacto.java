package com.example.tp6_grupo_11.model;

import java.io.Serializable;

public class Contacto implements Serializable {
    private String nombre;
    private String apellido;
    private String telefono;
    private String tipoTelefono;
    private String email;
    private String tipoEmail;
    private String direccion;
    private String fechaNacimiento;

    public Contacto(String nombre, String apellido, String telefono, String tipoTelefono,
                    String email, String tipoEmail, String direccion, String fechaNacimiento) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.tipoTelefono = tipoTelefono;
        this.email = email;
        this.tipoEmail = tipoEmail;
        this.direccion = direccion;
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public String getTelefono() { return telefono; }
    public String getTipoTelefono() { return tipoTelefono; }
    public String getEmail() { return email; }
    public String getTipoEmail() { return tipoEmail; }
    public String getDireccion() { return direccion; }
    public String getFechaNacimiento() { return fechaNacimiento; }
}