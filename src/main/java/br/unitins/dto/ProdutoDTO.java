package br.unitins.dto;

import jakarta.validation.constraints.NotBlank;

public record ProdutoDTO (
    

    @NotBlank(message = "O campo nome deve ser informado.")
    String nome,

    @NotBlank(message = "O campo deve ser informado.")
    String descricao,

    Integer estoque,
    
    Double preco


){

}
