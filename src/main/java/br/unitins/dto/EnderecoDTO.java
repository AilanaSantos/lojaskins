package br.unitins.dto;

import jakarta.validation.constraints.NotBlank;

public record EnderecoDTO (
    
    

    @NotBlank(message = "O campo nome deve ser informado.")
    String principal,

    @NotBlank(message = "O campo nome deve ser informado.")
    String cep,

    @NotBlank(message = "O campo nome deve ser informado.")
    String rua,

    @NotBlank(message = "O campo nome deve ser informado.")
    String bairro,

    @NotBlank(message = "O campo nome deve ser informado.")
    String numero,

    @NotBlank(message = "O campo nome deve ser informado.")
    String complemento,

    @NotBlank(message = "O campo nome deve ser informado.")
    String logradouro



){
    
}
