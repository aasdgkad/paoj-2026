package com.pao.proiect.biblioteca;

import com.pao.proiect.biblioteca.exception.ElementNegasitException;
import com.pao.proiect.biblioteca.exception.OperatieInvalidaException;
import com.pao.proiect.biblioteca.model.*;
import com.pao.proiect.biblioteca.service.CatalogService;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        CatalogService service = CatalogService.getInstance();

        Publicatie c1 = new CarteFizica("Dune", new ISBN("111-222-333"), "Frank Herbert", Categorie.SF, "Noua");
        Publicatie c2 = new CarteFizica("Istoria Romanilor", new ISBN("333-444-555"), "Neagu Djuvara",
                Categorie.ISTORIE,
                "Uzata");
        Publicatie c3 = new CarteDigitala("Clean Code", new ISBN("555-666-777"), "Robert Martin", Categorie.TEHNOLOGIE,
                "PDF", 2.5);
        Publicatie c4 = new CarteDigitala("Sapiens", new ISBN("777-888-999"), "Yuval Harari", Categorie.NON_FICTIUNE,
                "EPUB", 1.2);

        service.adauga(c1);
        service.adauga(c2);
        service.adauga(c3);
        service.adauga(c4);

        try {
            service.sterge(new ISBN("111-222-233"));

        } catch (ElementNegasitException e) {
            System.out.println("Eroare cautare: " + e.getMessage());
        } catch (OperatieInvalidaException e) {
            System.out.println("Eroare operatie: " + e.getMessage());
        } catch (NullPointerException e) {
            System.out.println("Date lipsa (input invalid): " + e.getMessage());
        }

        System.out.println("--- Catalog Complet ---");
        afiseazaCatalog(service.listeazaToate());

        System.out.println("\n--- Cautare dupa Autor: Robert Martin ---");
        afiseazaCatalog(service.getCartiDupaAutor("Robert Martin"));
    }

    private static void afiseazaCatalog(List<Publicatie> lista) {
        if (lista.isEmpty()) {
            System.out.println("Nicio carte găsită.");
            return;
        }

        for (Publicatie p : lista) {
            String detalii = String.format("Titlu: %-15s | Autor: %-15s | Categorie: %s",
                    p.getTitlu(), p.getAutor(), p.getCategorie());

            if (p instanceof CarteFizica f) {
                System.out.println(detalii + " | [Fizica: " + f.getStareFizica() + "]");
            } else if (p instanceof CarteDigitala d) {
                System.out.println(detalii + " | [Digitala: " + d.getFormat() + ", " + d.getMarimeMB() + "MB]");
            }
        }
    }
}