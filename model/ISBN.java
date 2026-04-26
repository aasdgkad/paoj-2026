package com.pao.proiect.biblioteca.model;

import java.util.Objects;

public final class ISBN {
    private final String valoare;

    public ISBN(String valoare) {
        if (valoare == null || valoare.length() < 10) {
            throw new IllegalArgumentException("ISBN invalid!");
        }
        this.valoare = valoare;
    }

    public String getValoare() {
        return valoare;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        ISBN isbn = (ISBN) o;
        return Objects.equals(valoare, isbn.valoare);
    }

    @Override
    public int hashCode() {
        return Objects.hash(valoare);
    }

    @Override
    public String toString() {
        return valoare;
    }
}