package tn.esprit;


import tn.esprit.models.Assurance;
import tn.esprit.models.Constat;
import tn.esprit.services.ServiceAssurance;
import tn.esprit.services.ServiceConstat;


public class Main {
    public static void main(String[] args) {
        Assurance a = new Assurance(4,"Gprod","tunis lac 1","2011","7321544","grod@gmail.com");
        Constat c = new Constat(
                1, // id
                "Jean", // a_preneur_nom
                "Dupont", // a_preneur_prenom
                "123456789", // a_preneur_tel
                "Toyota", // a_vehicule_moteur_marque
                "123ABC", // a_vehicule_moteur_num_immatriculation
                "AssuranceCompanyA", // a_societe_assurance_agence_nom
                "123 Rue de l'Assurance", // a_societe_assurance_agence_adresse
                "Paul", // b_preneur_nom
                "Durand", // b_preneur_prenom
                "987654321", // b_preneur_tel
                "Honda", // b_vehicule_moteur_marque
                "456DEF", // b_vehicule_moteur_num_immatriculation
                "AssuranceCompanyB", // b_societe_assurance_agence_nom
                "456 Rue de l'Assurance", // b_societe_assurance_agence_adresse
                "Paris", // localisation
                "Aucun témoin", // temoins
                true, // stationnement_arret
                false, // quittait_stationnement_arret
                true, // prenait_stationnement
                false, // sortait_dun_parking_lieu
                true, // doublait
                false, // virait_droite
                true // virait_gauche



        ) ;


        ServiceAssurance sa = new ServiceAssurance();
        ServiceConstat sc = new ServiceConstat();
        sa.add(a);
        sc.add(c);



        System.out.println(sa.getAll());
        System.out.println(sc.getAll());
    }
}