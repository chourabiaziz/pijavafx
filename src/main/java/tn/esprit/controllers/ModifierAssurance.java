package tn.esprit.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import tn.esprit.models.Assurance;
import tn.esprit.services.ServiceAssurance;

public class ModifierAssurance {

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

    private Assurance assurance; // L'objet Assurance que vous souhaitez modifier

    public void initAssurance(Assurance assurance) {
        this.assurance = assurance;
        // Afficher les données de l'assurance dans les champs correspondants
        tfNom.setText(assurance.getNom_assurance());
        tfAdresse.setText(assurance.getAdresse_assurance());
        tfCodePostal.setText(assurance.getCode_postal_assurance());
        tfTelephone.setText(assurance.getTel_assurance());
        tfEmail.setText(assurance.getEmail_assurance());
    }
    @FXML
    void modifierAssurance(ActionEvent event) {
        // Mettre à jour les données de l'objet Assurance avec les nouvelles valeurs des champs
        assurance.setNom_assurance(tfNom.getText());
        assurance.setAdresse_assurance(tfAdresse.getText());
        assurance.setCode_postal_assurance(tfCodePostal.getText());
        assurance.setTel_assurance(tfTelephone.getText());
        assurance.setEmail_assurance(tfEmail.getText());

        // Appeler la méthode pour mettre à jour l'assurance
        ServiceAssurance serviceAssurance = new ServiceAssurance();
        serviceAssurance.update(assurance);

    }


}
