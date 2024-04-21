package tn.esprit.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import tn.esprit.models.Constat;
import tn.esprit.services.ServiceConstat;

import java.util.List;

public class AfficherConstat {

    @FXML
    private TextField tfNomPreneurA;

    @FXML
    private TextField tfPrenomPreneurA;

    @FXML
    private TextField tfTelPreneurA;

    @FXML
    private TextField tfMarquePreneurA;

    @FXML
    private TextField tfImmatriculationPreneurA;

    @FXML
    private TextField tfNomSocieteA;

    @FXML
    private TextField tfAdresseSocieteA;

    @FXML
    private CheckBox enStationnement;

    @FXML
    private CheckBox quittaitStationnement;

    @FXML
    private CheckBox prenaitStationnement;

    @FXML
    private CheckBox sortaitParking;

    @FXML
    private CheckBox doublait;

    @FXML
    private CheckBox viraitDroite;

    @FXML
    private CheckBox viraitGauche;

    @FXML
    private TextField tfNomPreneurB;

    @FXML
    private TextField tfPrenomPreneurB;

    @FXML
    private TextField tfTelPreneurB;

    @FXML
    private TextField tfMarquePreneurB;

    @FXML
    private TextField tfImmatriculationPreneurB;

    @FXML
    private TextField tfNomSocieteB;

    @FXML
    private TextField tfAdresseSocieteB;

    @FXML
    private TextField tfLocalisation;

    @FXML
    private TextField tfTemoins;

    private ServiceConstat serviceConstat = new ServiceConstat();

    @FXML
    public void initialize() {
        afficherConstats();
    }

    public void afficherConstats() {
        List<Constat> constats = serviceConstat.getAll(); // Assurez-vous d'avoir une méthode dans ServiceConstat pour récupérer tous les constats

        if (constats.isEmpty()) {
            return;
        }

        // Supposons que vous voulez afficher uniquement le premier constat de la liste (index 0)
        Constat constat = constats.get(0);

        // Remplissez les champs avec les données du constat
        tfNomPreneurA.setText(constat.getA_preneur_nom());
        tfPrenomPreneurA.setText(constat.getA_preneur_prenom());
        tfTelPreneurA.setText(constat.getA_preneur_tel());
        tfMarquePreneurA.setText(constat.getA_vehicule_moteur_marque());
        tfImmatriculationPreneurA.setText(constat.getA_vehicule_moteur_num_immatriculation());
        tfNomSocieteA.setText(constat.getA_societe_assurance_agence_nom());
        tfAdresseSocieteA.setText(constat.getA_societe_assurance_agence_adresse());
        // Remplissez le reste des champs de la même manière

        // Assurez-vous de définir l'état des CheckBox également
        enStationnement.setSelected(constat.isStationnement_arret());
        quittaitStationnement.setSelected(constat.isQuittait_stationnement_arret());
        prenaitStationnement.setSelected(constat.isPrenait_stationnement());
        sortaitParking.setSelected(constat.isSortait_dun_parking_lieu());
        doublait.setSelected(constat.isDoublait());
        viraitDroite.setSelected(constat.isVirait_droite());
        viraitGauche.setSelected(constat.isVirait_gauche());

        tfLocalisation.setText(constat.getLocalisation());
        tfTemoins.setText(constat.getTemoins());
    }
}
