package tn.esprit.controllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import tn.esprit.models.Assurance;
import tn.esprit.models.Constat;
import tn.esprit.services.ServiceAssurance;
import tn.esprit.services.ServiceConstat;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AfficherConstat  implements Initializable {
@FXML
    private Label nomPreneurlabelA;
@FXML
    private Label prenomPreneurlabelA;
@FXML
    private Label telPreneurLabelA;
@FXML
    private Label marqueLabelA;
@FXML
    private Label immatriculationLabelA;
@FXML
    private Label nomSocieteLabelA;
@FXML
    private Label adresseSocietelabelA;





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
    private Label nomPreneurlabelB;
@FXML
    private Label prenomPreneurlabelB ;
@FXML
    private Label telPreneurLabelB;
@FXML
    private Label marqueLabelB;
@FXML
    private Label immatriculationLabelB;
@FXML
    private Label nomSocieteLabelB;
@FXML
    private Label adresseSocietelabelB;
@FXML
    private Label localisationLabel;
@FXML
    private Label temoinsLabel;





          /*  Label nomPreneurALabel = new Label("NomPreneurA: " + constat.getA_preneur_nom());
            Label PrenomPreneurALabel = new Label("PrenomPreneurA: " + constat.getA_preneur_prenom());
            Label TelPreneurALabel = new Label("TelPreneurA " + constat.getA_preneur_tel());
            Label MarquePreneurALabel = new Label("MarquePreneurA " + constat.getA_vehicule_moteur_marque());
            Label ImmatriculationPreneurALabel = new Label("ImmatriculationPreneurA " + constat.getA_vehicule_moteur_num_immatriculation());*/


/*
            Button modifierButton = new Button("Modifier");
            modifierButton.setOnAction(event -> {
                try {
                    Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    currentStage.close();

                    // Charger le fichier FXML de la page ModifierAssurance.fxml
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/ModifierConstat.fxml"));
                    Parent root = loader.load();

                    // Afficher la nouvelle scène
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });*/

          /*  Button supprimerButton = new Button("Supprimer");
            supprimerButton.setOnAction(event -> {
                // Retrieve the assurance associated with this button
                Constat constatToDelete = constat;

                // Create an instance of the ServiceAssurance class
                ServiceConstat serviceConstat1 = new ServiceConstat();

                // Perform the deletion operation
                boolean deleteSuccess = serviceConstat.delete(constatToDelete);
                if (deleteSuccess) {
                    // If deletion is successful, remove the card from the UI
                    children.remove(card);
                    System.out.println("Constat supprimée avec succès.");
                } else {
                    System.out.println("Échec de la suppression de constat.");
                }
            });*/


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        enStationnement.setDisable(true);
        quittaitStationnement.setDisable(true);
        prenaitStationnement.setDisable(true);
        sortaitParking.setDisable(true);
        doublait.setDisable(true);
        viraitDroite.setDisable(true);
        viraitGauche.setDisable(true);
    }

    public void afficherConstats(ArrayList<Constat> constats) {
        if (!constats.isEmpty()) {
            Constat constat = constats.get(constats.size() - 1);

            nomPreneurlabelA.setText(constat.getA_preneur_nom());
            prenomPreneurlabelA.setText(constat.getA_preneur_prenom());
            telPreneurLabelA.setText(constat.getA_preneur_tel());
            marqueLabelA.setText(constat.getA_vehicule_moteur_marque());
            immatriculationLabelA.setText(constat.getA_vehicule_moteur_num_immatriculation());
            nomSocieteLabelA.setText(constat.getA_societe_assurance_agence_nom());
            adresseSocietelabelA.setText(constat.getA_societe_assurance_agence_adresse());

            nomPreneurlabelB.setText(constat.getB_preneur_nom());
            prenomPreneurlabelB.setText(constat.getB_preneur_prenom());
            telPreneurLabelB.setText(constat.getB_preneur_tel());
            marqueLabelB.setText(constat.getB_vehicule_moteur_marque());
            immatriculationLabelB.setText(constat.getB_vehicule_moteur_num_immatriculation());
            nomSocieteLabelB.setText(constat.getB_societe_assurance_agence_nom());
            adresseSocietelabelB.setText(constat.getB_societe_assurance_agence_adresse());


            localisationLabel.setText(constat.getLocalisation());
            temoinsLabel.setText(constat.getTemoins());

            enStationnement.setSelected(constat.isStationnement_arret());
            quittaitStationnement.setSelected(constat.isQuittait_stationnement_arret());
            prenaitStationnement.setSelected(constat.isPrenait_stationnement());
            sortaitParking.setSelected(constat.isSortait_dun_parking_lieu());
            doublait.setSelected(constat.isDoublait());
            viraitDroite.setSelected(constat.isVirait_droite());
            viraitGauche.setSelected(constat.isVirait_gauche());

        }
    }


    }
