package tn.esprit.navigation;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Navigation {

    public void goToAjouterVoiture(ActionEvent event) {
        chargerEtChangerScene(event, "/AjouterVoiture.fxml", "Ajouter une Voiture");
    }

    public void goToListePannes(ActionEvent event) {
        chargerEtChangerScene(event, "/ListePannes.fxml", "Liste des Pannes");
    }

    public void goToListeAteliers(ActionEvent event) {
        chargerEtChangerScene(event, "/ListeAteliers.fxml", "Liste des Ateliers");
    }

    public void goToAddVoiture(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/tn/esprit/views/VoitureAdd.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
        ((Node) event.getSource()).getScene().getWindow().hide();
    }

    private void chargerEtChangerScene(ActionEvent event, String cheminFxml, String titre) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(cheminFxml));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle(titre);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException("Erreur lors du chargement du fichier FXML", e);
        }
    }
}

