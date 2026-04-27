package com.pao.laboratory08.exercise1;

import java.io.*;
import java.util.*;

public class Main {
    private static final String FILE_PATH = "src/com/pao/laboratory08/tests/studenti.txt";

    public static void main(String[] args) {
        List<Student> studenti = incarcareStudenti(FILE_PATH);

        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(" ", 2);
            String comanda = parts[0];

            if (comanda.equals("PRINT")) {
                studenti.forEach(System.out::println);
            } else if (comanda.equals("SHALLOW") || comanda.equals("DEEP")) {
                String numeCautat = parts[1];
                Student original = null;
                for (Student s : studenti) {
                    if (s.getNume().equals(numeCautat)) {
                        original = s;
                        break;
                    }
                }

                if (original != null) {
                    try {
                        Student clona;
                        if (comanda.equals("SHALLOW")) {
                            clona = (Student) original.clone();
                        } else {
                            clona = (Student) original.clone();
                            clona.setAdresa((Adresa) original.getAdresa().clone());
                        }

                        clona.getAdresa().setOras("MODIFICAT");

                        System.out.println("Original: " + original);
                        System.out.println("Clona: " + clona);

                    } catch (CloneNotSupportedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        scanner.close();
    }

    private static List<Student> incarcareStudenti(String path) {
        List<Student> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] p = line.split(",");

                if (p.length < 4) {
                    System.err.println("Atenție: Linie invalidă ignorată -> " + line);
                    continue;
                }

                try {
                    Adresa adresa = new Adresa(p[2].trim(), p[3].trim());
                    Student s = new Student(p[0].trim(), Integer.parseInt(p[1].trim()), adresa);
                    lista.add(s);
                } catch (NumberFormatException e) {
                    System.err.println("Eroare la parsarea vârstei în linia: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Eroare la citirea fișierului: " + e.getMessage());
        }
        return lista;
    }
}