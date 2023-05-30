package br.unitins.dto;

import jakarta.validation.constraints.NotBlank;

public record TelefoneDTO(

        @NotBlank(message = "O campo deve ser informado.")
        String numero,

        @NotBlank(message = "O campo deve ser informado.")
        String codigoArea


) {

}
