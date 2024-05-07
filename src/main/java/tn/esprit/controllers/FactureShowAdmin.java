package tn.esprit.controllers;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import tn.esprit.services.ContratService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class FactureShowAdmin implements Initializable {


    @FXML
    private Text client;

    @FXML
    private Button contrat;
    @FXML
    private Button send;
    @FXML
    private Button pay;

    @FXML
    private Text debut;

    @FXML
    private Text engagement;

    @FXML
    private Button facture;
    @FXML
    private Button pdf;

    @FXML
    private Text fin;

    @FXML
    private Text idc;

    @FXML
    private Text prix;

    @FXML
    private Text tot;
    @FXML
    private Text couverture;
    @FXML
    private Text tva;
    @FXML
    private VBox fafa;
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
    @FXML
    private AnchorPane anchorPane;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
      //  pdf.setOnAction(event -> saveAsPDF(pdf.getScene().getWindow()));
        pdf.setOnAction(event -> {
            Pdf pdfInstance = new Pdf();

            pdfInstance.download(String.valueOf(idee),cl,cv,db,fn,String.valueOf(eng),String.valueOf(pr),String.valueOf(tvae),String.valueOf(tote));


        });
        facture.setOnAction(this::goToFactureIndex);
        contrat.setOnAction(this::contrat);
        pay.setOnAction(this::pay);
    }


    void pay(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Payment.fxml"));
        Parent root ;
        try {

            root = loader.load();
            System.out.println("FXML file loaded successfully.");
            Payment controller = loader.getController();
            controller.setId(tote);
            Stage stage = new Stage();
            stage.setTitle("paiement");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
















    private void updateUI() {

        prix.setText(String.valueOf(pr));
        engagement.setText(String.valueOf(eng));
        couverture.setText(cl);
        idc.setText(String.valueOf(idee));
        client.setText(cv);
        debut.setText(db);
        fin.setText(fn);

        tot.setText(String.valueOf(tote));
        tva.setText(String.valueOf(tvae));

    }
    void goToFactureIndex(ActionEvent event) {
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
    void contrat(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ContratIndexAdmin.fxml"));
        Parent root ;
        try {
            Node source = (Node) event.getSource();
            root = loader.load();

            ContratIndexAdmin controller = loader.getController();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.setTitle("Contrat  ");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }







}
