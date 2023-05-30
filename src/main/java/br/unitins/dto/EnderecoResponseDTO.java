package br.unitins.dto;


import br.unitins.model.Endereco;


public record EnderecoResponseDTO (


 Long id,
 String principal,
 String cep,
 String rua,
 String bairro,
 String numero,
 String complemento,
 String logradouro

) 
{
    public EnderecoResponseDTO(Endereco endereco){

        this(endereco.getId(), endereco.getPrincipal(), endereco.getCep(), endereco.getRua(), endereco.getBairro(), endereco.getNumero(), endereco.getComplemento(), endereco.getLogradouro());
    }


}
