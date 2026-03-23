package com.pao.laboratory05.audit;

import java.util.Arrays;

public class AngajatService {
    private Angajat[] angajati;
    private AuditEntry[] auditLog;

    private AngajatService() {
        this.angajati = new Angajat[0];
        this.auditLog = new AuditEntry[0];
    }

    private static class Holder {
        private static final AngajatService INSTANCE = new AngajatService();
    }

    public static AngajatService getInstance() {
        return Holder.INSTANCE;
    }

    private void logAction(String action, String target) {
        AuditEntry[] newLog = new AuditEntry[auditLog.length + 1];
        System.arraycopy(auditLog, 0, newLog, 0, auditLog.length);
        newLog[auditLog.length] = new AuditEntry(action, target);
        this.auditLog = newLog;
    }

    public void addAngajat(Angajat a) {
        Angajat[] newArray = new Angajat[angajati.length + 1];
        System.arraycopy(angajati, 0, newArray, 0, angajati.length);
        newArray[angajati.length] = a;
        this.angajati = newArray;

        logAction("ADD", a.getNume());
        System.out.println("Angajat adăugat: " + a.getNume());
    }

    public void listBySalary() {
        Angajat[] copy = angajati.clone();
        Arrays.sort(copy);
        System.out.println("--- Angajați după salariu (descrescător) ---");
        for (int i = 0; i < copy.length; i++) {
            System.out.println((i + 1) + ". " + copy[i]);
        }
    }

    public void findByDepartament(String numeDept) {
        logAction("FIND_BY_DEPT", numeDept);
        boolean found = false;
        System.out.println("--- Angajați din " + numeDept + " ---");
        for (Angajat a : angajati) {
            if (a.getDepartament().nume().equalsIgnoreCase(numeDept)) {
                System.out.println(a);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Niciun angajat în departamentul: " + numeDept);
        }
    }

    public void printAuditLog() {
        System.out.println("--- Audit Log ---");
        for (AuditEntry entry : auditLog) {
            System.out.println(entry);
        }
    }
}