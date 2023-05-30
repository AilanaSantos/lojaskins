package br.unitins.dto;

import jakarta.validation.constraints.NotBlank;


import br.unitins.model.Telefone;

public record TelefoneResponseDTO(

    Long id,
    @NotBlank(message = "O campo nome deve ser informado.")
    String numero,

    String codigoarea

){

    public  TelefoneResponseDTO(Telefone tele){

        this(tele.getId(),tele.getNumero(),tele.getCodigoArea());
    }

}
