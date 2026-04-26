package com.pao.proiect.biblioteca.model;

import java.util.Objects;

public abstract class Utilizator {
    protected String id;
    protected String nume;

    public Utilizator(String id, String nume) {
        this.id = Objects.requireNonNull(id, "ID-ul nu poate fi null");
        this.nume = Objects.requireNonNull(nume, "Numele nu poate fi null");
    }

    public String getNume() {
        return nume;
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Utilizator))
            return false;
        Utilizator that = (Utilizator) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}