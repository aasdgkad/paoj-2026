package com.pao.laboratory07.exercise2;

public final class ComandaRedusa extends Comanda {
    private double reducere;

    public ComandaRedusa(String nume, double pret, double reducere) {
        this.nume = nume;
        this.reducere = reducere;
        this.pret = pret;
    }

    @Override
    public double pretFinal() {
        return pret * (1 - reducere / 100.0);
    }

    @Override
    public String descriere() {
        return "DISCOUNTED: " + nume + ", pret: " + String.format("%.2f", pretFinal()) + " lei (-"
                + String.format("%.0f", reducere) + "%) [PLACED]";
    }
}
