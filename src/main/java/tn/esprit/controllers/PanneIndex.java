package tn.esprit.controllers;

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
        if (tilePane == null) {
            // Afficher une alerte si tilePane est null
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("tilePane est null. Vérifiez le fichier FXML.");
            alert.showAndWait();
            return;
        }

        // Effacer les enfants existants
        tilePane.getChildren().clear();

        for (Panne panne : pannes) {
            Label label = new Label(panne.toString());
            tilePane.getChildren().add(label);
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
}