package tn.esprit.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import tn.esprit.models.Panne;
import tn.esprit.services.ServicePanne;
import java.util.Date;

import java.net.URL;
import java.util.ResourceBundle;

public class AjouterPanne implements Initializable {

    @FXML
    private TextField atelierIdTextField;

    @FXML
    private TextField localisationTextField;

    @FXML
    private TextField panneTextField;

    @FXML
    private TextArea descriptionTextArea;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    void submit(ActionEvent event) {
        // Récupérer les données saisies par l'utilisateur
        int atelierId = Integer.parseInt(atelierIdTextField.getText());
        String localisation = localisationTextField.getText();
        String panne = panneTextField.getText();
        String description = descriptionTextArea.getText();

        // Créer un objet Panne avec les données saisies
        Panne newPanne = new Panne();
        newPanne.setAtelier_id(atelierId);
        newPanne.setLocalisation(localisation);
        newPanne.setPanne(panne);
        newPanne.setDescription(description);

        // Obtenez la date actuelle
        Date currentDate = new Date();
        newPanne.setDate(currentDate); // Définissez la date de la panne sur la date actuelle

        // Ajouter la panne à la base de données en utilisant le service
        ServicePanne servicePanne = new ServicePanne();
        servicePanne.add(newPanne);

        // Afficher un message de confirmation
        System.out.println("Panne ajoutée avec succès !");
    }

}
