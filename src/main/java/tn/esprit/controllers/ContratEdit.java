// ContratEdit.java
package tn.esprit.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import tn.esprit.models.Contrat;
import tn.esprit.services.ContratService;

import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

import java.sql.Date;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class ContratEdit implements Initializable  {


    @FXML
    private TextField couverture;


    @FXML
    private TextField datedebut;
    @FXML
    private TextField client;

    @FXML
    private TextField engagement;

    @FXML
    private TextField prix;

    @FXML
    private Button retour;

    private int id, pr, eng;
    private String cl , cv;
    private String db;
    private Contrat  contrat ;
    private  ContratService contratService ;
    @FXML
    void submit(ActionEvent event) {
        send(id);
    }
    @FXML
    private Button annuler;

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

    private void send(int id) {


        ContratService cs = new ContratService();
        Contrat c = cs.getById(id) ;

        c.setCouverture(couverture.getText());
        c.setEngagement(Integer.parseInt(engagement.getText()));
        c.setPrix(Integer.parseInt(prix.getText()));
        c.setClient(client.getText());
        String dateString = datedebut.getText();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(dateString, formatter);
        java.sql.Date sqlDate = java.sql.Date.valueOf(localDate);
        c.setDebut(sqlDate);
      //  c.setDebut(java.sql.Date.valueOf(datedebut.getValue()));
        LocalDate finDate = c.getDebut().toLocalDate().plusMonths(c.getEngagement());
        c.setFin(java.sql.Date.valueOf(finDate));
try {
    cs.edit(c);
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Succès");
    alert.setHeaderText("Contrat a été mise a jour avec succès");
    alert.showAndWait();
}catch (Exception e) {

    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Erreur");
    alert.setHeaderText("Échec lors de la mise a jour du contrat");
    alert.setContentText("Une erreur est survenue lors de l'ajout du contrat. Veuillez réessayer.");
    alert.showAndWait();
}


    }

    public void setId(int id, String cl, String cv, int eng, String db, int pr) {
        this.id = id;
        this.cv = cv;
        this.eng = eng;
        this.db = db;
        this.pr = pr;
        this.cl = cl ;

        updateUI();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        annuler.setOnAction(this::goToContratList);
        retour.setOnAction(this::goToContratList);
        updateUI();
    }
    private void updateUI() {

        prix.setText(String.valueOf(pr));
        engagement.setText(String.valueOf(eng));
        couverture.setText(cv);

        client.setText(cl);
       datedebut.setText(db);





//        Instant instant = db.toInstant();
//        LocalDate localDate = instant.atZone(ZoneId.systemDefault()).toLocalDate();
//        datedebut.setValue(db);

    }


}
