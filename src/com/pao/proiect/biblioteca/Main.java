package com.pao.proiect.biblioteca;

import com.pao.proiect.biblioteca.exception.ElementNegasitException;
import com.pao.proiect.biblioteca.exception.OperatieInvalidaException;
import com.pao.proiect.biblioteca.model.*;
import com.pao.proiect.biblioteca.repository.ImprumutRepository;
import com.pao.proiect.biblioteca.repository.PublicatieRepository;
import com.pao.proiect.biblioteca.repository.UtilizatorRepository;
import com.pao.proiect.biblioteca.service.CatalogService;
import com.pao.proiect.biblioteca.util.DatabaseConnection;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== TEST JDBC REPOSITORIES ===");

        // =========================
        // REPOSITORY-URI
        // =========================
        PublicatieRepository publicatieRepo = new PublicatieRepository();
        UtilizatorRepository utilizatorRepo = new UtilizatorRepository();
        ImprumutRepository imprumutRepo = new ImprumutRepository();

        // // =========================
        // // 3. IMPRUMUTURI
        // // =========================
        System.out.println("\n--- TEST IMPRUMUTURI ---");

        imprumutRepo.save("u1", 3);
        imprumutRepo.save("u1", 2);

        System.out.println("Imprumuturi active:");
        imprumutRepo.findAllActive().forEach(System.out::println);

        // mark returnat
        imprumutRepo.markReturned(3);

        System.out.println("\nDupa returnare:");
        imprumutRepo.findAllActive().forEach(System.out::println);

    }
}