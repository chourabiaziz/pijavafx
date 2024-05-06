package tn.esprit.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

public class acceuil {
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

    public void goToAtelierList(ActionEvent actionEvent) {
        try {
            // Charger le fichier FXML de la liste des pannes (PanneIndex.fxml)
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/atelierIndex.fxml"));
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



    private void showAlert(Alert.AlertType alertType, String title, String headerText, String contentText) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.show();
    }

    public void goToModifierVoiture(ActionEvent actionEvent) {
        try {
            // Charger le fichier FXML de la liste des pannes (PanneIndex.fxml)
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/VoitureEdit.fxml"));
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

    public void goToSupprimerVoiture(ActionEvent actionEvent) {

    }

    public void goToAjouteVoiture(ActionEvent actionEvent) {
        try {
            // Charger le fichier FXML de la liste des pannes (PanneIndex.fxml)
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AjouterVoiture.fxml"));
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

    public void goToAjouterAteliert(ActionEvent actionEvent) {
        try {
            // Charger le fichier FXML de la liste des pannes (PanneIndex.fxml)
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

//    public void calculateMean(ActionEvent actionEvent) {
//        try {
//            // Charger le fichier FXML de la liste des pannes (PanneIndex.fxml)
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("/StatsView.fxml"));
//            Parent root = loader.load();
//            // Afficher la nouvelle scène
//            Scene scene = new Scene(root);
//            Stage stage = new Stage();
//            stage.setScene(scene);
//            stage.show();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    public void handleRequest(ActionEvent actionEvent) {
        try {
            // Charger le fichier FXML de la liste des pannes (PanneIndex.fxml)
            //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ChatBot.fxml"));
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/chatbotPage.fxml"));
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

    public void goToAccueil(ActionEvent actionEvent) {
        try {
            // Charger le fichier FXML de la liste des pannes (PanneIndex.fxml)
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/acceuil.fxml"));
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
