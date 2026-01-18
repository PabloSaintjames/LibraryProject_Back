package com.pol.springboot.app.librarydemo.dto.articulo;

public class ArticuloResponseDTO {

    private Long id;
    private String titulo;
    private String autor;
    private String tipo;      // LIBRO / CD
    private boolean alquilado;

    public ArticuloResponseDTO() {}

    public ArticuloResponseDTO(
            Long id,
            String titulo,
            String autor,
            String tipo,
            boolean alquilado
    ) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.tipo = tipo;
        this.alquilado = alquilado;
    }

    public ArticuloResponseDTO(Long id, String titulo, String cd, boolean alquilado) {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public boolean isAlquilado() {
        return alquilado;
    }

    public void setAlquilado(boolean alquilado) {
        this.alquilado = alquilado;
    }
}
