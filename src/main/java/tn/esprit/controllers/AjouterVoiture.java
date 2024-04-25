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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tn.esprit.models.Voiture;
import tn.esprit.services.ServiceVoiture ;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AjouterVoiture<VoitureIndex> implements Initializable {

    @FXML
    private TextField id;

    @FXML
    private DatePicker annee_fabrication;

    @FXML
    private TextField kilometrage;

    @FXML
    private TextField prix_achat;

    @FXML
    private TextField prix_actuel;

    @FXML
    private TextField marque;

    @FXML
    private TextField modele;

    @FXML
    private TextField numero_serie;

    @FXML
    private TextField type_carburant;

    @FXML
    private TextField numero_immatriculation;

    @FXML
    private TextField couleur;

    @FXML
    private TextField carte_grise;

    @FXML
    private TextField nom_image;

    @FXML
    private Button submit;

    @FXML
    void submit(ActionEvent event) {
        Voiture voiture = new Voiture();
        ServiceVoiture service = new ServiceVoiture(); // Assurez-vous d'avoir une classe ServiceVoiture pour gérer les opérations sur les voitures

        // Génération automatique de l'ID
        int newId = generateNewId(); // Méthode pour générer automatiquement un nouvel ID
        voiture.setId(newId);

        // Assignation des autres valeurs
        voiture.setKilometrage(Integer.parseInt(kilometrage.getText()));
        voiture.setPrix_achat(Double.parseDouble(prix_achat.getText()));
        voiture.setPrix_actuel(Double.parseDouble(prix_actuel.getText()));
        voiture.setMarque(marque.getText());
        voiture.setModele(modele.getText());
        voiture.setNumero_serie(numero_serie.getText());
        voiture.setType_carburant(type_carburant.getText());
        voiture.setNumero_immatriculation(numero_immatriculation.getText());
        voiture.setCouleur(couleur.getText());
        voiture.setCarte_grise(carte_grise.getText());
        voiture.setNom_image(nom_image.getText());
        voiture.setDate_achat(java.sql.Date.valueOf(annee_fabrication.getValue()));

        try {
            service.add(voiture);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succès");
            alert.setHeaderText("Voiture ajoutée avec succès");
            alert.showAndWait();
        } catch(Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Échec de l'ajout de la voiture");
            alert.setContentText("Une erreur est survenue lors de l'ajout de la voiture. Veuillez réessayer.");
            alert.showAndWait();
        }
    }

    private int generateNewId() {
        // Implémentez ici la logique pour générer automatiquement un nouvel ID
        // Par exemple, vous pouvez récupérer le dernier ID de la base de données et l'incrémenter de 1
        // Pour l'instant, nous utilisons simplement une valeur aléatoire pour l'ID
        return (int) (Math.random() * 1000); // Remplacez cette logique par votre propre logique de génération d'ID
    }


    @FXML
    void goToVoitureList(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/VoitureIndex.fxml"));
        Parent root ;
        try {
            Node source = (Node) event.getSource();
            root = loader.load();
            System.out.println("Le fichier FXML a été chargé avec succès.");
            VoitureIndex controller = loader.getController();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.setTitle("Liste des Voitures");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void goToAjouterPanne(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ajouterpanne.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    Button btn1;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    @FXML
    void reset(ActionEvent event) {
        // Réinitialiser les champs de saisie
        marque.clear();
        modele.clear();
        numero_serie.clear();
        type_carburant.clear();
        numero_immatriculation.clear();
        couleur.clear();
        prix_achat.clear();
        annee_fabrication.setValue(null); // Réinitialiser la date d'achat
    }

    public void goToPanneList(ActionEvent actionEvent) {
        try {
            // Charger le fichier FXML de la liste des pannes (PanneIndex.fxml)
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/PanneIndex.fxml"));
            Parent root = loader.load();

            // Afficher la nouvelle scène
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void goToAjouterAtelier(ActionEvent actionEvent) {
        try {
            // Charger le fichier Fxml pour ajouter atelier
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ajouterAtelier.fxml"));
            Parent root = loader.load();

            // Afficher la nouvelle scène
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
