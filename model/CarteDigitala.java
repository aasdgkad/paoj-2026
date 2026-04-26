package com.pao.proiect.biblioteca.model;

public class CarteDigitala extends Publicatie {
    private String format;
    private double marimeMB;

    public CarteDigitala(String titlu, ISBN isbn, String autor, Categorie categorie, String format, double marimeMB) {
        super(titlu, isbn, autor, categorie);
        this.format = format;
        this.marimeMB = marimeMB;
    }

    public String getFormat() {
        return format;
    }

    public double getMarimeMB() {
        return marimeMB;
    }

    @Override
    public String toString() {
        return "Carte Digitala [" + "Titlu: " + titlu + ", ISBN: " + isbn +
                ", Format: " + format + ", Marime: " + marimeMB + " MB]";
    }
}