package com.pao.proiect.biblioteca.repository;

import com.pao.proiect.biblioteca.model.*;
import com.pao.proiect.biblioteca.util.DatabaseConnection;

import java.sql.*;
import java.util.*;

public class UtilizatorRepository implements Repository<Utilizator, String> {

    private final Connection connection;

    public UtilizatorRepository() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public void save(Utilizator u) {

        String sql = """
                    INSERT INTO utilizatori (id, nume, tip, salariu, data_angajare)
                    VALUES (?, ?, ?, ?, ?)
                """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, u.getId());
            ps.setString(2, u.getNume());

            if (u instanceof Bibliotecar b) {
                ps.setString(3, "BIBLIOTECAR");
                ps.setDouble(4, b.getSalariu());
                ps.setDate(5, java.sql.Date.valueOf(b.getDataAngajare()));
            } else {
                ps.setString(3, "MEMBRU");
                ps.setNull(4, Types.DOUBLE);
                ps.setNull(5, Types.DATE);
            }

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Utilizator> findById(String id) {

        String sql = "SELECT * FROM utilizatori WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, id);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    String tip = rs.getString("tip");

                    if ("BIBLIOTECAR".equals(tip)) {
                        return Optional.of(new Bibliotecar(rs.getString("id"), rs.getString("nume"),
                                rs.getDouble("salariu"),
                                rs.getString("data_angajare")));
                    } else {
                        return Optional.of(new Membru(
                                rs.getString("id"),
                                rs.getString("nume")));
                    }
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return Optional.empty();
    }

    @Override
    public List<Utilizator> findAll() {

        List<Utilizator> list = new ArrayList<>();

        String sql = "SELECT * FROM utilizatori";

        try (PreparedStatement ps = connection.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                String tip = rs.getString("tip");

                if ("BIBLIOTECAR".equals(tip)) {
                    list.add(new Bibliotecar(
                            rs.getString("id"),
                            rs.getString("nume"),
                            rs.getDouble("salariu"),
                            rs.getString("data_angajare")));
                } else {
                    list.add(new Membru(
                            rs.getString("id"),
                            rs.getString("nume")));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    @Override
    public void update(Utilizator u) {

        String sql = """
                    UPDATE utilizatori
                    SET nume = ?, tip = ?, salariu = ?, data_angajare = ?
                    WHERE id = ?
                """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, u.getNume());

            if (u instanceof Bibliotecar b) {
                ps.setString(2, "BIBLIOTECAR");
                ps.setDouble(3, b.getSalariu());
                ps.setDate(4, java.sql.Date.valueOf(b.getDataAngajare()));
            } else {
                ps.setString(2, "MEMBRU");
                ps.setNull(3, Types.DOUBLE);
                ps.setNull(4, Types.DATE);
            }

            ps.setString(5, u.getId());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(String id) {

        String sql = "DELETE FROM utilizatori WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}