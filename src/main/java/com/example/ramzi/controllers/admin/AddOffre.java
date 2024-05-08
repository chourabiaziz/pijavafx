package com.example.ramzi.controllers.admin;

import com.example.ramzi.HelloApplication;
import com.example.ramzi.models.Offre;
import com.example.ramzi.services.OffreServices;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddOffre {
    @FXML
    private TextField AssuranceF;

    @FXML
    private TextField DescriptionF;

    @FXML
    private TextField EmailF;

    @FXML
    private TextField NumF;

    @FXML
    private Label checkAssurance;

    @FXML
    private Label checkEmail;

    @FXML
    private Label checkNum;

    @FXML
    private Label checkdescription;

    @FXML
    private Label checktitre;

    @FXML
    private TextField titreF;
    private OffreServices offreServices = new OffreServices();

    private void showErrorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showSuccessAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Success");
        alert.setContentText(message);
        alert.showAndWait();
    }

    boolean checktitre() {
        if (!titreF.getText().isEmpty()) {
            checktitre.setVisible(false);
            return true;
        } else {
            checktitre.setVisible(true);
            return false;
        }
    }

    boolean checkdescription() {
        if (!DescriptionF.getText().isEmpty()) {
            checkdescription.setVisible(false);
            return true;
        } else {
            checkdescription.setVisible(true);
            return false;
        }
    }

    boolean checkAssurance() {
        if (!AssuranceF.getText().isEmpty()) {
            checkAssurance.setVisible(false);
            return true;
        } else {
            checkAssurance.setVisible(true);
            return false;
        }
    }

    boolean checkEmail() {
        String emailPattern = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}";
        Pattern pattern = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(EmailF.getText());

        if (matcher.matches()) {
            checkEmail.setVisible(false);
            return true;
        } else {
            checkEmail.setVisible(true);
            return false;
        }
    }

    boolean checkNum() {
        String numPattern = "\\d{10}";
        Pattern pattern = Pattern.compile(numPattern);
        Matcher matcher = pattern.matcher(NumF.getText());

        if (matcher.matches()) {
            checkNum.setVisible(false);
            return true;
        } else {
            checkNum.setVisible(true);
            return false;
        }
    }

    @FXML
    void ajouterOffre(ActionEvent event) {
        checktitre(); checkdescription(); checkAssurance(); checkEmail(); checkNum();
        if (checktitre() && checkdescription() && checkAssurance() && checkEmail() && checkNum()) {
            Offre offre = new Offre();
            offre.setTitre(titreF.getText());
            offre.setDescription(DescriptionF.getText());
            offre.setAssurance(AssuranceF.getText());
            offre.setEmail_assurance(EmailF.getText());
            offre.setNum_assurance(NumF.getText());

            try {
                offreServices.ajouter(offre);
                showSuccessAlert("Offre ajoutée avec succès");
                Scene scene1 = titreF.getScene();
                Stage stage1 = (Stage) scene1.getWindow();
                stage1.close();
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/admin/ListeOffre.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                Stage stage=new Stage();
                stage.setTitle("");
                stage.setScene(scene);
                stage.show();
            } catch (SQLException e) {
                showErrorAlert("Erreur lors de l'ajout de l'offre : " + e.getMessage());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    @FXML
    void annuler() throws IOException {
        Scene scene1 = titreF.getScene();
        Stage stage1 = (Stage) scene1.getWindow();
        stage1.close();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/admin/ListeOffre.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage=new Stage();
        stage.setTitle("");
        stage.setScene(scene);
        stage.show();
    }
}


