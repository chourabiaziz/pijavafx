package tn.esprit.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tn.esprit.models.Assurance;
import tn.esprit.models.Constat;
import tn.esprit.services.ServiceAssurance;
import tn.esprit.services.ServiceConstat;


import javax.swing.*;

public class ModifierConstat {


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

    private ServiceConstat serviceConstat;

    @FXML
    private Button retourButton;
    private Constat constat;



    public ModifierConstat() {
        this.serviceConstat = new ServiceConstat();
    }


    public void initialize() {
        int constatId = 17 ;
        constat = serviceConstat.getById(constatId);

        tfNomPreneurA.setText(constat.getA_preneur_nom());
        tfPrenomPreneurA.setText(constat.getA_preneur_prenom());
        tfTelPreneurA.setText(constat.getA_preneur_tel());
        tfMarquePreneurA.setText(constat.getA_vehicule_moteur_marque());
        tfImmatriculationPreneurA.setText(constat.getA_vehicule_moteur_num_immatriculation());
        tfNomSocieteA.setText(constat.getA_societe_assurance_agence_nom());
        tfAdresseSocieteA.setText(constat.getA_societe_assurance_agence_adresse());

        tfNomPreneurB.setText(constat.getB_preneur_nom());
        tfPrenomPreneurB.setText(constat.getB_preneur_prenom());
        tfTelPreneurB.setText(constat.getB_preneur_tel());
        tfMarquePreneurB.setText(constat.getB_vehicule_moteur_marque());
        tfImmatriculationPreneurB.setText(constat.getB_vehicule_moteur_num_immatriculation());
        tfNomSocieteB.setText(constat.getB_societe_assurance_agence_nom());
        tfAdresseSocieteB.setText(constat.getB_societe_assurance_agence_adresse());

        enStationnement.setSelected(constat.isStationnement_arret());
        quittaitStationnement.setSelected(constat.isQuittait_stationnement_arret());
        prenaitStationnement.setSelected(constat.isPrenait_stationnement());
        sortaitParking.setSelected(constat.isSortait_dun_parking_lieu());
        doublait.setSelected(constat.isDoublait());
        viraitDroite.setSelected(constat.isVirait_droite());
        viraitGauche.setSelected(constat.isVirait_gauche());

    }



        private void showAlert(Alert.AlertType alertType, String title, String headerText, String contentText) {
            Alert alert = new Alert(alertType);
            alert.setTitle(title);
            alert.setHeaderText(headerText);
            alert.setContentText(contentText);
            alert.showAndWait();
        }

    public void retourner(javafx.event.ActionEvent actionEvent) {
        try {
            // Charger le fichier FXML de la page AfficherAssurance.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AjouterConstat.fxml"));
            Parent root = loader.load();

            // Créer une nouvelle scène avec le contenu chargé
            Scene scene = new Scene(root);

            // Obtenir la fenêtre actuelle et la fermer
            Stage stage = (Stage) retourButton.getScene().getWindow();
            stage.close();
            // Afficher la nouvelle scène
            Stage primaryStage = new Stage();
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void handleEnregistrerButtonAction(javafx.event.ActionEvent actionEvent) {
        String nomPreneurA = tfNomPreneurA.getText();
        String prenomPreneurA = tfPrenomPreneurA.getText();
        String telPreneurA = tfTelPreneurA.getText();
        String marqueA = tfMarquePreneurA.getText();
        String immatriculationA = tfImmatriculationPreneurA.getText();
        String nomSocieteA = tfNomSocieteA.getText();
        String adresseSocieteA = tfAdresseSocieteA.getText();

        String nomPreneurB = tfNomPreneurB.getText();
        String prenomPreneurB = tfPrenomPreneurB.getText();
        String telPreneurB = tfTelPreneurB.getText();
        String marqueB = tfMarquePreneurB.getText();
        String immatriculationB = tfImmatriculationPreneurB.getText();
        String nomSocieteB = tfNomSocieteB.getText();
        String adresseSocieteB = tfAdresseSocieteB.getText();

        String localisation = tfLocalisation.getText();
        String temoins = tfTemoins.getText();

        boolean enStationnementChecked = enStationnement.isSelected();
        boolean quittaitStationnementChecked = quittaitStationnement.isSelected();
        boolean prenaitStationnementChecked = prenaitStationnement.isSelected();
        boolean sortaitParkingChecked = sortaitParking.isSelected();
        boolean doublaitChecked = doublait.isSelected();
        boolean viraitDroiteChecked = viraitDroite.isSelected();
        boolean viraitGaucheChecked = viraitGauche.isSelected();

        // Mettre à jour l'objet Constat avec les nouvelles données
        constat.setA_preneur_nom(nomPreneurA);
        constat.setA_preneur_prenom(prenomPreneurA);
        constat.setA_preneur_tel(telPreneurA);
        constat.setA_vehicule_moteur_marque(marqueA);
        constat.setA_vehicule_moteur_num_immatriculation(immatriculationA);
        constat.setA_societe_assurance_agence_nom(nomSocieteA);
        constat.setA_societe_assurance_agence_adresse(adresseSocieteA);

        constat.setB_preneur_nom(nomPreneurB);
        constat.setB_preneur_prenom(prenomPreneurB);
        constat.setB_preneur_tel(telPreneurB);
        constat.setB_vehicule_moteur_marque(marqueB);
        constat.setB_vehicule_moteur_num_immatriculation(immatriculationB);
        constat.setB_societe_assurance_agence_nom(nomSocieteB);
        constat.setB_societe_assurance_agence_adresse(adresseSocieteB);

        constat.setLocalisation(localisation);
        constat.setTemoins(temoins);

        constat.setStationnement_arret(enStationnementChecked);
        constat.setQuittait_stationnement_arret(quittaitStationnementChecked);
        constat.setPrenait_stationnement(prenaitStationnementChecked);
        constat.setSortait_dun_parking_lieu(sortaitParkingChecked);
        constat.setDoublait(doublaitChecked);
        constat.setVirait_droite(viraitDroiteChecked);
        constat.setVirait_gauche(viraitGaucheChecked);


        // Appelez la méthode update de ServiceConstat pour mettre à jour les données dans la base de données
        boolean updateSuccess = serviceConstat.update(constat);

        if (updateSuccess) {
            showAlert(Alert.AlertType.INFORMATION, "Succès", "Modification réussie", "La modification de constat a été effectuée avec succès.");
        } else {
            showAlert(Alert.AlertType.ERROR, "Erreur", "Échec de la modification", "La modification de constat a échoué.");
        }

    }



    public void setConstatId(int id) {
        this.constat = serviceConstat.getById(id);
        if (constat == null) {
            throw new IllegalArgumentException("Constat with ID " + id + " not found.");
        }
    }





}












