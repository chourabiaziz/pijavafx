package tn.esprit.navigation;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tn.esprit.controllers.AjouterVoiture;
import tn.esprit.controllers.PanneIndex;
import tn.esprit.controllers.AtelierIndex;

import java.io.IOException;

public class Navigation {

    public void goToAjouterVoiture(ActionEvent event) {
        loadAndChangeScene(event, "/AjouterVoiture.fxml", "Ajouter une Voiture");
    }

    public void goToListePannes(ActionEvent event) {
        loadAndChangeScene(event, "/ListePannes.fxml", "Liste des Pannes");
    }

    public void goToListeAteliers(ActionEvent event) {
        loadAndChangeScene(event, "/ListeAteliers.fxml", "Liste des Ateliers");
    }

    private void loadAndChangeScene(ActionEvent event, String fxmlPath, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle(title);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException("Erreur lors du chargement du fichier FXML", e);
        }
    }
    public void goToAddVoiture(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/tn/esprit/views/VoitureAdd.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

    public void goToAddVoiture(java.awt.event.ActionEvent event) {
    }
}
