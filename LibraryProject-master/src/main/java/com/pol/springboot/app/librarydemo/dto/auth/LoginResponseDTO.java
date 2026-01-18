package com.pol.springboot.app.librarydemo.dto.auth;

public class LoginResponseDTO {

    private Long id;
    private String nombre;
    private String rol;
    private String token;

    public LoginResponseDTO(Long id, String nombre, String rol, String token) {
        this.id = id;
        this.nombre = nombre;
        this.rol = rol;
        this.token = token;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getRol() {
        return rol;
    }

    public String getToken() {
        return token;
    }
}
