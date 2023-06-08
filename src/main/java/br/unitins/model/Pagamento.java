package br.unitins.model;

import jakarta.persistence.Entity;

@Entity
public class Pagamento {

    private Float valor;

    private Compra compra;

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

}
