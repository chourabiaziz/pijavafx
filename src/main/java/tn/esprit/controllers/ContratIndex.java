package tn.esprit.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import tn.esprit.models.Contrat;
import tn.esprit.navigation.Navigation;
import tn.esprit.services.ContratService;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ContratIndex implements Initializable {
    Navigation n = new Navigation() ;
    @FXML
    private ListView<Contrat> listView;

    private ContratService contratService;
    private List<Contrat> listContrat;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        contratService = new ContratService();
        listContrat = new ArrayList<>();

        facture.setOnAction(n::goToFactureIndex);
        add.setOnAction(n::changeroute);
        afficherContrats();
    }

    @FXML
    private Button facture;
    ////class one cell

    private class ContratListCell extends ListCell<Contrat> {
   @Override
        protected void updateItem(Contrat contrat, boolean empty) {
            super.updateItem(contrat, empty);

            if (empty || contrat == null) {
                setText(null);
                setGraphic(null);
            } else {
                // Create an HBox to hold the contract details
                HBox card = new HBox();
                card.setStyle("-fx-background-color: #808080; -fx-padding: 10px; -fx-spacing: 10px;");
                Label idLabel = new Label("Contrat N°" + contrat.getId());
                String couvertureText = contrat.getCouverture() != null ? contrat.getCouverture() : "";
                Label couvertureLabel = new Label("-- " + couvertureText);
                String debutText = contrat.getDebut() != null ? contrat.getDebut().toString() : "";
                Label debutLabel = new Label("De " + debutText);
                String finText = contrat.getFin() != null ? contrat.getFin().toString() : "";
                Label finLabel = new Label("Jusqu'a " + finText);
                Label prixLabel = new Label(contrat.getPrix() + " DT");
                prixLabel.setStyle("-fx-font-weight: bold;");
                Button button1 = new Button("Modifier");
                Button button2 = new Button("Supprimer");
                HBox buttonBox = new HBox(button1, button2);
                buttonBox.setAlignment(Pos.CENTER_RIGHT);
                card.getChildren().addAll(idLabel, prixLabel, couvertureLabel, debutLabel, finLabel, buttonBox);
//buttons actions
                button1.setOnAction(event -> {


                    int id = contrat.getId() ;int engagement = contrat.getEngagement() ;String couverture = contrat.getCouverture() ;
                    int prix = contrat.getPrix() ;
                    String debut= contrat.getDebut().toString();

                    n.gotoedit(event, id ,couverture,engagement, debut,prix);

                });
                button2.setOnAction(event -> {
                   contratService.delete(contrat.getId());
                    refreshContrats();

                });
                setGraphic(card);
            }
        }}
    private void refreshContrats() {
        listView.getItems().clear(); // Clear the existing items
        listContrat.clear(); // Clear the list of contracts
        listContrat.addAll(contratService.getAll()); // Get the updated list of contracts
        ObservableList<Contrat> observableList = FXCollections.observableList(listContrat);
        listView.setItems(observableList); // Set the updated list to the ListView
    }





    private void afficherContrats() {
        List<Contrat> contrats = contratService.getAll();
        listContrat.addAll(contrats);
        ObservableList<Contrat> observableList = FXCollections.observableList(listContrat);
        listView.setItems(observableList);
        listView.setCellFactory(param -> new ContratListCell());
    }
@FXML
Button add ;



}
