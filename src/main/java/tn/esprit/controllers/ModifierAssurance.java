package tn.esprit.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import tn.esprit.models.Assurance;
import tn.esprit.services.ServiceAssurance;

import java.sql.*;
import java.util.ArrayList;

public class ModifierAssurance {


    @FXML
    private TextField nomTextField;

    @FXML
    private TextField codePostalTextField;

    @FXML
    private TextField adresseTextField;

    @FXML
    private TextField telephoneTextField;

    @FXML
    private TextField emailTextField;

    private ServiceAssurance serviceAssurance;
    private Assurance assurance;



    public ModifierAssurance() {
        this.serviceAssurance = new ServiceAssurance();
    }

    public void initialize() {
        int assuranceId = 1; // Par exemple, vous pouvez définir l'identifiant ici ou le passer comme paramètre depuis la vue précédente

        // Récupérer les données de l'assurance depuis la base de données en utilisant son identifiant
        assurance = serviceAssurance.getById(assuranceId);

        // Remplir les champs TextField avec les données de l'assurance
        nomTextField.setText(assurance.getNom_assurance());
        codePostalTextField.setText(assurance.getCode_postal_assurance());
        adresseTextField.setText(assurance.getAdresse_assurance());
        telephoneTextField.setText(assurance.getTel_assurance());
        emailTextField.setText(assurance.getEmail_assurance());
    }
    public void modifierAssurance(ActionEvent actionEvent) {
        String nom = nomTextField.getText();
        String adresse = adresseTextField.getText();
        String codePostal = codePostalTextField.getText();
        String telephone = telephoneTextField.getText();
        String email = emailTextField.getText();


        // Create an Assurance object with the updated values
        assurance.setNom_assurance(nom);
        assurance.setAdresse_assurance(adresse);
        assurance.setCode_postal_assurance(codePostal);
        assurance.setTel_assurance(telephone);
        assurance.setEmail_assurance(email);

        // Update the assurance in the database
        boolean updateSuccess = serviceAssurance.update(assurance);
        if (updateSuccess) {
            System.out.println("Assurance mise à jour avec succès.");
        } else {
            System.out.println("Échec de la mise à jour de l'assurance.");
        }
    }

    }

