package br.unitins.dto;

import br.unitins.model.Produto;

public record ProdutoResponseDTO (
    

    Long id,

    String nome,

    String descricao,

    Integer estoque,

    Double preco

){

    public ProdutoResponseDTO(Produto produto){

        this(produto.getId(), produto.getNome(), produto.getDescricao(), produto.getEstoque(), produto.getPreco());
    }
}
