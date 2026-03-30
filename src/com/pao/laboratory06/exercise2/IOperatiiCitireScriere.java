package com.pao.laboratory06.exercise2;

import java.util.Scanner;

public interface IOperatiiCitireScriere {
    void citire(Scanner scanner);

    void afiseaza();

    String tipContract();

    default boolean areBonus() {
        return false;
    }
}
