package tn.esprit.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import tn.esprit.models.Contrat;
import tn.esprit.services.ContratService;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ContratIndex implements Initializable {

    @FXML
    private HBox card;
    @FXML
    private Label couverture;

    @FXML
    private Label engagement;

    @FXML
    private Label prix;

    private ContratService contratService;

   private void afficherContrats() {
        List<Contrat> listContrat = contratService.getAll();

        // Clear existing labels
      //  client.setText("");
        couverture.setText("");
        engagement.setText("");
        prix.setText("");
        if (!listContrat.isEmpty()) {
            for (Contrat contrat : listContrat) {
                // client.setText(client.getText() + contrat.getClient() + "\n");
                couverture.setText(couverture.getText() + contrat.getCouverture() );
                engagement.setText(engagement.getText() + contrat.getEngagement() );
                prix.setText(prix.getText() + contrat.getPrix() );// Assuming there's a method getPrix() in Contrat class
        }}
    }








    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        contratService = new ContratService();
        afficherContrats();
    }


}

