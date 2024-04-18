package tn.esprit.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import tn.esprit.models.Contrat;
import tn.esprit.models.Facture;
import tn.esprit.services.ContratService;
import tn.esprit.services.FactureService;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class FactureAdd implements Initializable {

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
    private TextField tva;
    private int idee, pr, eng;
    private String cl , cv;
    private String db,fn;

    private ContratService contratService ;

    public void setId(int idee, String cl, String cv, int eng, String db, String fn , int pr) {
        this.idee = idee;
        this.cv = cv;
        this.eng = eng;
        this.db = db;
        this.fn=fn;
        this.pr = pr;
        this.cl = cl ;
        updateUI();
    }

    @FXML
    private void handleTvaKeyReleased() {
        updateTotale();
    }

    @FXML
    private void updateTotale() {
        try {

            int tvaValue = Integer.parseInt(tva.getText());
            int totale = pr + (pr * tvaValue / 100);
            tot.setText(String.valueOf(totale));
        } catch (NumberFormatException ex) {
            tot.setText("-_-");
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        facture.setOnAction(this::goToFactureIndex);
        contrat.setOnAction(this::contrat);
        send.setOnAction(this::send);


    }

    private void send(ActionEvent event) {
        Facture f = new Facture();
        FactureService cs = new FactureService();
        ContratService contratService1 = new ContratService()
;        f.setCreatedat(  Date.valueOf(LocalDate.now()));
        f.setTva(Integer.parseInt(tva.getText()));
        f.setTotale(Integer.parseInt(tot.getText()));
        f.setContrat(Integer.parseInt(idc.getText()));
        f.setClient(cl);
        try {
            cs.add(f);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succès");
            alert.setHeaderText("facture ajouté avec succès");
            alert.showAndWait();
        }
        catch(Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Échec de l'ajout du facture");
            alert.setContentText("Une erreur est survenue lors de l'ajout du contrat. Veuillez réessayer.");
            alert.showAndWait();
        }
    }



    private void updateUI() {

        prix.setText(String.valueOf(pr));
        engagement.setText(String.valueOf(eng));
        couverture.setText(cv);
        idc.setText(String.valueOf(idee));
        client.setText(cl);
        debut.setText(db);
        fin.setText(fn);

        int tvaValue = Integer.parseInt(tva.getText()); // Convert text input to integer
        int totale = pr + (pr * tvaValue / 100);
        tot.setText(totale+"");

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
