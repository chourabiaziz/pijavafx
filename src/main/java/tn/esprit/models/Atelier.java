package tn.esprit.models;

import java.sql.Time;
import java.util.Objects;

public class Atelier {
    private int id;
    private String nom, adresse, numero_telephone, specialite, avis;
    private Time 		heure_overture, heure_fermeture;

    public Atelier() {
    }

    public Atelier(int id, String nom, String adresse, String numero_telephone, String specialite, String avis, Time heure_ouverture, Time heure_fermeture) {
        this.id = id;
        this.nom = nom;
        this.adresse = adresse;
        this.numero_telephone = numero_telephone;
        this.specialite = specialite;
        this.avis = avis;
        this.		heure_overture = 		heure_overture;
        this.heure_fermeture = heure_fermeture;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getNumero_telephone() {
        return numero_telephone;
    }

    public String getSpecialite() {
        return specialite;
    }

    public String getAvis() {
        return avis;
    }

    public Time getHeure_overture() {
        return 		heure_overture;
    }

    public Time getHeure_fermeture() {
        return heure_fermeture;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setNumero_telephone(String numero_telephone) {
        this.numero_telephone = numero_telephone;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public void setAvis(String avis) {
        this.avis = avis;
    }

    public void setHeure_overture(Time heure_ouverture) {
        this.		heure_overture = heure_ouverture;
    }

    public void setHeure_fermeture(Time heure_fermeture) {
        this.heure_fermeture = heure_fermeture;
    }

    // hashCode
    @Override
    public int hashCode() {
        return Objects.hash(id, nom, adresse, numero_telephone, specialite, avis, 		heure_overture, heure_fermeture);
    }

    // toString
    @Override
    public String toString() {
        return "Atelier{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", adresse='" + adresse + '\'' +
                ", numero_telephone='" + numero_telephone + '\'' +
                ", specialite='" + specialite + '\'' +
                ", avis='" + avis + '\'' +
                ", =" + 		heure_overture +
                ", heure_fermeture=" + heure_fermeture +
                '}';
    }

    // equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Atelier atelier = (Atelier) o;
        return id == atelier.id &&
                Objects.equals(nom, atelier.nom) &&
                Objects.equals(adresse, atelier.adresse) &&
                Objects.equals(numero_telephone, atelier.numero_telephone) &&
                Objects.equals(specialite, atelier.specialite) &&
                Objects.equals(avis, atelier.avis) &&
                Objects.equals(		heure_overture, atelier.		heure_overture) &&
                Objects.equals(heure_fermeture, atelier.heure_fermeture);
    }
}
