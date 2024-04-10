package tn.esprit.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import tn.esprit.models.Contrat;
import tn.esprit.models.Personne;
import tn.esprit.services.ContratService;

import java.sql.Date;
import java.time.LocalDate;

public class AjouterContrat {

    @FXML
    private TextField couverture;

    @FXML
    private DatePicker datedebut;

    @FXML
    private TextField engagement;

    @FXML
    private TextField prix;

    @FXML
    private Button submit;

    @FXML
    void submit(ActionEvent event) {
        Contrat c = new Contrat();
        ContratService cs = new ContratService();
        c.setCouverture(couverture.getText());
        c.setEngagement(Integer.parseInt(engagement.getText()));
        c.setPrix(Integer.parseInt(prix.getText()));

        c.setDebut(Date.valueOf(datedebut.getValue()));
        LocalDate finDate = c.getDebut().toLocalDate().plusMonths(c.getEngagement());
        c.setFin(Date.valueOf(finDate));
        cs.add(c);

        if(cs.add(c)){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succès");
            alert.setHeaderText("Contrat ajouté avec succès");
             alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Échec de l'ajout du contrat");
            alert.setContentText("Une erreur est survenue lors de l'ajout du contrat. Veuillez réessayer.");
            alert.showAndWait();
        }
    }

}
