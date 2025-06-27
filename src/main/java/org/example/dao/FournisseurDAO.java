package org.example.dao;

import org.example.db.DBConnection;
import org.example.model.Fournisseur;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FournisseurDAO {

    public void ajouterFournisseur(Fournisseur f) throws SQLException {
        String sql = "INSERT INTO fournisseur (nom, email, telephone) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, f.getNom());
            stmt.setString(2, f.getEmail());
            stmt.setString(3, f.getTelephone());
            stmt.executeUpdate();
        }
    }

    public List<Fournisseur> listerFournisseurs() throws SQLException {
        List<Fournisseur> liste = new ArrayList<>();
        String sql = "SELECT * FROM fournisseur";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Fournisseur f = new Fournisseur(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("email"),
                        rs.getString("telephone")
                );
                liste.add(f);
            }
        }
        return liste;
    }

    public void supprimerFournisseur(int id) throws SQLException {
        String sql = "DELETE FROM fournisseur WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
