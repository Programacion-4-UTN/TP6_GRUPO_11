package com.example.tp6_grupo_11.model;

import java.io.Serializable;

public class Contacto implements Serializable {
    private int id;
    private String nombre;
    private String apellido;
    private String telefono;
    private String tipoTelefono;
    private String email;
    private String tipoEmail;
    private String direccion;
    private String fechaNacimiento;
    private String estudios;
    private String intereses;
    private boolean recibeInformacion;

    public Contacto() {
    }

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

    public Contacto(int id, String nombre, String apellido, String telefono, String tipoTelefono,
                    String email, String tipoEmail, String direccion, String fechaNacimiento,
                    String estudios, String intereses, boolean recibeInformacion) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.tipoTelefono = tipoTelefono;
        this.email = email;
        this.tipoEmail = tipoEmail;
        this.direccion = direccion;
        this.fechaNacimiento = fechaNacimiento;
        this.estudios = estudios;
        this.intereses = intereses;
        this.recibeInformacion = recibeInformacion;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getTipoTelefono() { return tipoTelefono; }
    public void setTipoTelefono(String tipoTelefono) { this.tipoTelefono = tipoTelefono; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTipoEmail() { return tipoEmail; }
    public void setTipoEmail(String tipoEmail) { this.tipoEmail = tipoEmail; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(String fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }

    public String getEstudios() { return estudios; }
    public void setEstudios(String estudios) { this.estudios = estudios; }

    public String getIntereses() { return intereses; }
    public void setIntereses(String intereses) { this.intereses = intereses; }

    public boolean isRecibeInformacion() { return recibeInformacion; }
    public void setRecibeInformacion(boolean recibeInformacion) { this.recibeInformacion = recibeInformacion; }
}