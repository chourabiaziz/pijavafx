package tn.esprit.models;


public class Constat {
    private  String a_preneur_nom, a_preneur_prenom, a_preneur_tel, a_vehicule_moteur_marque, a_vehicule_moteur_num_immatriculation , a_societe_assurance_agence_nom , a_societe_assurance_agence_adresse ;
    private  String b_preneur_nom, b_preneur_prenom, b_preneur_tel, b_vehicule_moteur_marque, b_vehicule_moteur_num_immatriculation , b_societe_assurance_agence_nom ,b_societe_assurance_agence_adresse;
    private String localisation ,temoins ;
    private boolean stationnement_arret ,quittait_stationnement_arret ,prenait_stationnement,sortait_dun_parking_lieu,doublait,virait_droite,virait_gauche ;
    private String image;
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

    public String getA_societe_assurance_agence_adresse() {
        return a_societe_assurance_agence_adresse;
    }

    public void setA_societe_assurance_agence_adresse(String a_societe_assurance_agence_adresse) {
        this.a_societe_assurance_agence_adresse = a_societe_assurance_agence_adresse;
    }

    public String getB_preneur_nom() {
        return b_preneur_nom;
    }

    public void setB_preneur_nom(String b_preneur_nom) {
        this.b_preneur_nom = b_preneur_nom;
    }

    public String getB_preneur_prenom() {
        return b_preneur_prenom;
    }

    public void setB_preneur_prenom(String b_preneur_prenom) {
        this.b_preneur_prenom = b_preneur_prenom;
    }

    public String getB_preneur_tel() {
        return b_preneur_tel;
    }

    public void setB_preneur_tel(String b_preneur_tel) {
        this.b_preneur_tel = b_preneur_tel;
    }

    public String getB_vehicule_moteur_marque() {
        return b_vehicule_moteur_marque;
    }

    public void setB_vehicule_moteur_marque(String b_vehicule_moteur_marque) {
        this.b_vehicule_moteur_marque = b_vehicule_moteur_marque;
    }

    public String getB_vehicule_moteur_num_immatriculation() {
        return b_vehicule_moteur_num_immatriculation;
    }

    public void setB_vehicule_moteur_num_immatriculation(String b_vehicule_moteur_num_immatriculation) {
        this.b_vehicule_moteur_num_immatriculation = b_vehicule_moteur_num_immatriculation;
    }

    public String getB_societe_assurance_agence_nom() {
        return b_societe_assurance_agence_nom;
    }

    public void setB_societe_assurance_agence_nom(String b_societe_assurance_agence_nom) {
        this.b_societe_assurance_agence_nom = b_societe_assurance_agence_nom;
    }

    public String getB_societe_assurance_agence_adresse() {
        return b_societe_assurance_agence_adresse;
    }

    public void setB_societe_assurance_agence_adresse(String b_societe_assurance_agence_adresse) {
        this.b_societe_assurance_agence_adresse = b_societe_assurance_agence_adresse;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public String getTemoins() {
        return temoins;
    }

    public void setTemoins(String temoins) {
        this.temoins = temoins;
    }

    public boolean isStationnement_arret() {
        return stationnement_arret;
    }

    public void setStationnement_arret(boolean stationnement_arret) {
        this.stationnement_arret = stationnement_arret;
    }

    public boolean isQuittait_stationnement_arret() {
        return quittait_stationnement_arret;
    }

    public void setQuittait_stationnement_arret(boolean quittait_stationnement_arret) {
        this.quittait_stationnement_arret = quittait_stationnement_arret;
    }

    public boolean isPrenait_stationnement() {
        return prenait_stationnement;
    }

    public void setPrenait_stationnement(boolean prenait_stationnement) {
        this.prenait_stationnement = prenait_stationnement;
    }

    public boolean isSortait_dun_parking_lieu() {
        return sortait_dun_parking_lieu;
    }

    public void setSortait_dun_parking_lieu(boolean sortait_dun_parking_lieu) {
        this.sortait_dun_parking_lieu = sortait_dun_parking_lieu;
    }

    public boolean isDoublait() {
        return doublait;
    }

    public void setDoublait(boolean doublait) {
        this.doublait = doublait;
    }

    public boolean isVirait_droite() {
        return virait_droite;
    }

    public void setVirait_droite(boolean virait_droite) {
        this.virait_droite = virait_droite;
    }

    public boolean isVirait_gauche() {
        return virait_gauche;
    }

    public void setVirait_gauche(boolean virait_gauche) {
        this.virait_gauche = virait_gauche;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Constat(int id, String a_preneur_nom, String a_preneur_prenom, String a_preneur_tel, String  a_vehicule_moteur_marque, String a_vehicule_moteur_num_immatriculation , String a_societe_assurance_agence_nom,String a_societe_assurance_agence_adresse,
                   String b_preneur_nom, String b_preneur_prenom, String b_preneur_tel, String  b_vehicule_moteur_marque, String b_vehicule_moteur_num_immatriculation , String b_societe_assurance_agence_nom , String b_societe_assurance_agence_adresse,
                     String localisation, String temoins,
                          boolean stationnement_arret, boolean quittait_stationnement_arret , boolean prenait_stationnement, boolean sortait_dun_parking_lieu, boolean doublait, boolean virait_droite, boolean virait_gauche

                   ) {
        this.id = id;
        this.a_preneur_nom = a_preneur_nom;
        this.a_preneur_prenom = a_preneur_prenom;
        this.a_preneur_tel = a_preneur_tel;
        this.a_vehicule_moteur_marque = a_vehicule_moteur_marque;
        this.a_vehicule_moteur_num_immatriculation = a_vehicule_moteur_num_immatriculation;
        this.a_societe_assurance_agence_nom = a_societe_assurance_agence_nom;
        this.a_societe_assurance_agence_adresse = a_societe_assurance_agence_adresse;

        this.b_preneur_nom = b_preneur_nom;
        this.b_preneur_prenom = b_preneur_prenom;
        this.b_preneur_tel = b_preneur_tel;
        this.b_vehicule_moteur_marque = b_vehicule_moteur_marque;
        this.b_vehicule_moteur_num_immatriculation = b_vehicule_moteur_num_immatriculation;
        this.b_societe_assurance_agence_nom = b_societe_assurance_agence_nom;
        this.b_societe_assurance_agence_adresse = b_societe_assurance_agence_adresse;

        this.localisation =localisation ;
        this.temoins =temoins ;

        this.stationnement_arret= stationnement_arret ;
        this.quittait_stationnement_arret = quittait_stationnement_arret ;
        this.prenait_stationnement = prenait_stationnement ;
        this.sortait_dun_parking_lieu = sortait_dun_parking_lieu ;
        this.doublait =doublait ;
        this.virait_droite=virait_droite ;
        this.virait_gauche =virait_gauche ;


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
                ", a_societe_assurance_agence_adresse='" + a_societe_assurance_agence_adresse + '\'' +

                ", b_preneur_nom=" + b_preneur_nom +
                ", b_preneur_prenom='" + b_preneur_prenom + '\'' +
                ", b_preneur_tel='" + b_preneur_tel + '\'' +
                ", b_vehicule_moteur_marque='" + b_vehicule_moteur_marque + '\'' +
                ", b_vehicule_moteur_num_immatriculation='" + b_vehicule_moteur_num_immatriculation + '\'' +
                ", b_societe_assurance_agence_nom='" + b_societe_assurance_agence_nom + '\'' +
                ", b_societe_assurance_agence_adresse='" + b_societe_assurance_agence_adresse + '\'' +

                ",localisation"+ localisation +
                ",temoins" + temoins +

                ",stationnement_arret"+ stationnement_arret +
                ",quittait_stationnement_arret"+ quittait_stationnement_arret +
                ",prenait_stationnement" + prenait_stationnement +
                ",sortait_dun_parking_lieu"+ sortait_dun_parking_lieu +
                ",doublait"+ doublait +
                ",virait_droite" + virait_droite +
                ",virait_gauche" + virait_gauche +
                "}\n";
    }
}
