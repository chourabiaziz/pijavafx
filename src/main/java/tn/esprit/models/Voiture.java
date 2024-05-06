package tn.esprit.models;

import java.util.Date;


public class Voiture {
    private int id;

    private int kilometrage;

//
    private Panne panne;

    public Panne getPanne() {
        return panne;
    }

    public void setPanne(Panne panne) {
        this.panne = panne;
    }

    private double prix_achat, prix_actuel;
    private String marque, modele, numero_serie, type_carburant, numero_immatriculation, couleur, carte_grise, nom_image;
    private Date date_achat;

    public Voiture(int id, int kilometrage, Panne panne, double prix_achat, double prix_actuel, String marque, String modele, String numero_serie, String type_carburant, String numero_immatriculation, String couleur, String carte_grise, String nom_image, Date date_achat) {
        this.id = id;
        this.kilometrage = kilometrage;
        this.panne = panne;
        this.prix_achat = prix_achat;
        this.prix_actuel = prix_actuel;
        this.marque = marque;
        this.modele = modele;
        this.numero_serie = numero_serie;
        this.type_carburant = type_carburant;
        this.numero_immatriculation = numero_immatriculation;
        this.couleur = couleur;
        this.carte_grise = carte_grise;
        this.nom_image = nom_image;
        this.date_achat = date_achat;
    }

    public Voiture() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getKilometrage() {
        return kilometrage;
    }

    public void setKilometrage(int kilometrage) {
        this.kilometrage = kilometrage;
    }

    public double getPrix_achat() {
        return prix_achat;
    }

    public void setPrix_achat(double prix_achat) {
        this.prix_achat = prix_achat;
    }

    public double getPrix_actuel() {
        return prix_actuel;
    }

    public void setPrix_actuel(double prix_actuel) {
        this.prix_actuel = prix_actuel;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public String getNumero_serie() {
        return numero_serie;
    }

    public void setNumero_serie(String numero_serie) {
        this.numero_serie = numero_serie;
    }

    public String getType_carburant() {
        return type_carburant;
    }

    public void setType_carburant(String type_carburant) {
        this.type_carburant = type_carburant;
    }

    public String getNumero_immatriculation() {
        return numero_immatriculation;
    }

    public void setNumero_immatriculation(String numero_immatriculation) {
        this.numero_immatriculation = numero_immatriculation;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public String getCarte_grise() {
        return carte_grise;
    }

    public void setCarte_grise(String carte_grise) {
        this.carte_grise = carte_grise;
    }

    public String getNom_image() {
        return nom_image;
    }

    public void setNom_image(String nom_image) {
        this.nom_image = nom_image;
    }

    public Date getDate_achat() {
        return date_achat;
    }

    public void setDate_achat(Date date_achat) {
        this.date_achat = date_achat;
    }
}
