package com.pao.laboratory09.exercise2;

import com.pao.laboratory09.exercise1.TipTranzactie;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Main {
    private static final String FILE_PATH = "output/lab09_ex2.bin";
    private static final int RECORD_SIZE = 32;

    public static void main(String[] args) {
        new File("output").mkdirs();
        Scanner scanner = new Scanner(System.in);

        if (!scanner.hasNextInt())
            return;
        int n = scanner.nextInt();
        scanner.nextLine();

        try (FileOutputStream fos = new FileOutputStream(FILE_PATH)) {
            for (int i = 0; i < n; i++) {
                String[] parts = scanner.nextLine().split(" ");
                int id = Integer.parseInt(parts[0]);
                double suma = Double.parseDouble(parts[1]);
                String data = parts[2];
                TipTranzactie tip = TipTranzactie.valueOf(parts[3]);

                fos.write(createRecord(id, suma, data, tip, (byte) 0));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (RandomAccessFile raf = new RandomAccessFile(FILE_PATH, "rw")) {
            while (scanner.hasNext()) {
                String cmdLine = scanner.nextLine();
                String[] parts = cmdLine.split(" ");
                String cmd = parts[0];

                switch (cmd) {
                    case "READ":
                        int idxRead = Integer.parseInt(parts[1]);
                        printRecord(idxRead, readRecord(raf, idxRead));
                        break;

                    case "UPDATE":
                        int idxUpd = Integer.parseInt(parts[1]);
                        String statusStr = parts[2];
                        byte statusByte = getStatusByte(statusStr);

                        raf.seek((long) idxUpd * RECORD_SIZE + 23);
                        raf.write(statusByte);
                        System.out.println("Updated [" + idxUpd + "]: " + statusStr);
                        break;

                    case "PRINT_ALL":
                        long totalRecords = raf.length() / RECORD_SIZE;
                        for (int i = 0; i < totalRecords; i++) {
                            printRecord(i, readRecord(raf, i));
                        }
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static byte[] createRecord(int id, double suma, String data, TipTranzactie tip, byte status) {
        ByteBuffer bb = ByteBuffer.allocate(RECORD_SIZE).order(ByteOrder.LITTLE_ENDIAN);
        bb.putInt(id);
        bb.putDouble(suma);

        byte[] dataBytes = data.getBytes(StandardCharsets.US_ASCII);
        for (int i = 0; i < 10; i++) {
            bb.put(i < dataBytes.length ? dataBytes[i] : (byte) ' ');
        }

        bb.put((byte) (tip == TipTranzactie.CREDIT ? 0 : 1));
        bb.put(status);
        return bb.array();
    }

    private static byte[] readRecord(RandomAccessFile raf, int idx) throws IOException {
        byte[] buffer = new byte[RECORD_SIZE];
        raf.seek((long) idx * RECORD_SIZE);
        raf.read(buffer);
        return buffer;
    }

    private static void printRecord(int idx, byte[] data) {
        ByteBuffer bb = ByteBuffer.wrap(data).order(ByteOrder.LITTLE_ENDIAN);
        int id = bb.getInt();
        double suma = bb.getDouble();
        byte[] dateBytes = new byte[10];
        bb.get(dateBytes);
        String dateStr = new String(dateBytes, StandardCharsets.US_ASCII).trim();
        byte tipByte = bb.get();
        byte statusByte = bb.get();

        String statusStr = switch (statusByte) {
            case 0 -> "PENDING";
            case 1 -> "PROCESSED";
            case 2 -> "REJECTED";
            default -> "UNKNOWN";
        };

        System.out.printf("[%d] id=%d data=%s tip=%s suma=%.2f RON status=%s%n",
                idx, id, dateStr, (tipByte == 0 ? "CREDIT" : "DEBIT"), suma, statusStr);
    }

    private static byte getStatusByte(String status) {
        return switch (status) {
            case "PENDING" -> 0;
            case "PROCESSED" -> 1;
            case "REJECTED" -> 2;
            default -> 0;
        };
    }
}