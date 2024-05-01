package tn.esprit.controllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import tn.esprit.models.Assurance;
import tn.esprit.services.ServiceAssurance;

import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;



public class AfficherAssuranceClient implements Initializable {


    @FXML
    private VBox assuranceCardVBox;

    @FXML
    private Button ajouterButton;

    @FXML
    private Button modifierButton;

    @FXML
    private Button supprimerButton;

    private String nom;
    private String adresse;
    private String codePostal;
    private String telephone;
    private String email;

    @FXML
    private TilePane tilePane;

    @FXML
    private AnchorPane mainAnchorPane;

    private final ServiceAssurance serviceAssurance = new ServiceAssurance();


    public void afficherAssurances() {
        List<Assurance> assurances = serviceAssurance.getAll();
        setAssurancesInView(assurances);
    }
    private void setAssurancesInView(List<Assurance> assurances) {
        ObservableList<Node> children = tilePane.getChildren();
        children.clear();
        for (Assurance assurance : assurances) {
            VBox card = new VBox();
            card.setPrefWidth(200);
            card.setPrefHeight(180);
            card.setSpacing(5);
            card.setPadding(new Insets(5));

            Label nomLabel = new Label("Nom: " + assurance.getNom_assurance());
            Label adresseLabel = new Label("Adresse: " + assurance.getAdresse_assurance());
            Label codePostalLabel = new Label("Code Postal: " + assurance.getCode_postal_assurance());
            Label telephoneLabel = new Label("Téléphone: " + assurance.getTel_assurance());
            Label emailLabel = new Label("Email: " + assurance.getEmail_assurance());

            card.getChildren().addAll(nomLabel, adresseLabel, codePostalLabel, telephoneLabel, emailLabel);
            children.add(card);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        InputStream imageStream = getClass().getResourceAsStream("/Image/background.png");
        if (imageStream == null) {
            System.err.println("Resource not found: /resources/background.png");
        } else {
            Image imageBack = new Image(imageStream);
            BackgroundImage backgroundImage = new BackgroundImage(
                    imageBack,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.CENTER,
                    new BackgroundSize(100, 100, true, true, false, true)
            );
            mainAnchorPane.setBackground(new Background(backgroundImage));
        }


        afficherAssurances();    }




}


