package com.pao.proiect.biblioteca.exception;

// Pentru situațiile în care operația nu poate fi făcută (duplicat, invalid etc.)
public class OperatieInvalidaException extends RuntimeException {
    public OperatieInvalidaException(String message) {
        super(message);
    }
}