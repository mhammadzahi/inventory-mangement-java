package org.example.model;

public class Produit {
    private int id;
    private String nom;
    private double prix;
    private int quantite;
    private int idFournisseur;

    public Produit() {}

    public Produit(String nom, double prix, int quantite, int idFournisseur) {
        this.nom = nom;
        this.prix = prix;
        this.quantite = quantite;
        this.idFournisseur = idFournisseur;
    }

    public Produit(int id, String nom, double prix, int quantite, int idFournisseur) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
        this.quantite = quantite;
        this.idFournisseur = idFournisseur;
    }

    // Getters and setters
    public int getId() { return id; }
    public String getNom() { return nom; }
    public double getPrix() { return prix; }
    public int getQuantite() { return quantite; }
    public int getIdFournisseur() { return idFournisseur; }

    public void setId(int id) { this.id = id; }
    public void setNom(String nom) { this.nom = nom; }
    public void setPrix(double prix) { this.prix = prix; }
    public void setQuantite(int quantite) { this.quantite = quantite; }
    public void setIdFournisseur(int idFournisseur) { this.idFournisseur = idFournisseur; }
}
