package tn.esprit.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import tn.esprit.models.Assurance;
import tn.esprit.services.ServiceAssurance;

import java.io.IOException;


public class AjouterAssurance {
    ServiceAssurance sp  = new ServiceAssurance();

    @FXML
    private TextField tfNom;

    @FXML
    private TextField tfAdresse;

    @FXML
    private TextField tfCodePostal;

    @FXML
    private TextField tfTelephone;

    @FXML
    private TextField tfEmail;

    @FXML
    void afficherAssurance(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherAssurance.fxml"));

        try {
            Parent root = loader.load();
            AfficherAssurance ap = loader.getController();

            ap.afficherAssurances(sp.getAll().toString());
            tfNom.getScene().setRoot(root);


        } catch (IOException e) {
            System.out.println(e.getMessage());
        }


    }

    @FXML
    void ajouterAssurance(ActionEvent event) {

        try {



            Assurance a = new Assurance();

        a.setNom_assurance(tfNom.getText());
        a.setAdresse_assurance(tfAdresse.getText());
        a.setCode_postal_assurance(tfCodePostal.getText());
        a.setTel_assurance(tfTelephone.getText());
        a.setEmail_assurance(tfEmail.getText());

        sp.add(a);
            System.out.println("Assurance ajoutée avec succès !");

// Rediriger vers la page AfficherAssurance
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherAssurance.fxml"));
            Parent root = loader.load();
            AfficherAssurance afficherAssurance = loader.getController();
            afficherAssurance.afficherAssurances("argumentNecessaire"); // Si vous avez une méthode pour afficher les assurances
            tfNom.getScene().setRoot(root);


        } catch (Exception e) {
            System.err.println("Erreur lors de l'ajout de l'assurance: " + e.getMessage());
        }

    }

}
