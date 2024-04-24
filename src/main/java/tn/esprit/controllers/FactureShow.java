package tn.esprit.controllers;

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
    private Button contrat;
    @FXML
    private Button send;


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
        pdf.setOnAction(event -> saveAsPDF(pdf.getScene().getWindow()));

        facture.setOnAction(this::goToFactureIndex);
    }














    private void saveAsPDF(Window window) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save PDF File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
        File file = fileChooser.showSaveDialog(window);
        if (file != null) {
            try {
                generatePDF(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private void generatePDF(File file) throws IOException {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                contentStream.setFont(PDType1Font.HELVETICA, 12);

                // Define text content
                String[] lines = {
                        "   Ref° facture: " + idee,"","",
                        "   Client: " + cl,"","",
                        
                        "   Couverture: " + cv,"","",
                        "   De: " + db,"","",
                        "   Jusqu'à: " + fn,"","",
                        "   Engagement: " + eng + " Mois","","",
                        "                                                           Prix:         " + pr + " TND","","",
                        "                                                           Tva: 19 %     ","","",
                        "                                                           Promotion de: " + tvae + " %","","",
                        "                                                           Totale:       " + tote + " TND","","",
                        "", // Empty line for spacing
                        "", // Empty line for spacing
                        "", // Empty line for spacing
                        "                  Signature : ------------------------"
                };

                // Starting position
                float x = 50;
                float y = page.getMediaBox().getHeight() - 50; // Adjust as needed

                // Adding text with line breaks
                contentStream.beginText();
                contentStream.newLineAtOffset(x, y);
                for (String line : lines) {
                    contentStream.showText(line);
                    contentStream.newLineAtOffset(0, -15); // Adjust line spacing as needed
                }
                contentStream.endText();
            }

            // Save the document
            document.save(file);
            System.out.println("The PDF file has been saved successfully!");
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
