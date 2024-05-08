package tn.esprit.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import tn.esprit.models.Atelier;
import tn.esprit.services.ServiceAtelier;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AtelierIndexAdmin implements Initializable {

    @FXML
    private TilePane tilePane;

    @FXML
    private TextField searchField;

    @FXML
    private Button ajouterButton;

    private ObservableList<Atelier> data = FXCollections.observableArrayList();
    private ObservableList<Atelier> filteredData = FXCollections.observableArrayList();

    private final ServiceAtelier serviceAtelier = new ServiceAtelier();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ajouterButton.setOnAction(event -> {
            try {
                // Charger le fichier FXML de la page AjouterAtelier.fxml
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/ajouterAtelier.fxml"));
                Parent root = loader.load();
                // Créer une nouvelle scène
                Scene scene = new Scene(root);
                // Obtenir la fenêtre actuelle
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                // Définir la nouvelle scène sur la fenêtre et l'afficher
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        loadData();
    }

    private void loadData() {
        // Charger les ateliers depuis le service
        List<Atelier> allAteliers = serviceAtelier.getAll();
        data.addAll(allAteliers);
        filteredData.addAll(allAteliers);

        // Afficher toutes les ateliers initialement
        displayAteliers(filteredData);
    }

    private void displayAteliers(ObservableList<Atelier> ateliers) {
        // Effacer le contenu du TilePane
        tilePane.getChildren().clear();

        String labelStyle = "-fx-text-fill: white;";

        for (Atelier atelier : ateliers) {
            VBox card = new VBox();
            card.setPrefWidth(200);
            card.setPrefHeight(180);
            card.setSpacing(5);
            card.setPadding(new Insets(5));
            card.setStyle("-fx-background-color: rgba(0, 0, 0, 0.7); -fx-border-color: white; -fx-border-width: 2;");

            Label nomLabel = new Label("Nom: " + atelier.getNom());
            nomLabel.setStyle(labelStyle);
            Label adresseLabel = new Label("Adresse: " + atelier.getAdresse());
            adresseLabel.setStyle(labelStyle);
            Label telephoneLabel = new Label("Téléphone: " + atelier.getNumero_telephone());
            telephoneLabel.setStyle(labelStyle);
            Label specialiteLabel = new Label("Spécialité: " + atelier.getSpecialite());
            specialiteLabel.setStyle(labelStyle);
            Label avisLabel = new Label("Avis: " + atelier.getAvis());
            avisLabel.setStyle(labelStyle);
            Label ouvertureLabel = new Label("Heure d'Ouverture: " + atelier.getHeure_overture());
            ouvertureLabel.setStyle(labelStyle);
            Label fermetureLabel = new Label("Heure de Fermeture: " + atelier.getHeure_fermeture());
            fermetureLabel.setStyle(labelStyle);

            // Ajouter les labels à la carte
            card.getChildren().addAll(nomLabel, adresseLabel, telephoneLabel, specialiteLabel, avisLabel, ouvertureLabel, fermetureLabel);

            // Ajouter la carte au TilePane
            tilePane.getChildren().add(card);
        }
    }

    @FXML
    private void searchFieldChanged() {
        String query = searchField.getText().trim().toLowerCase();

        filteredData.clear(); // Vider les données filtrées

        if (query.isEmpty()) {
            displayAteliers(data);
        } else {
            for (Atelier atelier : data) {
                if (atelier.getNom().toLowerCase().contains(query)) {
                    filteredData.add(atelier); // Ajouter les ateliers correspondants aux données filtrées
                }
            }
            displayAteliers(filteredData); // Afficher les données filtrées
        }
    }

    public void modifierAtelier(ActionEvent actionEvent) {
    }

    public void supprimerAtelier(ActionEvent actionEvent) {
    }
}
