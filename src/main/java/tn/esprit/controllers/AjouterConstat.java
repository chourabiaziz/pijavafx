package tn.esprit.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tn.esprit.models.Assurance;
import tn.esprit.models.Constat;
import tn.esprit.services.ServiceAssurance;
import tn.esprit.services.ServiceConstat;

import java.io.IOException;

public class AjouterConstat {

    ServiceConstat sp  = new ServiceConstat();


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



    @FXML
    void ajouterConstat(ActionEvent event) {

        try {

            // Récupérer les données saisies dans les TextField

            // Récupérer les données saisies dans les TextField
            String nomPreneurA = tfNomPreneurA.getText();
            String prenomPreneurA = tfPrenomPreneurA.getText();
            String telPreneurA = tfTelPreneurA.getText();
            String marquePreneurA = tfMarquePreneurA.getText();
            String immatriculationPreneurA = tfImmatriculationPreneurA.getText();
            String nomSocieteA = tfNomSocieteA.getText();
            String adresseSocieteA = tfAdresseSocieteA.getText();

            String nomPreneurB = tfNomPreneurB.getText();
            String prenomPreneurB = tfPrenomPreneurB.getText();
            String telPreneurB = tfTelPreneurB.getText();
            String marquePreneurB = tfMarquePreneurB.getText();
            String immatriculationPreneurB = tfImmatriculationPreneurB.getText();
            String nomSocieteB = tfNomSocieteB.getText();
            String adresseSocieteB = tfAdresseSocieteB.getText();

            // Récupérer les valeurs des CheckBox
            boolean enStationnementValue = enStationnement.isSelected();
            boolean quittaitStationnementValue = quittaitStationnement.isSelected();
            boolean prenaitStationnementValue = prenaitStationnement.isSelected();
            boolean sortaitParkingValue = sortaitParking.isSelected();
            boolean doublaitValue = doublait.isSelected();
            boolean viraitDroiteValue = viraitDroite.isSelected();
            boolean viraitGaucheValue = viraitGauche.isSelected();

            String localisationText = tfLocalisation.getText();
            String temoinsText = tfTemoins.getText();


            Constat c = new Constat();

            c.setA_preneur_nom(tfNomPreneurA.getText());
            c.setA_preneur_prenom(tfPrenomPreneurA.getText());
            c.setA_preneur_tel(tfTelPreneurA.getText());
            c.setA_vehicule_moteur_marque(tfMarquePreneurA.getText());
            c.setA_vehicule_moteur_num_immatriculation(tfImmatriculationPreneurA.getText());
            c.setA_societe_assurance_agence_nom(tfNomSocieteA.getText());
            c.setA_societe_assurance_agence_adresse(tfAdresseSocieteA.getText());


            c.setB_preneur_nom(tfNomPreneurB.getText());
            c.setB_preneur_prenom(tfPrenomPreneurB.getText());
            c.setB_preneur_tel(tfTelPreneurB.getText());
            c.setB_vehicule_moteur_marque(tfMarquePreneurB.getText());
            c.setB_vehicule_moteur_num_immatriculation(tfImmatriculationPreneurB.getText());
            c.setB_societe_assurance_agence_nom(tfNomSocieteB.getText());
            c.setB_societe_assurance_agence_adresse(tfAdresseSocieteB.getText());


            c.setLocalisation(tfLocalisation.getText());
            c.setTemoins(tfTemoins.getText());


            c.setStationnement_arret(enStationnement.isSelected());
            c.setQuittait_stationnement_arret(quittaitStationnement.isSelected());
            c.setPrenait_stationnement(prenaitStationnement.isSelected());
            c.setSortait_dun_parking_lieu(sortaitParking.isSelected());
            c.setDoublait(doublait.isSelected());
            c.setVirait_droite(viraitDroite.isSelected());
            c.setVirait_gauche(viraitDroite.isSelected());

            sp.add(c);
            System.out.println("Constat ajoutée avec succès !");


           // Rediriger vers la page AfficherAssurance
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherConstat.fxml"));
            Parent root = loader.load();

                // Get the controller of the AfficherConstat.fxml file
            AfficherConstat controller = loader.getController();
             tfNomPreneurA.getScene().setRoot(root);



        } catch (Exception e) {
            System.err.println("Erreur lors de l'ajout de constat: " + e.getMessage());
        }

    }







}
