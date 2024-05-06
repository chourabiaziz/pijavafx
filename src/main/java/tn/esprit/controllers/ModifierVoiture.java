package tn.esprit.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import tn.esprit.models.Voiture;
import tn.esprit.services.ServiceVoiture;

public class ModifierVoiture {

    @FXML
    private TextField marqueField;

    @FXML
    private TextField modeleField;

    @FXML
    private TextField numeroSerieField;

    @FXML
    private TextField typeCarburantField;

    @FXML
    private TextField numeroImmatriculationField;

    @FXML
    private TextField kilometrageField;

    @FXML
    private TextField couleurField;

    @FXML
    private TextField prixAchatField;

    @FXML
    private TextField prixActuelField;

    @FXML
    private TextField carteGriseField;

    private Voiture voiture; // Voiture à modifier
    private ServiceVoiture serviceVoiture; // Service pour gérer les opérations sur les voitures

    // Méthode pour charger les détails de la voiture à modifier
    public void loadVoitureDetails(Voiture voiture) {
        this.voiture = voiture;
        marqueField.setText(voiture.getMarque());
        modeleField.setText(voiture.getModele());
        numeroSerieField.setText(voiture.getNumero_serie());
        typeCarburantField.setText(voiture.getType_carburant());
        numeroImmatriculationField.setText(voiture.getNumero_immatriculation());
        kilometrageField.setText(String.valueOf(voiture.getKilometrage()));
        couleurField.setText(voiture.getCouleur());
        prixAchatField.setText(String.valueOf(voiture.getPrix_achat()));
        prixActuelField.setText(String.valueOf(voiture.getPrix_actuel()));
        carteGriseField.setText(voiture.getCarte_grise());
    }

    // Méthode appelée lors de l'appui sur le bouton "Enregistrer" pour modifier la voiture
    @FXML
    void saveButtonClicked(ActionEvent event) {
        // Mise à jour des détails de la voiture avec les nouvelles valeurs des champs
        voiture.setMarque(marqueField.getText());
        voiture.setModele(modeleField.getText());
        voiture.setNumero_serie(numeroSerieField.getText());
        voiture.setType_carburant(typeCarburantField.getText());
        voiture.setNumero_immatriculation(numeroImmatriculationField.getText());
        voiture.setKilometrage(Integer.parseInt(kilometrageField.getText()));
        voiture.setCouleur(couleurField.getText());
        voiture.setPrix_achat(Double.parseDouble(prixAchatField.getText()));
        voiture.setPrix_actuel(Double.parseDouble(prixActuelField.getText()));
        voiture.setCarte_grise(carteGriseField.getText());

        // Appel de la méthode update du service pour mettre à jour la voiture dans la base de données
        serviceVoiture.update(voiture);

        // Affichage d'une boîte de dialogue pour indiquer que la modification a réussi
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Succès");
        alert.setHeaderText("Voiture modifiée avec succès");
        alert.showAndWait();
    }
}
