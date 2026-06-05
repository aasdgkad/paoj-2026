package com.pao.proiect.biblioteca.repository;

import com.pao.proiect.biblioteca.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ImprumutRepository {

    private final Connection connection;

    public ImprumutRepository() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    // =========================
    // CREATE (imprumut)
    // =========================
    public void save(String utilizatorId, int publicatieId) {

        String sql = """
                    INSERT INTO imprumuturi
                    (utilizator_id, publicatie_id, data_imprumut, returnat)
                    VALUES (?, ?, CURDATE(), false)
                """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, utilizatorId);
            ps.setInt(2, publicatieId);

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // =========================
    // READ active
    // =========================
    public List<String> findAllActive() {

        List<String> list = new ArrayList<>();

        String sql = """
                    SELECT * FROM imprumuturi
                    WHERE returnat = false
                """;

        try (PreparedStatement ps = connection.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                list.add(
                        "ID=" + rs.getInt("id") +
                                ", user=" + rs.getString("utilizator_id") +
                                ", pub=" + rs.getInt("publicatie_id") +
                                ", date=" + rs.getDate("data_imprumut"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    // =========================
    // RETURNARE CARTE
    // =========================
    public void markReturned(int id) {

        String sql = """
                    UPDATE imprumuturi
                    SET returnat = true
                    WHERE id = ?
                """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}