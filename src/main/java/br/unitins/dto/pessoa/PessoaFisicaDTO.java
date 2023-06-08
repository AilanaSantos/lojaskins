package br.unitins.dto.pessoa;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PessoaFisicaDTO (

    @NotBlank(message = "O campo cpf deve ser informado.")
    @Size(max = 14, message = "O cpf deve posssuir no máximo 14 caracteres.")
    String cpf,

    Integer sexo,

    @NotBlank(message = "O campo nome deve ser informado.")
    String nome
    ){

}
