package org.example.gui;

import org.example.dao.FournisseurDAO;
import org.example.model.Fournisseur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class FournisseurFrame {
    private JTextField nomField, emailField, telephoneField;
    private JTextArea outputArea;
    private FournisseurDAO dao;

    public JPanel getContentPanel() {
        dao = new FournisseurDAO();

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        nomField = new JTextField(15);
        emailField = new JTextField(15);
        telephoneField = new JTextField(15);

        JButton addButton = new JButton("Ajouter");
        JButton listButton = new JButton("Afficher");

        outputArea = new JTextArea(10, 45);
        outputArea.setEditable(false);

        // Add components to panel
        panel.add(new JLabel("Nom:"));
        panel.add(nomField);
        panel.add(new JLabel("Email:"));
        panel.add(emailField);
        panel.add(new JLabel("Téléphone:"));
        panel.add(telephoneField);
        panel.add(addButton);
        panel.add(listButton);
        panel.add(new JScrollPane(outputArea));

        // Actions
        addButton.addActionListener((ActionEvent e) -> ajouterFournisseur());
        listButton.addActionListener((ActionEvent e) -> afficherFournisseurs());

        return panel;
    }

    private void ajouterFournisseur() {
        try {
            String nom = nomField.getText().trim();
            String email = emailField.getText().trim();
            String tel = telephoneField.getText().trim();

            if (nom.isEmpty() || email.isEmpty() || tel.isEmpty()) {
                outputArea.setText("Veuillez remplir tous les champs !");
                return;
            }

            Fournisseur f = new Fournisseur(nom, email, tel);
            dao.ajouterFournisseur(f);
            outputArea.setText("Fournisseur ajouté avec succès !");
            nomField.setText("");
            emailField.setText("");
            telephoneField.setText("");

        } catch (Exception e) {
            e.printStackTrace();
            outputArea.setText("Erreur: " + e.getMessage());
        }
    }

    private void afficherFournisseurs() {
        try {
            List<Fournisseur> liste = dao.listerFournisseurs();
            outputArea.setText("");
            for (Fournisseur f : liste) {
                outputArea.append(f.getId() + " - " + f.getNom() + " - " + f.getEmail() + " - " + f.getTelephone() + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
            outputArea.setText("Erreur: " + e.getMessage());
        }
    }
}
