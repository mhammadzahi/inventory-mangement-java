package org.example;

import org.example.gui.FournisseurFrame;
import org.example.gui.ProduitFrame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame mainFrame = new JFrame("Stock Management System");
            mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mainFrame.setSize(700, 500);

            JTabbedPane tabs = new JTabbedPane();

            // Instead of launching their own frames, FournisseurFrame and ProduitFrame will return JPanel
            tabs.addTab("Fournisseurs", new FournisseurFrame().getContentPanel());
            tabs.addTab("Produits", new ProduitFrame().getContentPanel());

            mainFrame.add(tabs);
            mainFrame.setLocationRelativeTo(null); // center the window
            mainFrame.setVisible(true);
        });
    }
}
