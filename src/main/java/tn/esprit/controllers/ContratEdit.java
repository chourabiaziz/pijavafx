// ContratEdit.java
package tn.esprit.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import tn.esprit.models.Contrat;
import tn.esprit.services.ContratService;

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
    private TextField engagement;

    @FXML
    private TextField prix;

    @FXML
    private Button submit;
    private int id, pr, eng;
    private String cv;
    private String db;
    private Contrat  contrat ;
    private  ContratService contratService ;
    @FXML
    void submit(ActionEvent event) {
        send(id);
    }

    private void send(int id) {


        ContratService cs = new ContratService();
        Contrat c = cs.getById(id) ;

        c.setCouverture(couverture.getText());
        c.setEngagement(Integer.parseInt(engagement.getText()));
        c.setPrix(Integer.parseInt(prix.getText()));
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

    public void setId(int id, String cv, int eng, String db, int pr) {
        this.id = id;
        this.cv = cv;
        this.eng = eng;
        this.db = db;
        this.pr = pr;

        updateUI();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateUI();
    }
    private void updateUI() {

        prix.setText(String.valueOf(pr));
        engagement.setText(String.valueOf(eng));
        couverture.setText(cv);


       datedebut.setText(db);


        System.out.println(db);


//        Instant instant = db.toInstant();
//        LocalDate localDate = instant.atZone(ZoneId.systemDefault()).toLocalDate();
//        datedebut.setValue(db);

    }



}
