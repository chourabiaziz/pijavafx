package tn.esprit.controllers;

        import javafx.fxml.FXML;
        import javafx.scene.control.Alert;
        import javafx.scene.control.Button;
        import javafx.scene.image.Image;
        import javafx.scene.image.ImageView;
        import javafx.stage.FileChooser;

        import java.awt.image.BufferedImage;
        import java.io.File;
        import java.io.IOException;

        import net.sourceforge.tess4j.TesseractException;
        import tn.esprit.utils.OCRManager; // Importer la classe OCRManager depuis le package approprié

        import javax.imageio.ImageIO;


public class Scan {

    @FXML
    private ImageView imageView;


    public void scannerTexte() {
        // Ouvrir une boîte de dialogue pour sélectionner une image
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("selectionner  une image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Images", "*.jpg", "*.jpeg", "*.png", "*.bmp", "*.gif"),
                new FileChooser.ExtensionFilter("Tous les fichiers", "*.*")
        );
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            try {

                // Créer un OCR manager pour extraire le texte de l'image
                OCRManager ocrManager = new OCRManager();

                // Extraire le texte de l'image
                String texteExtrait = ocrManager.getTextFromImage(selectedFile); // pass the selectedFile instead of image

                // Afficher le texte extrait dans une boîte de dialogue
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Texte extrait");
                alert.setHeaderText(null);
                alert.setContentText("Texte extrait de l'image :\n" + texteExtrait);
                alert.showAndWait();
            } catch (IOException e) {
                e.printStackTrace();
                // Gérer les erreurs d'extraction du texte
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("Une erreur s'est produite lors de l'extraction du texte.");
                alert.showAndWait();
            } catch (TesseractException e) {
                throw new RuntimeException(e);
            }
        } else {
            // Gérer le cas où aucune image n'est sélectionnée
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aucune image sélectionnée");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez sélectionner une image avant de scanner le texte.");
            alert.showAndWait();
        }
    }

}