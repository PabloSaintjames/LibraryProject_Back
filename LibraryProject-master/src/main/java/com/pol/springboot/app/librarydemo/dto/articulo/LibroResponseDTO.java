package com.pol.springboot.app.librarydemo.dto.articulo;

public class LibroResponseDTO extends ArticuloResponseDTO {

    private String isbn;

    public LibroResponseDTO() {}

    public LibroResponseDTO(
            Long id,
            String titulo,
            String autor,
            boolean alquilado,
            String isbn
    ) {
        super(id, titulo, autor, "LIBRO", alquilado);
        this.isbn = isbn;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}
