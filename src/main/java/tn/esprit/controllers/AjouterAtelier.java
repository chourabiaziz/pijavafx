package tn.esprit.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
//        Time heureOuverture = Time.valueOf(heureOuverturePicker.getValue());
//        Time heureFermeture = Time.valueOf(heureFermeturePicker.getValue());

        // Créer un objet Atelier avec les données saisies
 //       Atelier atelier = new Atelier(0, nom, adresse, telephone, specialite, avis, heureOuverture, heureFermeture);

        // Ajouter l'atelier à la base de données en utilisant le service
//        serviceAtelier.add(atelier);
    }
}
