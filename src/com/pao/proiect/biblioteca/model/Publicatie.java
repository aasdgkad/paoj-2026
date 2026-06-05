package com.pao.proiect.biblioteca.model;

import java.util.Objects;

public abstract class Publicatie implements Comparable<Publicatie> {

    protected String titlu;
    protected Categorie categorie;
    protected String autor;
    protected ISBN isbn;

    public Publicatie(String titlu, ISBN isbn, String autor, Categorie categorie) {
        this.titlu = titlu;
        this.isbn = isbn;
        this.autor = autor;
        this.categorie = categorie;
    }

    public String getTitlu() {
        return titlu;
    }

    public ISBN getIsbn() {
        return isbn;
    }

    public String getAutor() {
        return autor;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Publicatie that = (Publicatie) o;
        return Objects.equals(isbn, that.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn);
    }

    @Override
    public String toString() {
        return "Publicatie{" + "titlu='" + titlu + '\'' + ", isbn='" + isbn + '\'' + '}';
    }

    @Override
    public int compareTo(Publicatie alta) {
        return this.titlu.compareTo(alta.titlu);
    }
}