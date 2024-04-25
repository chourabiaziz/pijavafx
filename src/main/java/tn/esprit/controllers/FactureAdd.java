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
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
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
import java.util.Optional;
import java.util.ResourceBundle;

public class FactureAdd implements Initializable {

    @FXML
    private Text client;

    @FXML
    private Button retour;
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
        tot.setText(pr + pr*19/100 +"");
        updateUI();
    }

    @FXML
    private void handleTvaKeyReleased() {
        updateTotale();
    }
@FXML
private Text errpromo ;
    @FXML
    private void updateTotale() {
                if (tva.getText().matches("[a-zA-Z]+") || tva.getText().isEmpty() ) {
                    errpromo.setText("champ ne peut pas etre vide ou contient des lettres inclus les espaces");
                    errpromo.setFill(Color.RED);
                    send.setDisable(true);
                } else if (!tva.getText().matches("(100|[1-9]?[0-9])")) {
            errpromo.setText("Promotion doit etre  entre 0 et 100");
            errpromo.setFill(Color.RED);
            send.setDisable(true);
        } else {
                    errpromo.setText("");
                    send.setDisable(false);
                }
            int tvaValue = Integer.parseInt(tva.getText());
            int totale = pr - (pr * tvaValue / 100) + (pr * 19 / 100);

            tot.setText(totale+"");

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
            Optional<ButtonType> result = alert.showAndWait();

// Check if the user clicked OK
            if (result.isPresent() && result.get() == ButtonType.OK) {
                contrat(event);
            } else {
                contrat(event);


            }

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

        prix.setText(pr +"");
        engagement.setText(String.valueOf(eng));
        couverture.setText(cv);
        idc.setText(String.valueOf(idee));
        client.setText(cl);
        debut.setText(db);
        fin.setText(fn);



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
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        errpromo.setText("");
        send.setOnAction(this::send);
        retour.setOnAction(this::contrat);



    }
}
