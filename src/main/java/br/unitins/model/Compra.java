package br.unitins.model;

import jakarta.persistence.Entity;

import java.time.LocalDate;

@Entity
public class Compra extends DefaultEntity {

    private LocalDate date;

    private Double totalcompra;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getTotalcompra() {
        return totalcompra;
    }

    public void setTotalcompra(Double double1) {
        this.totalcompra = double1;
    }

}
