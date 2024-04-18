package tn.esprit.controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import tn.esprit.models.Voiture;

import tn.esprit.services.ServiceVoiture;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AjouterVoiture<VoitureIndex> implements Initializable {

    @FXML
    private TextField id;

    @FXML
    private DatePicker annee_fabrication;

    @FXML
    private TextField kilometrage;

    @FXML
    private TextField prix_achat;
    @FXML
    private TextField prix_actuel;
    @FXML
    private TextField marque;
    @FXML
    private TextField modele;
    @FXML
    private TextField numero_serie;
    @FXML
    private TextField type_carburant;
    @FXML
    private TextField numero_immatriculation;
    @FXML
    private TextField couleur;

    @FXML
    private TextField carte_grise;
    @FXML
    private TextField nom_image;

    @FXML
    private Button submit;

    @FXML
    void submit(ActionEvent event) {
        Voiture voiture = new Voiture();
        ServiceVoiture service = new ServiceVoiture(); // Assurez-vous d'avoir une classe ServiceVoiture pour gérer les opérations sur les voitures

        voiture.setId(Integer.parseInt(id.getText()));
        voiture.setKilometrage(Integer.parseInt(kilometrage.getText()));
        voiture.setPrix_achat(Double.parseDouble(prix_achat.getText()));
        voiture.setPrix_actuel(Double.parseDouble(prix_actuel.getText()));
        voiture.setMarque(marque.getText());
        voiture.setModele(modele.getText());
        voiture.setNumero_serie(numero_serie.getText());
        voiture.setType_carburant(type_carburant.getText());
        voiture.setNumero_immatriculation(numero_immatriculation.getText());
        voiture.setCouleur(couleur.getText());
        voiture.setCarte_grise(carte_grise.getText());
        voiture.setNom_image(nom_image.getText());

        try {
            service.add(voiture);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succès");
            alert.setHeaderText("Voiture ajoutée avec succès");
            alert.showAndWait();
        } catch(Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Échec de l'ajout de la voiture");
            alert.setContentText("Une erreur est survenue lors de l'ajout de la voiture. Veuillez réessayer.");
            alert.showAndWait();
        }
    }

    @FXML
    void goToVoitureList(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/VoitureIndex.fxml"));
        try {
            Node source = (Node) event.getSource();
            Parent root = loader.load();
            System.out.println("Fichier FXML chargé avec succès.");
            VoitureIndex controller = loader.getController();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.setTitle("Liste des Voitures");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    @FXML
    Button btn1;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
