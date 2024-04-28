package tn.esprit.controllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
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


    @FXML
    private Button modifierButton;

    @FXML
    private Button ajouterConstatButton;


    private ServiceConstat serviceConstat;
    @FXML
    private VBox constatsContainer;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        enStationnement.setDisable(true);
        quittaitStationnement.setDisable(true);
        prenaitStationnement.setDisable(true);
        sortaitParking.setDisable(true);
        doublait.setDisable(true);
        viraitDroite.setDisable(true);
        viraitGauche.setDisable(true);

        this.serviceConstat = new ServiceConstat();

    }

    public void afficherConstat(Constat constat) {
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


    public void afficherTousLesConstats(ArrayList<Constat> constats) {
        constatsContainer.getChildren().clear();

        for (Constat constat : constats) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherConstat.fxml"));
                Parent root = loader.load();
                AfficherConstat controller = loader.getController();
                controller.afficherConstat(constat);

                // Créer le bouton Supprimer
                Button supprimerButton = new Button("Supprimer");
                supprimerButton.setOnAction(event -> {
                    Constat constatToDelete = constat;

                    System.out.println("Deleting constat with ID: " + constatToDelete.getId());

                    ServiceConstat serviceConstat = new ServiceConstat();
                    boolean deleteSuccess = serviceConstat.delete(constatToDelete);

                    if (deleteSuccess) {
                        showAlert(Alert.AlertType.INFORMATION, "Succès", "Suppression réussie", "Le constat a été supprimé avec succès.");
                    } else {
                        showAlert(Alert.AlertType.ERROR, "Erreur", "Échec de la suppression", "La suppression du constat a échoué.");
                    }
                });

                constatsContainer.getChildren().add(supprimerButton);
                constatsContainer.getChildren().add(root);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @FXML
    private void handleAjouterConstatButtonAction(ActionEvent event ) {
        try {
            // Charger le fichier FXML de la page AjouterConstat.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AjouterConstat.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);

            Stage stage = (Stage) ajouterConstatButton.getScene().getWindow();
            stage.close();

            // Afficher la nouvelle scène
            Stage primaryStage = new Stage();
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


//    @FXML
//    private void handleModifierButtonAction(ActionEvent event) {
//
//        try {
//            // Charger le fichier FXML de la page ModifierConstat.fxml
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ModifierConstat.fxml"));
//            Parent root = loader.load();
//            ModifierConstat controller = loader.getController();
//            controller.setId(constat.getId());
//            Scene scene = new Scene(root);
//
//            Stage stage = (Stage) modifierButton.getScene().getWindow();
//            stage.close();
//
//            // Afficher la nouvelle scène
//            Stage primaryStage = new Stage();
//            primaryStage.setScene(scene);
//            primaryStage.show();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    private void showAlert(Alert.AlertType alertType, String title, String headerText, String contentText) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }


/*
    public void handleSupprimerButtonAction(javafx.event.ActionEvent actionEvent) {
        Constat constatToDelete = constat;
        constatToDelete.setId(constat.getId());

        System.out.println("Deleting constat with ID: " + constatToDelete.getId());

        // Create an instance of the ServiceAssurance class
        ServiceConstat serviceConstat = new ServiceConstat();

        System.out.println("constatToDelete id: " + constatToDelete.getId());

        boolean deleteSuccess = serviceConstat.delete(constatToDelete);
        if (deleteSuccess) {
            showAlert(Alert.AlertType.INFORMATION, "Succès", "Suppression réussie", "Le constat a été supprimé avec succès.");
        } else {
            showAlert(Alert.AlertType.ERROR, "Erreur", "Échec de la suppression", "La suppression du constat a échoué.");
        }
    }
*/

}

