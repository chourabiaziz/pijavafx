package tn.esprit.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import tn.esprit.services.ContratService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FactureShow implements Initializable {


    @FXML
    private Text client;

    @FXML
    private Button contrat;
    @FXML
    private Button send;

    @FXML
    private Text couverture;

    @FXML
    private Text debut;

    @FXML
    private Text engagement;

    @FXML
    private Button facture;

    @FXML
    private Text fin;

    @FXML
    private Text idc;

    @FXML
    private Text prix;

    @FXML
    private Text tot;

    @FXML
    private Text tva;
    private int idee, pr, eng ,tvae , tote;
    private String cl , cv;
    private String db,fn;

    private ContratService contratService ;

    public void setId(int idee, String cl, String cv, int eng, String db, String fn , int pr ,int tvae , int tote ) {
        this.idee = idee;
        this.cv = cv;
        this.eng = eng;
        this.db = db;
        this.fn=fn;
        this.pr = pr;
        this.cl = cl ;
        this.tvae=tvae;
        this.tote =tote ;
        updateUI();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        facture.setOnAction(this::goToFactureIndex);
        contrat.setOnAction(this::contrat);



    }
    private void updateUI() {

        prix.setText(String.valueOf(pr));
        engagement.setText(String.valueOf(eng));
        couverture.setText(cv);
        idc.setText(String.valueOf(idee));
        client.setText(cl);
        debut.setText(db);
        fin.setText(fn);

        tot.setText(String.valueOf(tote));
        tva.setText(String.valueOf(tvae));

    }
    void goToFactureIndex(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FactureIndex.fxml"));
        Parent root ;
        try {
            Node source = (Node) event.getSource();
            root = loader.load();
            System.out.println("FXML file loaded successfully.");
            FactureIndex controller = loader.getController();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.setTitle("Facture  ");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    void contrat(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ContratIndex.fxml"));
        Parent root ;
        try {
            Node source = (Node) event.getSource();
            root = loader.load();
            System.out.println("FXML file loaded successfully.");
            ContratIndex controller = loader.getController();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.setTitle("Facture  ");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
