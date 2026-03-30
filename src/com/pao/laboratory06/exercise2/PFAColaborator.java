package com.pao.laboratory06.exercise2;

import java.util.Scanner;

public class PFAColaborator extends Colaborator {
    private double cheltuieliLunare;

    public PFAColaborator() {
    }

    public PFAColaborator(String nume, String prenume, double venitbrutlunar, double cheltuieliLunare) {
        super(nume, prenume, venitbrutlunar);
        this.cheltuieliLunare = cheltuieliLunare;
    }

    @Override
    public double calculeazaVenitNetAnual() {
        final double SALARIU_MINIM_BRUT = 4050.0;

        double venitNet = (super.venitbrutlunar - cheltuieliLunare) * 12;

        double impozit = 0.10 * venitNet;

        double cass;
        if (venitNet < 6 * SALARIU_MINIM_BRUT) {
            cass = 0.10 * (6 * SALARIU_MINIM_BRUT);
        } else if (venitNet <= 72 * SALARIU_MINIM_BRUT) {
            cass = 0.10 * venitNet;
        } else {
            cass = 0.10 * (72 * SALARIU_MINIM_BRUT);
        }

        double cas = 0;
        if (venitNet >= 12 * SALARIU_MINIM_BRUT && venitNet <= 24 * SALARIU_MINIM_BRUT) {
            cas = 0.25 * (12 * SALARIU_MINIM_BRUT);
        } else if (venitNet > 24 * SALARIU_MINIM_BRUT) {
            cas = 0.25 * (24 * SALARIU_MINIM_BRUT);
        }

        double venitNetAnual = venitNet - impozit - cass - cas;
        return venitNetAnual;
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
        return "PFA";
    }
}
