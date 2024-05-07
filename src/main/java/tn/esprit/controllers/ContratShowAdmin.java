package tn.esprit.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ContratShowAdmin  implements Initializable {


    @FXML
    private Text client;

    @FXML
    private Text couverture;

    @FXML
    private Text debut;

    @FXML
    private Text engagement;

    @FXML
    private Text fin;
    @FXML
    private Text idc ;
    @FXML
    private Text prix;
    @FXML
    private Button retour;
    @FXML
    private Button facture;
    @FXML
    private Button facturepage;

    private int id, pr, eng;
    private String cl , cv;
    private String db , fn;




    public void setId(int id, String cl, String cv, int eng, String db ,String fn, int pr) {
        this.id = id;
        this.cv = cv;
        this.eng = eng;
        this.db = db;
        this.fn = fn;
        this.pr = pr;
        this.cl = cl ;

        updateUI();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        retour.setOnAction(this::goToContratList);
        facturepage.setOnAction(this::facturepage);
        retour.setStyle("-fx-background-color: #cacaca; -fx-padding: 10px; -fx-border-radius: 50px;");
        //  facture.setStyle("-fx-background-color: #cacaca; -fx-padding: 10px; -fx-border-radius: 50px;");
        facture.setOnAction(event -> {
            facture(event, id , cl, cv,eng, db,fn,pr);
        });
        updateUI();
    }


    public void facture(ActionEvent event , int id , String couverture, String cliente, int engagement , String debut , String fin , int prix) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FactureAdd.fxml"));
        Parent root = null;
        try {
            Node source = (Node) event.getSource();
            root = loader.load();
            System.out.println("FXML file loaded successfully.");
            FactureAdd controller = loader.getController();
            controller.setId(id , cliente, couverture , engagement , debut, fin,prix);
            Stage stage = (Stage) source.getScene().getWindow();
            stage.setTitle("Generer facture");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private void updateUI() {

        prix.setText(String.valueOf(pr));
        engagement.setText(String.valueOf(eng));
        couverture.setText(cv);

        client.setText(cl);
        debut.setText(db);
        fin.setText(fn);

        // System.out.println(cl+cv+eng+"");




//        Instant instant = db.toInstant();
//        LocalDate localDate = instant.atZone(ZoneId.systemDefault()).toLocalDate();
//        datedebut.setValue(db);

    }
    @FXML
    void goToContratList(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ContratIndexAdmin.fxml"));
        Parent root ;
        try {
            Node source = (Node) event.getSource();
            root = loader.load();
            System.out.println("FXML file loaded successfully.");
            ContratIndexAdmin controller = loader.getController();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.setTitle("contrat  ");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    void facturepage(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FactureIndexAdmin.fxml"));
        Parent root ;
        try {
            Node source = (Node) event.getSource();
            root = loader.load();
            System.out.println("FXML file loaded successfully.");
            FactureIndexAdmin controller = loader.getController();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.setTitle("Facture  ");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
