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
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import tn.esprit.models.Assurance;
import tn.esprit.services.ServiceAssurance;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

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
    private AnchorPane mainAnchorPane;


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
            // Récupérer les valeurs des champs de saisie
            String nom = tfNom.getText();
            String adresse = tfAdresse.getText();
            String codePostal = tfCodePostal.getText();
            String telephone = tfTelephone.getText();
            String email = tfEmail.getText();

            // Vérifier si les champs obligatoires sont vides
            if (nom.isEmpty() || adresse.isEmpty() || codePostal.isEmpty() || telephone.isEmpty() || email.isEmpty()) {
                // Afficher un message d'erreur si un champ obligatoire est vide
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez remplir tous les champs obligatoires !");
                alert.showAndWait();
                return; // Sortir de la méthode sans ajouter l'assurance
            }

            // Vérifier le format du code postal (5 chiffres)
            if (!codePostal.matches("\\d{5}")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("Le code postal doit contenir 5 chiffres !");
                alert.showAndWait();
                return; // Sortir de la méthode sans ajouter l'assurance
            }

            // Vérifier le format du numéro de téléphone
            if (!telephone.matches("\\d{8}")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("Le numéro de téléphone doit contenir 8 chiffres !");
                alert.showAndWait();
                return; // Sortir de la méthode sans ajouter l'assurance
            }

            // Vérifier le format de l'email
            if (!email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("L'email n'est pas valide !");
                alert.showAndWait();
                return; // Sortir de la méthode sans ajouter l'assurance
            }

            // Si toutes les vérifications passent, ajouter l'assurance
            Assurance a = new Assurance();
            a.setNom_assurance(nom);
            a.setAdresse_assurance(adresse);
            a.setCode_postal_assurance(codePostal);
            a.setTel_assurance(telephone);
            a.setEmail_assurance(email);

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



