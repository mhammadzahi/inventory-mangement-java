package org.example.model;

public class Produit {
    private int id;
    private String nom;
    private double prix;
    private int quantite;
    private int fournisseurId;

    public Produit() {}

    public Produit(String nom, double prix, int quantite, int fournisseurId) {
        this.nom = nom;
        this.prix = prix;
        this.quantite = quantite;
        this.fournisseurId = fournisseurId;
    }

    public Produit(int id, String nom, double prix, int quantite, int fournisseurId) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
        this.quantite = quantite;
        this.fournisseurId = fournisseurId;
    }

    // Getters
    public int getId() { return id; }
    public String getNom() { return nom; }
    public double getPrix() { return prix; }
    public int getQuantite() { return quantite; }
    public int getFournisseurId() { return fournisseurId; }

    // Setters
    public void setId(int id) { this.id = id; }
    public void setNom(String nom) { this.nom = nom; }
    public void setPrix(double prix) { this.prix = prix; }
    public void setQuantite(int quantite) { this.quantite = quantite; }
    public void setFournisseurId(int fournisseurId) { this.fournisseurId = fournisseurId; }
}
