package com.pao.laboratory06.exercise2;

import java.util.Scanner;

public class SRLColaborator extends Colaborator {
    private double cheltuieliLunare;

    public SRLColaborator() {
    }

    public SRLColaborator(String nume, String prenume, double venitbrutlunar, double cheltuieliLunare) {
        super(nume, prenume, venitbrutlunar);
        this.cheltuieliLunare = cheltuieliLunare;
    }

    @Override
    public double calculeazaVenitNetAnual() {
        return (super.venitbrutlunar - cheltuieliLunare) * 12 * 0.84;
    }

    @Override
    public void citire(Scanner in) {
        this.nume = in.next();
        this.prenume = in.next();
        this.venitbrutlunar = in.nextDouble();
        this.cheltuieliLunare = in.nextDouble();
    }

    @Override
    public void afiseaza() {
        System.out.printf("%s: %s %s, venit net anual: %.2f lei%n",
                tipContract(), nume, prenume, calculeazaVenitNetAnual());
    }

    @Override
    public String tipContract() {
        return "SRL";
    }

}
