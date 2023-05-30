package br.unitins.dto;


import br.unitins.model.Skins;

public record SkinsResponseDTO(

    long id,

    String nome,

    String tipo

){

    public SkinsResponseDTO(Skins skins){

        this(skins.getId(),skins.getTipo(),skins.getNome());
    }

}
