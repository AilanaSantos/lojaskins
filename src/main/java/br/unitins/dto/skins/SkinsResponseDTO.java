package br.unitins.dto.skins;


import br.unitins.model.Skins;

public record SkinsResponseDTO(

    long id,

    String nome,

    String tipo

){

    public SkinsResponseDTO(Skins skins){

        this(skins.getId(),skins.getTipoDesgate(),skins.getNome());
    }

}
