package tn.esprit.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import tn.esprit.models.Personne;
import tn.esprit.services.ServicePersonne;

import java.io.IOException;

public class AjouterPersonne {
    ServicePersonne sp  = new ServicePersonne();
    @FXML
    private TextField tfAge;

    @FXML
    private TextField tfNom;

    @FXML
    private TextField tfPrenom;
    @FXML
    void affichierPerssone(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AffichierPersonne.fxml"));

        try {
            Parent root = loader.load();
            AffichierPersonne ap = loader.getController();

            ap.setLbPersones(sp.getAll().toString());
            tfAge.getScene().setRoot(root);


        } catch (IOException e) {
            System.out.println(e.getMessage());
        }


    }
    @FXML
    void ajouterPersonne(ActionEvent event) {

        Personne p = new Personne();

        p.setNom(tfNom.getText());
        p.setPrenom(tfPrenom.getText());
        p.setAge(Integer.parseInt(tfAge.getText()));

        sp.add(p);

    }

}
