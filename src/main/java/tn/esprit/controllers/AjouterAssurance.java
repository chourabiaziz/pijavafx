package tn.esprit.controllers;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tn.esprit.models.Assurance;
import tn.esprit.services.ServiceAssurance;

import java.io.IOException;
import java.util.List;

import static javafx.application.Application.launch;


public class AjouterAssurance {
    ServiceAssurance sp  = new ServiceAssurance();

    @FXML
    private TextField tfNom;

    @FXML
    private TextField tfAdresse;

    @FXML
    private TextField tfCodePostal;

    @FXML
    private TextField tfTelephone;

    @FXML
    private TextField tfEmail;


    @FXML
    void AfficherAssurance(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherAssurance.fxml"));

        try {
            Parent root = loader.load();
            AfficherAssurance ap = loader.getController();
            List<Assurance> assurancesList = sp.getAll();
            ap.setAssurances(assurancesList);

            tfNom.getScene().setRoot(root);


        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }


    @FXML
    void ajouterAssurance(ActionEvent event) {
        try {

            Assurance a = new Assurance();

            a.setNom_assurance(tfNom.getText());
            a.setAdresse_assurance(tfAdresse.getText());
            a.setCode_postal_assurance(tfCodePostal.getText());
            a.setTel_assurance(tfTelephone.getText());
            a.setEmail_assurance(tfEmail.getText());

            sp.add(a);
            System.out.println("Assurance ajoutée avec succès !");

            // Afficher une boîte de dialogue de succès
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succès");
            alert.setHeaderText(null);
            alert.setContentText("L'ajout s'est fait avec succès !");
            alert.showAndWait();


        } catch (Exception e) {
            System.err.println("Erreur lors de l'ajout de l'assurance: " + e.getMessage());
        }

    }


}



