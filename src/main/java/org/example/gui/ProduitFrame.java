package org.example.gui;

import org.example.dao.ProduitDAO;
import org.example.model.Produit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class ProduitFrame {
    private JTextField nomField, prixField, quantiteField, fournisseurIdField;
    private JTextArea outputArea;
    private ProduitDAO dao;

    public JPanel getContentPanel() {
        dao = new ProduitDAO();

        JPanel mainPanel = new JPanel(new BorderLayout());

        // Top Form
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        nomField = new JTextField(10);
        prixField = new JTextField(10);
        quantiteField = new JTextField(10);
        fournisseurIdField = new JTextField(10);

        formPanel.add(new JLabel("Nom:"));
        formPanel.add(nomField);
        formPanel.add(new JLabel("Prix:"));
        formPanel.add(prixField);
        formPanel.add(new JLabel("Quantité:"));
        formPanel.add(quantiteField);
        formPanel.add(new JLabel("ID Fournisseur:"));
        formPanel.add(fournisseurIdField);

        // Output
        outputArea = new JTextArea(10, 45);
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);

        // Bottom Buttons
        JButton addButton = new JButton("Ajouter");
        JButton listButton = new JButton("Afficher");
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(addButton);
        buttonPanel.add(listButton);

        // Assemble Layout
        mainPanel.add(formPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Actions
        addButton.addActionListener((ActionEvent e) -> ajouterProduit());
        listButton.addActionListener((ActionEvent e) -> afficherProduits());

        return mainPanel;
    }

    private void ajouterProduit() {
        try {
            String nom = nomField.getText().trim();
            double prix = Double.parseDouble(prixField.getText().trim());
            int quantite = Integer.parseInt(quantiteField.getText().trim());
            int fournisseurId = Integer.parseInt(fournisseurIdField.getText().trim());

            Produit p = new Produit(nom, prix, quantite, fournisseurId);
            dao.ajouterProduit(p);
            outputArea.setText("Produit ajouté avec succès !");
            nomField.setText("");
            prixField.setText("");
            quantiteField.setText("");
            fournisseurIdField.setText("");
        } catch (Exception e) {
            e.printStackTrace();
            outputArea.setText("Erreur: " + e.getMessage());
        }
    }

    private void afficherProduits() {
        try {
            List<Produit> produits = dao.listerProduits();
            outputArea.setText("");
            for (Produit p : produits) {
                outputArea.append(p.getId() + " - " + p.getNom() + " - " + p.getPrix() +
                        " - " + p.getQuantite() + " - Fournisseur ID: " + p.getFournisseurId() + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
            outputArea.setText("Erreur: " + e.getMessage());
        }
    }
}
