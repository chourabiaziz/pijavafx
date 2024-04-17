package tn.esprit.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import tn.esprit.models.Contrat;
import tn.esprit.models.Facture;
import tn.esprit.services.ContratService;
import tn.esprit.services.FactureService;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class FactureIndex implements Initializable {
    @FXML
    private Button add;

    @FXML
    private Button contrat;

    @FXML
    private ListView<Facture> listView;

    private FactureService factureService;
    private List<Facture> listContrat;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        contrat.setOnAction(this::gotofacture);
        add.setOnAction(this::add);
        listContrat = new ArrayList<>();
        afficherContrats();

    }



    private class ContratListCell extends ListCell<Facture> {

        protected void updateItem(Facture facture, boolean empty) {
            super.updateItem(facture, empty);

            if (empty || facture == null) {
                setText(null);
                setGraphic(null);
            } else {
                // Create an HBox to hold the contract details
                HBox card = new HBox();
                card.setStyle("-fx-background-color: #808080; -fx-padding: 10px; -fx-spacing: 10px;");
                Label idLabel = new Label("Facture N°" + facture.getId());
                Label totale = new Label(  facture.getTotale()+" TND");
                Label contrat = new Label("pour contrat N°" + facture.getId());
                Label createdat = new Label("creation en" + facture.getCreatedat());

                totale.setStyle("-fx-font-weight: bold;");
                createdat.setStyle("-fx-font-weight: bold;");
                Button button0 = new Button("Consulter");
                Button button1 = new Button("archiver");Button button2 = new Button("Supprimer");
                HBox buttonBox = new HBox(button0 , button1, button2);
                buttonBox.setAlignment(Pos.CENTER_RIGHT);
                card.getChildren().addAll(idLabel, totale, contrat, createdat, buttonBox);
//buttons actions
                button1.setOnAction(event -> {


//                    int id = contrat.getId() ;int engagement = contrat.getEngagement() ;String couverture = contrat.getCouverture() ;
//                    int prix = contrat.getPrix() ;
//                    String debut= contrat.getDebut().toString();
//
//                    n.gotoedit(event, id ,couverture,engagement, debut,prix);
                    System.out.println("archiver");

                });
                button2.setOnAction(event -> {
//                    contratService.delete(contrat.getId());
//                    refreshContrats();
                    System.out.println("supprimer");

                });
                button0.setOnAction(event -> {
//                    contratService.delete(contrat.getId());
//                    refreshContrats();
                    System.out.println("consulter");
                });
                setGraphic(card);
            }
        }}
//    private void refreshContrats() {
//        listView.getItems().clear(); // Clear the existing items
//        listContrat.clear(); // Clear the list of contracts
//        listContrat.addAll(contratService.getAll()); // Get the updated list of contracts
//        ObservableList<Contrat> observableList = FXCollections.observableList(listContrat);
//        listView.setItems(observableList); // Set the updated list to the ListView
//    }


    private void afficherContrats() {
        FactureService fs = new FactureService() ;
        List<Facture> factures = fs.getAll();
        listContrat.addAll(factures);
        ObservableList<Facture> observableList = FXCollections.observableList(listContrat);
        listView.setItems(observableList);
        listView.setCellFactory(param -> new ContratListCell());
    }












    private void gotofacture(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ContratIndex.fxml"));
        Parent root = null;
        try {
            Node source = (Node) event.getSource();
            root = loader.load();
            System.out.println("FXML file loaded successfully.");
            ContratIndex controller = loader.getController();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.setTitle("Liste des Contrats");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void add(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FactureAdd.fxml"));
        Parent root = null;
        try {
            Node source = (Node) event.getSource();
            root = loader.load();
            System.out.println("FXML file loaded successfully.");
            FactureAdd controller = loader.getController();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.setTitle("Liste des Contrats");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
