package com.pao.proiect.biblioteca.service;

import com.pao.proiect.biblioteca.exception.ElementNegasitException;
import com.pao.proiect.biblioteca.exception.OperatieInvalidaException;
import com.pao.proiect.biblioteca.model.*;
import java.util.*;

public class CatalogService {
    private static CatalogService instance;
    private final Set<Publicatie> inventar;
    private final Map<String, List<Publicatie>> indexAutor;
    private final Map<Categorie, List<Publicatie>> indexCategorie;

    private CatalogService() {
        this.inventar = new TreeSet<>();
        this.indexAutor = new HashMap<>();
        this.indexCategorie = new HashMap<>();
    }

    public static synchronized CatalogService getInstance() {
        if (instance == null) {
            instance = new CatalogService();
        }
        return instance;
    }

    public void adauga(Publicatie p) throws IllegalArgumentException {
        if (p == null)
            throw new OperatieInvalidaException("Publicatie nula");

        if (inventar.stream().anyMatch(carte -> carte.getIsbn().equals(p.getIsbn()))) {
            throw new OperatieInvalidaException("Eroare: O carte cu ISBN-ul " + p.getIsbn() + " exista deja.");
        }

        inventar.add(p);
        indexAutor.computeIfAbsent(p.getAutor(), k -> new ArrayList<>()).add(p);
        indexCategorie.computeIfAbsent(p.getCategorie(), k -> new ArrayList<>()).add(p);
    }

    public void sterge(ISBN isbn) {
        Publicatie p = inventar.stream().filter(a -> a.getIsbn().equals(isbn)).findFirst().orElse(null);
        if (p == null) {
            throw new ElementNegasitException("Nu s-a gasit cartea cu ISBN: " + isbn);
        } else {
            inventar.remove(p);
            for (List<Publicatie> lista : indexAutor.values()) {
                lista.remove(p);
            }
            for (List<Publicatie> lista : indexCategorie.values()) {
                lista.remove(p);
            }
        }
    }

    public List<Publicatie> listeazaToate() {
        return new ArrayList<>(inventar);
    }

    public List<Publicatie> getCartiDupaAutor(String autor) {
        return indexAutor.getOrDefault(autor, Collections.emptyList());
    }

    public List<Publicatie> getCartiDupaCategorie(Categorie categorie) {
        return indexCategorie.getOrDefault(categorie, Collections.emptyList());
    }
}