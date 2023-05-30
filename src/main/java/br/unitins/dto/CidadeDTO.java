package br.unitins.dto;

import jakarta.validation.constraints.NotBlank;


public record CidadeDTO(

Long id,
Long idEstado,

@NotBlank(message = "O campo nome deve ser informado.")
String nome



) {


}

