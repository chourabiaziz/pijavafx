package tn.esprit.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import tn.esprit.models.Atelier;
import tn.esprit.services.ServiceAtelier;

import java.sql.Time;

public class AjouterAtelierAdmin {

    @FXML
    private TextField nomTextField;

    @FXML
    private TextField adresseTextField;

    @FXML
    private TextField telephoneTextField;

    @FXML
    private TextField specialiteTextField;

    @FXML
    private TextField avisTextField;

    @FXML
    private TextField heureOuvertureTextField;

    @FXML
    private TextField heureFermetureTextField;

    private ServiceAtelier serviceAtelier;

    public AjouterAtelierAdmin() {
        serviceAtelier = new ServiceAtelier();
    }

    @FXML
    void ajouterAtelier(ActionEvent event) {
        // Récupérer les données saisies par l'utilisateur
        String nom = nomTextField.getText();
        String adresse = adresseTextField.getText();
        String telephone = telephoneTextField.getText();
        String specialite = specialiteTextField.getText();
        String avis = avisTextField.getText();

        // Vérifier si les champs obligatoires sont vides
        if (nom.isEmpty() || adresse.isEmpty() || telephone.isEmpty() || specialite.isEmpty()) {
            // Afficher un message d'erreur si un champ obligatoire est vide
            showAlert("Erreur", "Veuillez remplir tous les champs obligatoires !");
            return;
        }

        // Récupérer les valeurs saisies dans les champs de texte pour les heures d'ouverture et de fermeture
        String heureOuvertureText = heureOuvertureTextField.getText();
        String heureFermetureText = heureFermetureTextField.getText();

        // Valider les entrées pour les heures d'ouverture et de fermeture
        if (validateHeure(heureOuvertureText) && validateHeure(heureFermetureText)) {
            // Convertir les valeurs en objets Time
            Time heureOuverture = Time.valueOf(heureOuvertureText + ":00");
            Time heureFermeture = Time.valueOf(heureFermetureText + ":00");

            // Créer un objet Atelier avec les données saisies
            Atelier atelier = new Atelier(0, nom, adresse, telephone, specialite, avis, heureOuverture, heureFermeture);

            // Ajouter l'atelier à la base de données en utilisant le service
            serviceAtelier.add(atelier);
            showAlert("Succès", "Atelier ajouté avec succès !");
        } else {
            // Afficher un message d'erreur si les entrées pour les heures d'ouverture et de fermeture ne sont pas valides
            showAlert("Erreur", "Les heures saisies ne sont pas valides. Assurez-vous de respecter le format HH:MM.");
        }
    }

    // Méthode pour valider le format des heures (HH:MM)
    private boolean validateHeure(String heure) {
        return heure.matches("^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$");
    }

    // Méthode pour afficher une boîte de dialogue avec un message
    private void showAlert(String title, String contentText) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contentText);
        alert.showAndWait();
    }
    @FXML
    public void allerAListeAteliers(ActionEvent event) {
        // Redirection vers la liste des ateliers
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Liste des Ateliers");
        alert.setHeaderText(null);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AtelierIndexAdmin.fxml"));
        alert.showAndWait();
    }
}
