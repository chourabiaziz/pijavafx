package com.example.ramzi.controllers.user;

import com.example.ramzi.HelloApplication;
import com.example.ramzi.models.Dvis;
import com.example.ramzi.services.DvisServices;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddDvis {

    @FXML
    private Label checkcivilite;

    @FXML
    private Label checkcouverture;

    @FXML
    private Label checkcylindre;

    @FXML
    private Label checkdescription;

    @FXML
    private Label checkemail;

    @FXML
    private Label checkmatricule;

    @FXML
    private Label checknom;

    @FXML
    private Label checkprenom;

    @FXML
    private Label checkpuissance;

    @FXML
    private Label checkstatut;

    @FXML
    private Label checktelephone;

    @FXML
    private Label checkvoiture;

    @FXML
    private TextField civiliteF;

    @FXML
    private TextField couvertureF;

    @FXML
    private Spinner<Integer> cylindreF;

    @FXML
    private TextField descriptionF;

    @FXML
    private TextField emailF;

    @FXML
    private TextField matriculeF;

    @FXML
    private TextField nomF;

    @FXML
    private TextField prenomF;

    @FXML
    private Spinner<Integer> puissanceF;

    @FXML
    private TextField statutF;

    @FXML
    private TextField telephoneF;

    @FXML
    private TextField voitureF;
    int offreId=0;
private final DvisServices dvisServices=new DvisServices();
    @FXML
    public void initialize(int offreId) {
        this.offreId=offreId;
        puissanceF.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, Integer.MAX_VALUE, 1));
        cylindreF.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, Integer.MAX_VALUE, 1));
    }
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

    boolean validateCivilite() {
        if (!civiliteF.getText().isEmpty()) {
            checkcivilite.setVisible(false);
            return true;
        } else {
            checkcivilite.setVisible(true);
            return false;
        }
    }

    boolean validateCouverture() {
        if (!couvertureF.getText().isEmpty()) {
            checkcouverture.setVisible(false);
            return true;
        } else {
            checkcouverture.setVisible(true);
            return false;
        }
    }

    boolean validateCylindre() {
        // Cylindre doit être supérieur à zéro
        if (cylindreF.getValue() > 0) {
            checkcylindre.setVisible(false);
            return true;
        } else {
            checkcylindre.setVisible(true);
            return false;
        }
    }

    boolean validateDescription() {
        if (!descriptionF.getText().isEmpty()) {
            checkdescription.setVisible(false);
            return true;
        } else {
            checkdescription.setVisible(true);
            return false;
        }
    }

    boolean validateEmail() {
        String emailPattern = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}";
        Pattern pattern = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(emailF.getText());

        if (matcher.matches()) {
            checkemail.setVisible(false);
            return true;
        } else {
            checkemail.setVisible(true);
            return false;
        }
    }

    boolean validateMatricule() {
        if (!matriculeF.getText().isEmpty()) {
            checkmatricule.setVisible(false);
            return true;
        } else {
            checkmatricule.setVisible(true);
            return false;
        }
    }

    boolean validateNom() {
        if (!nomF.getText().isEmpty()) {
            checknom.setVisible(false);
            return true;
        } else {
            checknom.setVisible(true);
            return false;
        }
    }

    boolean validatePrenom() {
        if (!prenomF.getText().isEmpty()) {
            checkprenom.setVisible(false);
            return true;
        } else {
            checkprenom.setVisible(true);
            return false;
        }
    }

    boolean validatePuissance() {
        // Puissance doit être supérieure à zéro
        if (puissanceF.getValue() > 0) {
            checkpuissance.setVisible(false);
            return true;
        } else {
            checkpuissance.setVisible(true);
            return false;
        }
    }

    boolean validateStatut() {
        if (!statutF.getText().isEmpty()) {
            checkstatut.setVisible(false);
            return true;
        } else {
            checkstatut.setVisible(true);
            return false;
        }
    }

    boolean validateTelephone() {
        String numPattern = "\\d{10}";
        Pattern pattern = Pattern.compile(numPattern);
        Matcher matcher = pattern.matcher(telephoneF.getText());

        if (matcher.matches()) {
            checktelephone.setVisible(false);
            return true;
        } else {
            checktelephone.setVisible(true);
            return false;
        }
    }

    boolean validateVoiture() {
        if (!voitureF.getText().isEmpty()) {
            checkvoiture.setVisible(false);
            return true;
        } else {
            checkvoiture.setVisible(true);
            return false;
        }
    }

    @FXML
    void Ajouter() {
        validateCivilite() ; validateCouverture() ; validateCylindre() ; validateDescription() ;
        validateEmail() ; validateMatricule() ; validateNom() ; validatePrenom() ;
        validatePuissance() ; validateStatut() ; validateTelephone() ; validateVoiture();
        if (validateCivilite() && validateCouverture() && validateCylindre() && validateDescription() &&
                validateEmail() && validateMatricule() && validateNom() && validatePrenom() &&
                validatePuissance() && validateStatut() && validateTelephone() && validateVoiture()) {
            Dvis dvis=new Dvis();
            dvis.setNom(nomF.getText());
            dvis.setPrenom(prenomF.getText());
            dvis.setCivilite(civiliteF.getText());
            dvis.setTelephone(Integer.parseInt(telephoneF.getText()));
            dvis.setEmail(emailF.getText());
            dvis.setVoiture(voitureF.getText());
            dvis.setMatricule(matriculeF.getText());
            dvis.setPuissance(puissanceF.getValue());
            dvis.setCylindre(cylindreF.getValue());
            dvis.setDescription(descriptionF.getText());
            dvis.setStatut(statutF.getText());
            dvis.setCouverture(couvertureF.getText());
            dvis.setOffre_id(this.offreId);
            try {
                dvisServices.ajouter(dvis);
                showSuccessAlert("Dvis ajouté avec succès !");
                Scene scene1 = couvertureF.getScene();
                Stage stage1 = (Stage) scene1.getWindow();
                stage1.close();
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/user/ListDevis.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                Stage stage=new Stage();
                stage.setTitle("");
                stage.setScene(scene);
                stage.show();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        } else {
            showErrorAlert("Veuillez remplir tous les champs correctement.");
        }
    }
    @FXML
    void annuler() throws IOException {
        Scene scene1 = couvertureF.getScene();
        Stage stage1 = (Stage) scene1.getWindow();
        stage1.close();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/user/ListDevis.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage=new Stage();
        stage.setTitle("");
        stage.setScene(scene);
        stage.show();
    }
}
