package com.pao.proiect.biblioteca.model;

public class CarteFizica extends Publicatie {
    private String StareFizica;

    public CarteFizica(String titlu, ISBN isbn, String autor, Categorie categorie, String stareFizica) {
        super(titlu, isbn, autor, categorie);
        this.StareFizica = stareFizica;
    }

    public String getStareFizica() {
        return StareFizica;
    }

    public void setStareFizica(String stareFizica) {
        this.StareFizica = stareFizica;
    }

    @Override
    public String toString() {
        return "Carte Fizica [" + "Titlu: " + titlu + ", ISBN: " + isbn +
                ", Stare Fizica: " + StareFizica + "]";
    }
}