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

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        nomField = new JTextField(15);
        prixField = new JTextField(10);
        quantiteField = new JTextField(10);
        fournisseurIdField = new JTextField(5);
        JButton addButton = new JButton("Ajouter");
        JButton listButton = new JButton("Afficher");
        outputArea = new JTextArea(10, 45);
        outputArea.setEditable(false);

        panel.add(new JLabel("Nom:")); panel.add(nomField);
        panel.add(new JLabel("Prix:")); panel.add(prixField);
        panel.add(new JLabel("Quantité:")); panel.add(quantiteField);
        panel.add(new JLabel("ID Fournisseur:")); panel.add(fournisseurIdField);
        panel.add(addButton);
        panel.add(listButton);
        panel.add(new JScrollPane(outputArea));

        addButton.addActionListener((ActionEvent e) -> ajouterProduit());
        listButton.addActionListener((ActionEvent e) -> afficherProduits());

        return panel;
    }

    private void ajouterProduit() {
        try {
            Produit p = new Produit(
                    nomField.getText(),
                    Double.parseDouble(prixField.getText()),
                    Integer.parseInt(quantiteField.getText()),
                    Integer.parseInt(fournisseurIdField.getText())
            );
            dao.ajouterProduit(p);
            outputArea.setText("Produit ajouté avec succès !");
        } catch (Exception e) {
            e.printStackTrace();
            outputArea.setText("Erreur: " + e.getMessage());
        }
    }

    private void afficherProduits() {
        try {
            List<Produit> liste = dao.listerProduits();
            outputArea.setText("");
            for (Produit p : liste) {
                outputArea.append(
                        p.getId() + " - " + p.getNom() +
                                " - Prix: " + p.getPrix() +
                                " - Qté: " + p.getQuantite() +
                                " - Fournisseur ID: " + p.getIdFournisseur() + "\n"
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
            outputArea.setText("Erreur: " + e.getMessage());
        }
    }
}
