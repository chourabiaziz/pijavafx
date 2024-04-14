package tn.esprit.models;


public class Constat {
    private  String a_preneur_nom, a_preneur_prenom, a_preneur_tel, a_vehicule_moteur_marque, a_vehicule_moteur_num_immatriculation , a_societe_assurance_agence_nom;
    private int id ;

    public Constat() {
    }

    public String getA_preneur_nom() {
        return a_preneur_nom;
    }

    public void setA_preneur_nom(String a_preneur_nom) {
        this.a_preneur_nom = a_preneur_nom;
    }

    public String getA_preneur_prenom() {
        return a_preneur_prenom;
    }

    public void setA_preneur_prenom(String a_preneur_prenom) {
        this.a_preneur_prenom = a_preneur_prenom;
    }

    public String getA_preneur_tel() {
        return a_preneur_tel;
    }

    public void setA_preneur_tel(String a_preneur_tel) {
        this.a_preneur_tel = a_preneur_tel;
    }

    public String getA_vehicule_moteur_marque() {
        return a_vehicule_moteur_marque;
    }

    public void setA_vehicule_moteur_marque(String a_vehicule_moteur_marque) {
        this.a_vehicule_moteur_marque = a_vehicule_moteur_marque;
    }

    public String getA_vehicule_moteur_num_immatriculation() {
        return a_vehicule_moteur_num_immatriculation;
    }

    public void setA_vehicule_moteur_num_immatriculation(String a_vehicule_moteur_num_immatriculation) {
        this.a_vehicule_moteur_num_immatriculation = a_vehicule_moteur_num_immatriculation;
    }

    public String getA_societe_assurance_agence_nom() {
        return a_societe_assurance_agence_nom;
    }

    public void setA_societe_assurance_agence_nom(String a_societe_assurance_agence_nom) {
        this.a_societe_assurance_agence_nom = a_societe_assurance_agence_nom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Constat(int id, String a_preneur_nom, String a_preneur_prenom, String a_preneur_tel, String  a_vehicule_moteur_marque, String a_vehicule_moteur_num_immatriculation , String a_societe_assurance_agence_nom) {
        this.id = id;
        this.a_preneur_nom = a_preneur_nom;
        this.a_preneur_prenom = a_preneur_prenom;
        this.a_preneur_tel = a_preneur_tel;
        this.a_vehicule_moteur_marque = a_vehicule_moteur_marque;
        this.a_vehicule_moteur_num_immatriculation = a_vehicule_moteur_num_immatriculation;
        this.a_societe_assurance_agence_nom = a_societe_assurance_agence_nom;

    }


    @Override
    public String toString() {
        return "Constat{" +
                "id=" + id +
                ", a_preneur_nom=" + a_preneur_nom +
                ", a_preneur_prenom='" + a_preneur_prenom + '\'' +
                ", a_preneur_tel='" + a_preneur_tel + '\'' +
                ", a_vehicule_moteur_marque='" + a_vehicule_moteur_marque + '\'' +
                ", a_vehicule_moteur_num_immatriculation='" + a_vehicule_moteur_num_immatriculation + '\'' +
                ", a_societe_assurance_agence_nom='" + a_societe_assurance_agence_nom + '\'' +

                "}\n";
    }
}
