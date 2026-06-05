package com.pao.proiect.biblioteca.repository;

import com.pao.proiect.biblioteca.model.*;
import com.pao.proiect.biblioteca.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PublicatieRepository {

    private final Connection connection;

    public PublicatieRepository() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    public void save(Publicatie p) {

        String sql = """
                    INSERT INTO publicatii
                    (isbn, titlu, autor, categorie, tip, stare_fizica, format, marime_mb)
                    VALUES (?, ?, ?, ?, ?, ?, ?, ?)
                """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, p.getIsbn().getValoare());
            ps.setString(2, p.getTitlu());
            ps.setString(3, p.getAutor());
            ps.setString(4, p.getCategorie().name());

            if (p instanceof CarteFizica cf) {

                ps.setString(5, "FIZICA");
                ps.setString(6, cf.getStareFizica());
                ps.setNull(7, Types.VARCHAR);
                ps.setNull(8, Types.DOUBLE);

            } else {

                CarteDigitala cd = (CarteDigitala) p;

                ps.setString(5, "DIGITALA");
                ps.setNull(6, Types.VARCHAR);
                ps.setString(7, cd.getFormat());
                ps.setDouble(8, cd.getMarimeMB());
            }

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Publicatie> findAll() {

        List<Publicatie> list = new ArrayList<>();

        String sql = "SELECT * FROM publicatii";

        try (PreparedStatement ps = connection.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                String isbn = rs.getString("isbn");
                String titlu = rs.getString("titlu");
                String autor = rs.getString("autor");
                Categorie categorie = Categorie.valueOf(rs.getString("categorie"));
                String tip = rs.getString("tip");

                Publicatie p;

                if ("FIZICA".equals(tip)) {

                    p = new CarteFizica(
                            titlu,
                            new ISBN(isbn),
                            autor,
                            categorie,
                            rs.getString("stare_fizica"));

                } else {

                    p = new CarteDigitala(
                            titlu,
                            new ISBN(isbn),
                            autor,
                            categorie,
                            rs.getString("format"),
                            rs.getDouble("marime_mb"));
                }

                list.add(p);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    public Optional<Publicatie> findByISBN(String isbn) {

        String sql = "SELECT * FROM publicatii WHERE isbn = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, isbn);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    String titlu = rs.getString("titlu");
                    String autor = rs.getString("autor");
                    Categorie categorie = Categorie.valueOf(rs.getString("categorie"));
                    String tip = rs.getString("tip");

                    Publicatie p;

                    if ("FIZICA".equals(tip)) {

                        p = new CarteFizica(titlu, new ISBN(isbn), autor, categorie, rs.getString("stare_fizica"));

                    } else {

                        p = new CarteDigitala(titlu, new ISBN(isbn), autor, categorie, rs.getString("format"),
                                rs.getDouble("marime_mb"));
                    }

                    return Optional.of(p);
                }

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return Optional.empty();
    }

    public void deleteByISBN(String isbn) {

        String sql = "DELETE FROM publicatii WHERE isbn = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, isbn);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Publicatie p) {

        String sql = """
                    UPDATE publicatii
                    SET titlu = ?, autor = ?, categorie = ?, tip = ?,
                        stare_fizica = ?, format = ?, marime_mb = ?
                    WHERE isbn = ?
                """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, p.getTitlu());
            ps.setString(2, p.getAutor());
            ps.setString(3, p.getCategorie().name());

            if (p instanceof CarteFizica cf) {

                ps.setString(4, "FIZICA");
                ps.setString(5, cf.getStareFizica());
                ps.setNull(6, Types.VARCHAR);
                ps.setNull(7, Types.DOUBLE);

            } else {

                CarteDigitala cd = (CarteDigitala) p;

                ps.setString(4, "DIGITALA");
                ps.setNull(5, Types.VARCHAR);
                ps.setString(6, cd.getFormat());
                ps.setDouble(7, cd.getMarimeMB());
            }

            ps.setString(8, p.getIsbn().getValoare());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}