package tn.esprit.controllers;

import javafx.scene.layout.HBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import tn.esprit.models.Panne;
import tn.esprit.services.ServicePanne;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class PanneIndex implements Initializable {

    @FXML
    private TilePane tilePane;

    private ObservableList<Panne> data = FXCollections.observableArrayList();
    private ObservableList<Panne> filteredData = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadData();
    }

    private void loadData() {
        // Récupérer les données à partir du service ou de la base de données
        ServicePanne service = new ServicePanne();
        List<Panne> allPannes = service.getAll();
        data.addAll(allPannes);
        filteredData.addAll(allPannes);

        // Afficher toutes les pannes initialement
        displayPannes(filteredData);
    }

    private void displayPannes(ObservableList<Panne> pannes) {
        tilePane.getChildren().clear();

        for (Panne panne : pannes) {
            VBox card = new VBox(10);
            card.setStyle("-fx-background-color: rgba(255,255,255,0.7); -fx-padding: 15; -fx-border-color: white; -fx-border-width: 2;");
            card.setPadding(new Insets(10));

            Label titrePanne = new Label("Panne: " + panne.getPanne()); // Assuming getNom() is correctly defined in Panne
            titrePanne.setStyle("-fx-fill: white; -fx-font-size: 18px; -fx-font-weight: bold;");

            Label detailsPanne = new Label("Description: " + panne.getDescription()); // Assuming getDescription() is defined
            detailsPanne.setStyle("-fx-fill: white; -fx-font-size: 14px;");

            HBox actions = new HBox(10);
            Button modifierButton = new Button("Modifier");
            modifierButton.setStyle("-fx-background-color: #ffc107; -fx-text-fill: white; -fx-font-size: 14px;");
            modifierButton.setOnAction(event -> modifierPanne(panne));

            Button supprimerButton = new Button("Supprimer");
            supprimerButton.setStyle("-fx-background-color: #dc3545; -fx-text-fill: white; -fx-font-size: 14px;");
            supprimerButton.setOnAction(event -> supprimerPanne(panne));

            actions.getChildren().addAll(modifierButton, supprimerButton);

            card.getChildren().addAll(titrePanne, detailsPanne, actions);
            tilePane.getChildren().add(card);
        }
    }

    @FXML
    private void goToAjouterPanne(ActionEvent event) {
        try {
            // Charger le fichier FXML de la page AjouterPanne.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AjouterPanne.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void modifierPanne(Panne panne) {
        // Implémenter la logique pour modifier la panne
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Modifier Panne");
        alert.setHeaderText(null);
        alert.setContentText("Modification de la panne: " + panne.getPanne() + " (à implémenter).");
        alert.showAndWait();
    }

    private void supprimerPanne(Panne panne) {
        // Implémenter la logique pour supprimer la panne
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Supprimer Panne");
        alert.setHeaderText(null);
        alert.setContentText("Suppression de la panne: " + panne.getPanne() + " (à implémenter).");
        alert.showAndWait();
    }
}
