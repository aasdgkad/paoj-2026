package com.pao.laboratory05.angajati;

/**
 * Exercise 3 — Angajați
 *
 * Cerințele complete se află în:
 *   src/com/pao/laboratory05/Readme.md  →  secțiunea "Exercise 3 — Angajați"
 *
 * Creează fișierele de la zero în acest pachet, apoi rulează Main.java
 * pentru a verifica output-ul așteptat din Readme.
 */
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AngajatService service = AngajatService.getInstance();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== Gestionare Angajați =====");
            System.out.println("1. Adaugă angajat");
            System.out.println("2. Listare după salariu");
            System.out.println("3. Caută după departament");
            System.out.println("0. Ieșire");
            System.out.print("Opțiune: ");

            int optiune = scanner.nextInt();
            scanner.nextLine(); // Consumă newline-ul rămas

            if (optiune == 0) {
                System.out.println("La revedere!");
                break;
            }

            switch (optiune) {
                case 1 -> {
                    System.out.print("Nume: ");
                    String nume = scanner.nextLine();
                    System.out.print("Departament (nume): ");
                    String numeDept = scanner.nextLine();
                    System.out.print("Departament (locatie): ");
                    String locatieDept = scanner.nextLine();
                    System.out.print("Salariu: ");
                    double salariu = scanner.nextDouble();

                    service.addAngajat(new Angajat(nume, new Departament(numeDept, locatieDept), salariu));
                }
                case 2 -> service.listBySalary();
                case 3 -> {
                    System.out.print("Departament: ");
                    String cautare = scanner.nextLine();
                    service.findByDepartament(cautare);
                }
                default -> System.out.println("Opțiune invalidă.");
            }
        }
        scanner.close();
    }
}
