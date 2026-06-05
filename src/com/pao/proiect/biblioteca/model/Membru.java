package com.pao.proiect.biblioteca.model;

import java.util.Objects;
import java.util.ArrayList;
import java.util.List;

public class Membru extends Utilizator {
    private List<Publicatie> imprumutate;

    public Membru(String id, String nume) {
        super(id, nume);
        this.imprumutate = new ArrayList<>();
    }

    public void imprumutaCarte(Publicatie p) {
        Objects.requireNonNull(p, "Cartea nu poate fi null");
        imprumutate.add(p);
    }

    public void returneazaCarte(Publicatie p) {
        imprumutate.remove(p);
    }

    public List<Publicatie> getImprumutate() {
        return new ArrayList<>(imprumutate);
    }
}