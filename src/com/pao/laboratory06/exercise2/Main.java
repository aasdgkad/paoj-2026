package com.pao.laboratory06.exercise2;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextInt())
            return;

        int n = sc.nextInt();
        List<Colaborator> lista = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String tipStr = sc.next();
            TipColaborator tip = TipColaborator.valueOf(tipStr); // Convertește String în Enum

            Colaborator c = null;
            switch (tip) {
                case CIM -> c = new CIMColaborator();
                case PFA -> c = new PFAColaborator();
                case SRL -> c = new SRLColaborator();
            }

            if (c != null) {
                c.citire(sc); // Fiecare clasă știe acum să citească restul liniei sale
                lista.add(c);
            }
        }

        // 1. Afișare listă completă și găsire Maxim
        Colaborator maxVenit = lista.get(0);
        for (Colaborator c : lista) {
            c.afiseaza();
            if (c.calculeazaVenitNetAnual() > maxVenit.calculeazaVenitNetAnual()) {
                maxVenit = c;
            }
        }

        // 2. Afișare Colaborator Maxim
        System.out.print("\nColaborator cu venit net maxim: ");
        maxVenit.afiseaza();

        // 3. Filtrare Persoane Juridice (folosind instanceof)
        System.out.println("\nColaboratori persoane juridice:");
        for (Colaborator c : lista) {
            if (c.tipContract().equals("SRL")) {
                c.afiseaza();
            }
        }

        // 4. Statistici pe tip (Sume și Număr)
        System.out.println("\nSume și număr colaboratori pe tip:");
        Map<String, Double> sume = new LinkedHashMap<>();
        Map<String, Integer> contoare = new LinkedHashMap<>();

        for (Colaborator c : lista) {
            String tip = c.tipContract();
            double net = c.calculeazaVenitNetAnual();

            sume.put(tip, sume.getOrDefault(tip, 0.0) + net);
            contoare.put(tip, contoare.getOrDefault(tip, 0) + 1);
        }

        sume.forEach((tip, suma) -> {
            System.out.printf("%s: suma = %.2f lei, număr = %d%n",
                    tip, suma, contoare.get(tip));
        });
    }
}