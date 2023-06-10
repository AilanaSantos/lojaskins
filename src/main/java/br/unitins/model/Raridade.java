package br.unitins.model;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Raridade {

    CONSUMIDOR (1, "Consumidor"),
    MILITAR (2, "Militar"),
    RESTRITO (3, "Restrito"),
    SECRETO (4, "Secreto"),
    OCULTO (5, "Oculto"),
    CONTRABANDO (6, "Contrabando");

     private int id;
    private String label;

    Raridade(int id, String label) {
        this.id = id;
        this.label = label;
    }

    public int getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public static Raridade valueOf(Integer id) throws IllegalArgumentException {
        if (id == null)
            return null;
        for(Raridade raridade : Raridade.values()) {
            if (id.equals(raridade.getId()))
                return raridade;
        } 
        throw new IllegalArgumentException("Id inválido:" + id);
    }

    
}
