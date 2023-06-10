package br.unitins.model;

import jakarta.persistence.Entity;

@Entity
public class Skins extends DefaultEntity {

    private String nome;
    private String tipoDesgate;
    private Raridade raridade;
    private TipoArma tipoArma;

    

    public TipoArma getTipoArma() {
        return tipoArma;
    }

    public void setTipoArma(TipoArma tipoArma) {
        this.tipoArma = tipoArma;
    }

    public Raridade getRaridade() {
        return raridade;
    }

    public void setRaridade(Raridade raridade) {
        this.raridade = raridade;
    }

    public String getTipoDesgate() {
        return tipoDesgate;
    }

    public void setTipoDesgate(String tipoDesgate) {
        this.tipoDesgate = tipoDesgate;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
