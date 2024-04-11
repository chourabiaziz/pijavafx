package tn.esprit.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.w3c.dom.Text;
import tn.esprit.models.Contrat;
import tn.esprit.services.ContratService;

import javafx.event.ActionEvent;
import java.io.IOException;
import java.net.URL;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ContratIndex  implements Initializable {

    @FXML
    private ListView<Contrat> listView;
    @FXML
    private Label couverture;

    @FXML
    private Label engagement;

    @FXML
    private Label prix;
    @FXML
    private ListView<Contrat> listview;



    private ContratService contratService;
     private List<Contrat> listContrat;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        contratService = new ContratService();
        listContrat = new ArrayList<>();
        afficherContrats();
    }

////class  one cell

    private static class ContratListCell extends ListCell<Contrat> {

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

                // Create Labels for each contract detail
                Label idLabel = new Label("Contrat N°"+contrat.getId());

                Label couvertureLabel = new Label("-- " + contrat.getCouverture());
                Label debutLabel = new Label("De " + contrat.getDebut().toString());
                Label finLabel = new Label("Jusqu'a " + contrat.getFin().toString());
                Label prixLabel = new Label(contrat.getPrix()+" DT");
                prixLabel.setStyle("-fx-font-weight: bold;");


                Button button1 = new Button("Edit");
                Button button2 = new Button("Delete");
                HBox buttonBox = new HBox(button1, button2);
                buttonBox.setAlignment(Pos.CENTER_RIGHT);
                // Add the Labels to the HBox
                card.getChildren().addAll(idLabel, prixLabel, couvertureLabel, debutLabel, finLabel ,buttonBox);

                // Set the HBox as the graphic for the cell
                setGraphic(card);
            }
        }


    }



    private void afficherContrats() {
        List<Contrat> contrats = contratService.getAll();
        listContrat.addAll(contrats);
        ObservableList<Contrat> observableList = FXCollections.observableList(listContrat);
        listView.setItems(observableList);

        // Utilisez la cellule personnalisée pour afficher chaque élément de la liste
        listView.setCellFactory(param -> new ContratListCell());
    }

  /* private void afficherContrats() {
        List<Contrat> contrats = contratService.getAll();
       listContrat.addAll(contrats);
       ObservableList<Contrat> observableList = FXCollections.observableList(listContrat);
       listView.setItems(observableList);

   }*/


    @FXML
    void changeroute(ActionEvent event)  {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AjouterContrat.fxml"));
        Parent root = null;
        try {
            Node  source = (Node) event.getSource();

            root = loader.load();
            System.out.println("FXML file loaded successfully.");
            AjouterContrat controller = loader.getController();
            Stage stage = (Stage) source.getScene().getWindow();

            stage.setTitle("Ajouter Contrat");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }


}

