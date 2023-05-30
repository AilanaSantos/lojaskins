package br.unitins.dto;

import java.time.LocalDate;

import br.unitins.model.Compra;

public record CompraResponseDTO(

        Long id,
        LocalDate date,
        Double totalcompra

) {

    public CompraResponseDTO(Compra compra) {

        this(compra.getId(), compra.getDate(), compra.getTotalcompra());

    }

}
