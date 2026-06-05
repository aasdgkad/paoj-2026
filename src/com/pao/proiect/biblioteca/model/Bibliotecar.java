package com.pao.proiect.biblioteca.model;

public class Bibliotecar extends Utilizator {
    private double salariu;
    private String dataAngajare;

    public Bibliotecar(String id, String nume, double salariu, String dataAngajare) {
        super(id, nume);
        if (salariu < 0)
            throw new IllegalArgumentException("Salariul nu poate fi negativ");
        this.salariu = salariu;
        this.dataAngajare = dataAngajare;
    }

    public void scadeSalariul(double procent) {
        if (procent > 0 && procent <= 0.5) {
            this.salariu -= this.salariu * procent;
        } else {
            throw new IllegalArgumentException("Procent invalid");
        }
    }

    public String getDataAngajare() {
        return dataAngajare;
    }

    public double getSalariu() {
        return salariu;
    }

    @Override
    public String toString() {
        return "Bibliotecar: " + nume + " (ID: " + id + "), Data angajării: " + dataAngajare + ", Salariu: " + salariu
                + " RON";
    }
}