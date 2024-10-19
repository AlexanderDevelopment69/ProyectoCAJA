package com.mycompany.proyectocaja.model;

public class Usuario {

    private int id;
    private String username;
    private String password;
    private Rol rol; // Asociación con la clase Rol
    private String nombreCompleto;
    private String email;
    private boolean estado; // Para indicar si el usuario está activo o no

    // Constructor vacío
    public Usuario() {}

    // Constructor completo
    public Usuario(int id, String username, String password, Rol rol, String nombreCompleto, String email, boolean estado) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.rol = rol; // Se relaciona el rol con la clase Rol
        this.nombreCompleto = nombreCompleto;
        this.email = email;
        this.estado = estado;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    // Método para mostrar una descripción del usuario
    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", rol=" + rol.getNombre() + // Se obtiene el nombre del rol
                ", nombreCompleto='" + nombreCompleto + '\'' +
                ", email='" + email + '\'' +
                ", estado=" + estado +
                '}';
    }
}
