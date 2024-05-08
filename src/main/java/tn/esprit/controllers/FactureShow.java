package tn.esprit.controllers;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import com.sun.javafx.font.FontFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import tn.esprit.services.ContratService;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
//import javafx.embed.swing.SwingFXUtils;

import javax.imageio.ImageIO;
import javax.swing.text.Document;
import javax.swing.text.ParagraphView;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;


public class FactureShow implements Initializable {


    @FXML
    private Text client;

    @FXML
    private Button contrate;
    @FXML
    private Button facturee;
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
        pay.setOnAction(this::processPayment);
        facturee.setOnAction(this::goToFactureIndex);
        contrate.setOnAction(this::contrat);

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

    private void processPayment(ActionEvent event) {
        try {
            Stripe.apiKey = "sk_test_51P9mzx08ZPBTxeQJ65MEjAfcnQfq8I8ekDhjcFD0ZPnD2AbwvbCSDm8cKNX7TwoexbTCNJTL3zKY8aNV5ujXgrPg00x9JUN7K6";
            PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                    .setAmount(1000L)
                    .setCurrency("usd")
                    .build();
            PaymentIntent intent = PaymentIntent.create(params);
            System.out.println("Payment successful. PaymentIntent ID: " + intent.getId());
        } catch (StripeException e) {
// If there was an error processing the payment, display the error message
            System.out.println("Payment failed. Error: " + e.getMessage());
        }
    }






}
