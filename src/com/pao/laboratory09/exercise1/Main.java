package com.pao.laboratory09.exercise1;

import java.io.*;
import java.util.*;

public class Main {
    private static final String FILE_PATH = "output/lab09_ex1.ser";

    public static void main(String[] args) {
        new File("output").mkdirs();

        List<Tranzactie> listaTranzactii = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        if (scanner.hasNextInt()) {
            int n = scanner.nextInt();
            scanner.nextLine();

            for (int i = 0; i < n; i++) {
                String linie = scanner.nextLine();
                String[] p = linie.split(" ");

                Tranzactie t = new Tranzactie(
                        Integer.parseInt(p[0]),
                        Double.parseDouble(p[1]),
                        p[2], p[3], p[4],
                        TipTranzactie.valueOf(p[5]));
                t.setNote("procesat");
                listaTranzactii.add(t);
            }
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(listaTranzactii);
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Tranzactie> deserializate = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            deserializate = (List<Tranzactie>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        while (scanner.hasNext()) {
            String commandLine = scanner.nextLine();
            if (commandLine.isEmpty())
                continue;

            String[] parts = commandLine.split(" ");
            String cmd = parts[0];

            switch (cmd) {
                case "LIST":
                    deserializate.forEach(System.out::println);
                    break;

                case "FILTER":
                    String prefix = parts[1];
                    boolean gasit = false;
                    for (Tranzactie t : deserializate) {
                        if (t.getData().startsWith(prefix)) {
                            System.out.println(t);
                            gasit = true;
                        }
                    }
                    if (!gasit)
                        System.out.println("Niciun rezultat.");
                    break;

                case "NOTE":
                    int idCautat = Integer.parseInt(parts[1]);
                    Tranzactie gasita = null;
                    for (Tranzactie t : deserializate) {
                        if (t.getId() == idCautat) {
                            gasita = t;
                            break;
                        }
                    }
                    if (gasita != null) {
                        System.out.println("NOTE[" + idCautat + "]: " + gasita.getNote());
                    } else {
                        System.out.println("NOTE[" + idCautat + "]: not found");
                    }
                    break;
            }
        }
        scanner.close();
    }
}