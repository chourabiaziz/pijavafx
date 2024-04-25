package tn.esprit.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import tn.esprit.models.Atelier;
import tn.esprit.services.ServiceAtelier;

import java.sql.Time;

public class AjouterAtelier {

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

    public AjouterAtelier() {
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
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succès");
            alert.setHeaderText("Atelier ajoutée avec succès");
            alert.showAndWait();
        } else {
            // Afficher un message d'erreur si les entrées pour les heures d'ouverture et de fermeture ne sont pas valides
            System.out.println("Les heures saisies ne sont pas valides. Assurez-vous de respecter le format HH:MM.");
        }
    }

    // Méthode pour valider le format des heures (HH:MM)
    private boolean validateHeure(String heure) {
        return heure.matches("^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$");
    }
}
