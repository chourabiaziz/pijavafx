// ContratEdit.java
package tn.esprit.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
import java.util.ResourceBundle;

public class ContratEdit implements Initializable  {


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
    private int id, pr, eng;
    private String cv;
    private Date db;
    private Contrat  contrat ;
    private  ContratService contratService ;
    @FXML
    void submit(ActionEvent event) {

    }
    public void setId(int id, String cv, int eng, Date db, int pr) {
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
//        Instant instant = db.toInstant();
//        LocalDate localDate = instant.atZone(ZoneId.systemDefault()).toLocalDate();
//        datedebut.setValue(localDate);

    }



}
