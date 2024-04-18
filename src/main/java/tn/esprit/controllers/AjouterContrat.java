package tn.esprit.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tn.esprit.models.Contrat;
import tn.esprit.models.Personne;
import tn.esprit.services.ContratService;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AjouterContrat implements Initializable {

    @FXML
    private TextField couverture;

    @FXML
    private DatePicker datedebut;

    @FXML
    private TextField engagement;

    @FXML
    private TextField prix;
    @FXML
    private TextField client;

    @FXML
    private Button submit;

    @FXML
    void submit(ActionEvent event) {
        Contrat c = new Contrat();
        ContratService cs = new ContratService();
        c.setCouverture(couverture.getText());
        c.setCouverture(client.getText());
        c.setEngagement(Integer.parseInt(engagement.getText()));
        c.setPrix(Integer.parseInt(prix.getText()));

        c.setDebut(Date.valueOf(datedebut.getValue()));
        LocalDate finDate = c.getDebut().toLocalDate().plusMonths(c.getEngagement());
        c.setFin(Date.valueOf(finDate));

        try {
            cs.add(c);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succès");
            alert.setHeaderText("Contrat ajouté avec succès");
            alert.showAndWait();
        }
        catch(Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Échec de l'ajout du contrat");
            alert.setContentText("Une erreur est survenue lors de l'ajout du contrat. Veuillez réessayer.");
            alert.showAndWait();
        }


    }
    @FXML
    void goToContratList(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ContratIndex.fxml"));
        Parent root ;
        try {
            Node source = (Node) event.getSource();
            root = loader.load();
            System.out.println("FXML file loaded successfully.");
            ContratIndex controller = loader.getController();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.setTitle("Modifier Contrat");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    Button btn1;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btn1.setOnAction(this::goToContratList);
    }
}
