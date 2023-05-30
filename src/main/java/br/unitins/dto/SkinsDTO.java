package br.unitins.dto;

import jakarta.validation.constraints.NotBlank;


public record SkinsDTO (


    @NotBlank(message = "O campo deve ser informado.")
    String nome,
    String tipo
   

){

}


