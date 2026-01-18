package com.pol.springboot.app.librarydemo.mapper;

import com.pol.springboot.app.librarydemo.dto.articulo.*;
import com.pol.springboot.app.librarydemo.model.*;

public final class ArticuloMapper {

    private ArticuloMapper() {
        // evita instanciación
    }

    public static ArticuloResponseDTO toResponse(Articulo articulo) {

        if (articulo instanceof Libro libro) {
            return new LibroResponseDTO(
                    libro.getId(),
                    libro.getTitulo(),
                    libro.getAutor(),
                    libro.isAlquilado(),
                    libro.getIsbn()
            );
        }

        if (articulo instanceof Cd cd) {
            return new CdResponseDTO(
                    cd.getId(),
                    cd.getTitulo(),
                    "CD",
                    cd.isAlquilado(),
                    cd.getEstiloMusical()
            );
        }

        throw new IllegalStateException(
                "Tipo de artículo no soportado: " + articulo.getClass().getName()
        );
    }
}
