package com.pao.proiect.biblioteca.service;

import com.pao.proiect.biblioteca.model.Utilizator;
import com.pao.proiect.biblioteca.exception.ElementNegasitException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class UtilizatorService {
    private static UtilizatorService instance;
    private final Set<Utilizator> utilizatori = new HashSet<>();

    private UtilizatorService() {
    }

    public static UtilizatorService getInstance() {
        if (instance == null)
            instance = new UtilizatorService();
        return instance;
    }

    public void adauga(Utilizator u) {
        if (u == null)
            throw new IllegalArgumentException("Utilizatorul nu poate fi null");
        utilizatori.add(u);
    }

    public void sterge(String id) {
        boolean removed = utilizatori.removeIf(u -> u.getId().equals(id));
        if (!removed) {
            throw new ElementNegasitException("Utilizatorul cu ID-ul " + id + " nu a fost găsit.");
        }
    }

    public Optional<Utilizator> cauta(String id) {
        return utilizatori.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst();
    }
}