package com.pol.springboot.app.librarydemo.dto.articulo;


public class CdResponseDTO extends ArticuloResponseDTO {

    private String estiloMusical;

    public CdResponseDTO(
            Long id,
            String titulo,
            String cd, boolean alquilado,
            String estiloMusical
    ) {
        super(id, titulo, "CD", alquilado);
        this.estiloMusical = estiloMusical;
    }
}
