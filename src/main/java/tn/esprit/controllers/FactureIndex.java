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
import javafx.scene.control.*;
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
    private Button contrat;
    @FXML
    private Button facture;
    @FXML
    private ListView<Facture> listView;

    private FactureService factureService = new FactureService() ;
    private List<Facture> listContrat;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        contrat.setOnAction(this::contrat);
//        contrat.setStyle("-fx-background-color: #cacaca; -fx-padding: 10px; -fx-border-radius: 50px;");
//        facture.setStyle("-fx-background-color: #cacaca; -fx-padding: 10px; -fx-border-radius: 50px;");
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
                card.setStyle("-fx-alignment: center ;-fx-background-color: #6d89ef; -fx-padding: 10px; -fx-spacing: 10px;");
                Label idLabel = new Label("Facture N°" + facture.getId());
                Label totale = new Label(  facture.getTotale()+" TND");
                Label contrat = new Label("pour contrat N°" + facture.getId());
                Label createdat = new Label("créé en " + facture.getCreatedat());

                totale.setStyle("-fx-font-weight: bold;");
                createdat.setStyle("-fx-font-weight: bold;");
                Button button0 = new Button("Consulter");
                Button button1 = new Button("archiver");Button button2 = new Button("Supprimer");
                HBox buttonBox = new HBox(button0 , button1, button2);
                button0.setStyle("-fx-background-color: #cacaca; -fx-padding: 10px; -fx-border-radius: 50px;");
                button1.setStyle("-fx-background-color: #6e6e6e; -fx-padding: 10px; -fx-border-radius: 50px;");
                button2.setStyle("-fx-background-color: #ef1a1a; -fx-padding: 10px; -fx-border-radius: 50px;");
                buttonBox.setStyle("-fx-spacing: 10px;");
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



                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Succès");
                    alert.setHeaderText("facture Supprimer avec succès");
                    alert.showAndWait();
                    factureService.delete(facture.getId());
                    refreshContrats();
                });
                button0.setOnAction(event -> {
                    ContratService cs = new ContratService();
                    Contrat c = cs.getById(facture.getContrat());
                  facture(event, facture.getId() , c.getClient(), c.getCouverture(),c.getEngagement(), c.getDebut()+"",c.getFin()+"",c.getPrix() , facture.getTva() , facture.getTotale());

                });

                setGraphic(card);
            }
        }}


    private void refreshContrats() {
        listView.getItems().clear(); // Clear the existing items
        listContrat.clear(); // Clear the list of contracts
         afficherContrats();
    }
    private void afficherContrats() {
        FactureService fs = new FactureService() ;
        List<Facture> factures = fs.getAll();
        listContrat.addAll(factures);
        ObservableList<Facture> observableList = FXCollections.observableList(listContrat);
        listView.setItems(observableList);
        listView.setCellFactory(param -> new ContratListCell());
    }







    public void facture(ActionEvent event , int id ,String cliente , String couverture, int engagement , String debut , String fin , int prix , int tva,int tot) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FactureShow.fxml"));
        Parent root = null;
        try {
            Node source = (Node) event.getSource();
            root = loader.load();
            System.out.println("FXML file loaded successfully.");
            FactureShow controller = loader.getController();
            controller.setId(id , cliente, couverture , engagement , debut, fin,prix , tva ,tot);
            Stage stage = (Stage) source.getScene().getWindow();
            stage.setTitle("facture");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }




    private void contrat(ActionEvent event) {
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

    private void edit(ActionEvent event) {
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
