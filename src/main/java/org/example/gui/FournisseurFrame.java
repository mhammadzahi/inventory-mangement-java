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

        JPanel mainPanel = new JPanel(new BorderLayout());

        // Top form
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        nomField = new JTextField(10);
        emailField = new JTextField(10);
        telephoneField = new JTextField(10);

        formPanel.add(new JLabel("Nom:"));
        formPanel.add(nomField);
        formPanel.add(new JLabel("Email:"));
        formPanel.add(emailField);
        formPanel.add(new JLabel("Téléphone:"));
        formPanel.add(telephoneField);

        // Output area
        outputArea = new JTextArea(10, 45);
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);

        // Buttons
        JButton addButton = new JButton("Ajouter Fournisseur");
        JButton listButton = new JButton("Afficher Tous");
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(addButton);
        buttonPanel.add(listButton);

        // Assemble
        mainPanel.add(formPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Actions
        addButton.addActionListener((ActionEvent e) -> ajouterFournisseur());
        listButton.addActionListener((ActionEvent e) -> afficherFournisseurs());

        return mainPanel;
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
