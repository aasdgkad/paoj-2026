package com.pao.laboratory10.exercise2;

import com.pao.laboratory10.exercise1.TipTranzactie;
import com.pao.laboratory10.exercise1.Tranzactie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();

        List<Tranzactie> lista = new ArrayList<>();

        for (int i = 0; i < N; i++) {

            int id = scanner.nextInt();
            double suma = scanner.nextDouble();
            String data = scanner.next();
            TipTranzactie tip = TipTranzactie.valueOf(scanner.next());

            lista.add(new Tranzactie(id, suma, data, tip));
        }

        while (scanner.hasNext()) {

            String comanda = scanner.next();

            switch (comanda) {

                case "UNIQUE_IDS": {
                    LinkedHashSet<Integer> ids = new LinkedHashSet<>();

                    for (Tranzactie t : lista) {
                        ids.add(t.getId());
                    }

                    System.out.println(
                            "IDs unice (" + ids.size() + "): " + ids);

                    break;
                }

                case "MONTHLY_REPORT": {

                    TreeMap<String, double[]> raport = new TreeMap<>();

                    for (Tranzactie t : lista) {

                        String luna = t.getData().substring(0, 7);

                        raport.putIfAbsent(luna, new double[2]);

                        double[] valori = raport.get(luna);

                        if (t.getTip() == TipTranzactie.CREDIT) {
                            valori[0] += t.getSuma();
                        } else {
                            valori[1] += t.getSuma();
                        }
                    }

                    for (String luna : raport.keySet()) {

                        double[] valori = raport.get(luna);

                        System.out.printf(
                                "%s: CREDIT %.2f RON, DEBIT %.2f RON%n",
                                luna,
                                valori[0],
                                valori[1]);
                    }

                    break;
                }

                case "TOP": {

                    int n = scanner.nextInt();

                    List<Tranzactie> copie = new ArrayList<>(lista);

                    copie.sort(
                            Comparator.comparingDouble(
                                    Tranzactie::getSuma).reversed());

                    System.out.println("Top " + n + ":");

                    int limita = Math.min(n, copie.size());

                    for (int i = 0; i < limita; i++) {
                        System.out.println(copie.get(i));
                    }

                    break;
                }

                case "SORT_ASC": {

                    Collections.sort(
                            lista,
                            Comparator.comparingDouble(
                                    Tranzactie::getSuma));

                    for (Tranzactie t : lista) {
                        System.out.println(t);
                    }

                    break;
                }

                case "SORT_DESC": {

                    Collections.sort(
                            lista,
                            Comparator.comparingDouble(
                                    Tranzactie::getSuma).reversed());

                    for (Tranzactie t : lista) {
                        System.out.println(t);
                    }

                    break;
                }

                case "REVERSE": {

                    Collections.reverse(lista);

                    for (Tranzactie t : lista) {
                        System.out.println(t);
                    }

                    break;
                }

                case "MIN_MAX": {

                    Comparator<Tranzactie> comparator = Comparator.comparingDouble(
                            Tranzactie::getSuma);

                    Tranzactie min = Collections.min(lista, comparator);
                    Tranzactie max = Collections.max(lista, comparator);

                    System.out.println("MIN: " + min);
                    System.out.println("MAX: " + max);

                    break;
                }

                case "CME_DEMO": {

                    try {

                        for (Tranzactie t : lista) {
                            lista.remove(t);
                        }

                    } catch (ConcurrentModificationException e) {

                        System.out.println(
                                "ConcurrentModificationException prins: modificare in iteratie detectata.");
                    }

                    break;
                }
            }
        }

        scanner.close();
    }
}