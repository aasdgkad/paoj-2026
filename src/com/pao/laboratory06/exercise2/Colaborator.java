package com.pao.laboratory06.exercise2;

public abstract class Colaborator implements IOperatiiCitireScriere {
    protected String nume, prenume;
    protected double venitbrutlunar;

    public Colaborator() {
    }

    public Colaborator(String nume, String prenume, double venitbrutlunar) {
        this.nume = nume;
        this.prenume = prenume;
        this.venitbrutlunar = venitbrutlunar;
    }

    public String getName() {
        return nume + " " + prenume;
    }

    public double getSalary() {
        return venitbrutlunar;
    }

    @Override
    public String toString() {
        return "Colaborator{" +
                "nume='" + nume + '\'' +
                ", prenume='" + prenume + '\'' +
                ", venitbrutlunar=" + venitbrutlunar +
                '}';
    }

    public abstract double calculeazaVenitNetAnual();
}
