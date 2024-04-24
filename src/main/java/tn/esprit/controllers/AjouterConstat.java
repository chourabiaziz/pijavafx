package tn.esprit.controllers;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import tn.esprit.models.Assurance;
import tn.esprit.models.Constat;
import tn.esprit.services.ServiceAssurance;
import tn.esprit.services.ServiceConstat;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AjouterConstat {

    ServiceConstat sp  = new ServiceConstat();


    @FXML
    private TextField tfNomPreneurA;

    @FXML
    private TextField tfPrenomPreneurA;

    @FXML
    private TextField tfTelPreneurA;

    @FXML
    private TextField tfMarquePreneurA;

    @FXML
    private TextField tfImmatriculationPreneurA;

    @FXML
    private TextField tfNomSocieteA;

    @FXML
    private TextField tfAdresseSocieteA;

    @FXML
    private CheckBox enStationnement;

    @FXML
    private CheckBox quittaitStationnement;

    @FXML
    private CheckBox prenaitStationnement;

    @FXML
    private CheckBox sortaitParking;

    @FXML
    private CheckBox doublait;

    @FXML
    private CheckBox viraitDroite;

    @FXML
    private CheckBox viraitGauche;

    @FXML
    private TextField tfNomPreneurB;

    @FXML
    private TextField tfPrenomPreneurB;

    @FXML
    private TextField tfTelPreneurB;

    @FXML
    private TextField tfMarquePreneurB;

    @FXML
    private TextField tfImmatriculationPreneurB;

    @FXML
    private TextField tfNomSocieteB;

    @FXML
    private TextField tfAdresseSocieteB;


    @FXML
    private TextField tfLocalisation;

    @FXML
    private TextField tfTemoins;

    @FXML
    private ImageView imageView;

    @FXML
    void ajouterConstat(ActionEvent event) {

        try {

            // Récupérer les valeurs des champs de saisie
            String nomPreneurA = tfNomPreneurA.getText();
            String prenomPreneurA = tfPrenomPreneurA.getText();
            String telPreneurA = tfTelPreneurA.getText();
            String marquePreneurA = tfMarquePreneurA.getText();
            String immatriculationPreneurA = tfImmatriculationPreneurA.getText();
            String nomSocieteA = tfNomSocieteA.getText();
            String adresseSocieteA = tfAdresseSocieteA.getText();

            // Récupérer les valeurs des champs de saisie
            String nomPreneurB = tfNomPreneurB.getText();
            String prenomPreneurB = tfPrenomPreneurB.getText();
            String telPreneurB = tfTelPreneurB.getText();
            String marquePreneurB = tfMarquePreneurB.getText();
            String immatriculationPreneurB = tfImmatriculationPreneurB.getText();
            String nomSocieteB = tfNomSocieteB.getText();
            String adresseSocieteB = tfAdresseSocieteB.getText();


            // Vérifier si les champs obligatoires sont vides
            if (nomPreneurA.isEmpty() || prenomPreneurA.isEmpty() || telPreneurA.isEmpty() ||
                    marquePreneurA.isEmpty() || immatriculationPreneurA.isEmpty() || nomSocieteA.isEmpty() || adresseSocieteA.isEmpty()) {
                // Afficher un message d'erreur si un champ obligatoire est vide
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez remplir tous les champs obligatoires !");
                alert.showAndWait();
                return; // Sortir de la méthode sans ajouter le constat
            }

            // Vérifier la longueur des noms et prénoms
            if (nomPreneurB.length() > 15 || prenomPreneurB.length() > 15) {
                // Afficher un message d'erreur si un nom ou un prénom dépasse 15 lettres
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("Le nom et le prénom ne doivent pas dépasser 15 caractères !");
                alert.showAndWait();
                return; // Sortir de la méthode sans ajouter le constat
            }

            // Vérifier la longueur des noms et prénoms
            if (nomSocieteA.length() > 15 || nomSocieteB.length() > 15) {
                // Afficher un message d'erreur si un nom ou un prénom dépasse 15 lettres
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("Le nom et le prénom ne doivent pas dépasser 15 caractères !");
                alert.showAndWait();
                return; // Sortir de la méthode sans ajouter le constat
            }



            // Vérifier le format du numéro de téléphone
            if (!telPreneurA.matches("\\d{8}") || !telPreneurB.matches("\\d{8}") )  {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("Le numéro de téléphone doit contenir 8 chiffres !");
                alert.showAndWait();
                return; // Sortir de la méthode sans ajouter l'assurance
            }




            Constat c = new Constat();

            c.setA_preneur_nom(tfNomPreneurA.getText());
            c.setA_preneur_prenom(tfPrenomPreneurA.getText());
            c.setA_preneur_tel(tfTelPreneurA.getText());
            c.setA_vehicule_moteur_marque(tfMarquePreneurA.getText());
            c.setA_vehicule_moteur_num_immatriculation(tfImmatriculationPreneurA.getText());
            c.setA_societe_assurance_agence_nom(tfNomSocieteA.getText());
            c.setA_societe_assurance_agence_adresse(tfAdresseSocieteA.getText());


            c.setB_preneur_nom(tfNomPreneurB.getText());
            c.setB_preneur_prenom(tfPrenomPreneurB.getText());
            c.setB_preneur_tel(tfTelPreneurB.getText());
            c.setB_vehicule_moteur_marque(tfMarquePreneurB.getText());
            c.setB_vehicule_moteur_num_immatriculation(tfImmatriculationPreneurB.getText());
            c.setB_societe_assurance_agence_nom(tfNomSocieteB.getText());
            c.setB_societe_assurance_agence_adresse(tfAdresseSocieteB.getText());


            c.setLocalisation(tfLocalisation.getText());
            c.setTemoins(tfTemoins.getText());


            c.setStationnement_arret(enStationnement.isSelected());
            c.setQuittait_stationnement_arret(quittaitStationnement.isSelected());
            c.setPrenait_stationnement(prenaitStationnement.isSelected());
            c.setSortait_dun_parking_lieu(sortaitParking.isSelected());
            c.setDoublait(doublait.isSelected());
            c.setVirait_droite(viraitDroite.isSelected());
            c.setVirait_gauche(viraitDroite.isSelected());

            sp.add(c);
            System.out.println("Constat ajoutée avec succès !");

            // Afficher une boîte de dialogue de succès
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succès");
            alert.setHeaderText(null);
            alert.setContentText("L'ajout s'est fait avec succès !");
            alert.showAndWait();

        } catch (Exception e) {
            System.err.println("Erreur lors de l'ajout de constat: " + e.getMessage());
        }

    }


    @FXML
    void afficherConstat(ActionEvent event) {


        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherConstat.fxml"));

            Parent root = loader.load();
            // Récupérer le contrôleur de la vue AfficherConstat
            AfficherConstat afficherConstatController = loader.getController();

            // Récupérer tous les constats à partir du service
            ArrayList<Constat> constats = sp.getAll();

            // Mettre à jour l'affichage dans le contrôleur AfficherConstat
            afficherConstatController.afficherConstats(constats);
            tfNomPreneurA.getScene().setRoot(root);


        } catch (IOException e) {
            System.out.println("Error loading AfficherConstat.fxml: " + e.getMessage()); // Error message
        }
    }


    @FXML
    void addImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif")
        );

        // Show open file dialog
        File selectedFile = fileChooser.showOpenDialog(((Button) event.getSource()).getScene().getWindow());
        if (selectedFile != null) {
            try {
                // Load the selected image file
                Image image = new Image(selectedFile.toURI().toString());
                imageView.setImage(image);
            } catch (Exception e) {
                e.printStackTrace();
                // Show an error message if unable to load the image
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Failed to load the image.");
                alert.showAndWait();
            }
        }
    }


    @FXML
    public void initialize() {

        imageView = new ImageView();
    }



}
