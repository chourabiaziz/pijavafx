package tn.esprit.controllers;

import com.google.zxing.client.j2se.MatrixToImageWriter;
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
import tn.esprit.models.Contrat;
import tn.esprit.services.ContratService;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ContratShow implements Initializable {
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
    private Button facturee;
    @FXML
    private Button contrat;

    private int id, pr, eng;
    private String cl , cv;
    private String db , fn;

    @FXML
    private Button generateButton;

    @FXML
    private ImageView qrCodeImageView;
    @FXML
    private void generateQRCode() {
        String myInformation = "Contrat ref : " +id+
                " couverture :" +cv+
                " Pour Mr/Mdm :" +cl+
                " Engagement dans  :" +eng+
                " Mois  "+
                " Valable de  :" +db+
                " Jusqu'au :" +fn
                +" de Prix (Sans Promotion et Sans TVA) " + pr + " TND"
;

                ByteArrayOutputStream stream = new ByteArrayOutputStream();
        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(myInformation, BarcodeFormat.QR_CODE, 300, 300);
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", stream);
            byte[] byteArray = stream.toByteArray();
            String qrCodeImage = Base64.getEncoder().encodeToString(byteArray);
            Image image = new Image("data:image/png;base64," + qrCodeImage);
            qrCodeImageView.setImage(image);
        } catch (WriterException | IOException e) {
            e.printStackTrace();
            showAlert("Erreur", "Erreur lors de la génération du QR Code", e.getMessage(), AlertType.ERROR);
        }
    }

    private void showAlert(String title, String header, String content, AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

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
        facturee.setOnAction(this::facturee);
        contrat.setOnAction(this::contratou);

        updateUI();
    }

    private void facturee(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FactureIndex.fxml"));
        Parent root = null;
        try {
            Node source = (Node) event.getSource();
            root = loader.load();
            System.out.println("FXML file loaded successfully.");
            FactureIndex controller = loader.getController();

            Stage stage = (Stage) source.getScene().getWindow();
            stage.setTitle("Generer facture");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void contratou(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ContratIndex.fxml"));
        Parent root = null;
        try {
            Node source = (Node) event.getSource();
            root = loader.load();
            System.out.println("FXML file loaded successfully.");
            ContratIndex controller = loader.getController();

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




}
