package com.pao.laboratory06.exercise2;

import java.util.Scanner;

public class CIMColaborator extends Colaborator {
    private boolean bonus;

    public CIMColaborator() {
    }

    public CIMColaborator(String nume, String prenume, double venitbrutlunar, boolean bonus) {
        super(nume, prenume, venitbrutlunar);
        this.bonus = bonus;
    }

    @Override
    public double calculeazaVenitNetAnual() {
        double venitNetAnual = super.venitbrutlunar * 0.55 * 12;
        if (bonus) {
            venitNetAnual *= 1.10;
        }
        return venitNetAnual;
    }

    @Override
    public void citire(Scanner in) {
        this.nume = in.next();
        this.prenume = in.next();
        this.venitbrutlunar = in.nextDouble();
        String bonusInput = in.next();
        this.bonus = bonusInput.equalsIgnoreCase("DA");
    }

    @Override
    public void afiseaza() {
        System.out.printf("%s: %s %s, venit net anual: %.2f lei%n",
                tipContract(), nume, prenume, calculeazaVenitNetAnual());
    }

    @Override
    public String tipContract() {
        return "CIM";
    }

    @Override
    public boolean areBonus() {
        return bonus;
    }
}
