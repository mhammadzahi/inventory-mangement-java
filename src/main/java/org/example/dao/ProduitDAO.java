package org.example.dao;

import org.example.db.DBConnection;
import org.example.model.Produit;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProduitDAO {

    public void ajouterProduit(Produit p) throws SQLException {
        String sql = "INSERT INTO produit (nom, prix, quantite, id_fournisseur) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, p.getNom());
            stmt.setDouble(2, p.getPrix());
            stmt.setInt(3, p.getQuantite());
            stmt.setInt(4, p.getFournisseurId());
            stmt.executeUpdate();
        }
    }

    public List<Produit> listerProduits() throws SQLException {
        List<Produit> liste = new ArrayList<>();
        String sql = "SELECT * FROM produit";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Produit p = new Produit(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getDouble("prix"),
                        rs.getInt("quantite"),
                        rs.getInt("id_fournisseur")
                );
                liste.add(p);
            }
        }
        return liste;
    }

    public void supprimerProduit(int id) throws SQLException {
        String sql = "DELETE FROM produit WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
