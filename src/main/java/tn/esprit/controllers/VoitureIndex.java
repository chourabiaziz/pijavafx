package tn.esprit.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import tn.esprit.models.Voiture;
import tn.esprit.services.ServiceVoiture;
import javafx.scene.Parent;
import javafx.scene.Scene;


import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class VoitureIndex implements Initializable {

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
    private TextField searchField;

    @FXML
    private ImageView imageView;

    private ObservableList<Voiture> data = FXCollections.observableArrayList();
    private ObservableList<Voiture> filteredData = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Ajouter le gestionnaire d'événements pour le bouton "Ajouter"
        // Gestionnaire d'événements pour le bouton "Ajouter"
        ajouterButton.setOnAction(event -> {
            try {
                // Charger le fichier FXML de la page d'ajout de voiture
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/ajouter_voiture.fxml"));
                Parent root = loader.load();

                // Créer une nouvelle scène avec la page d'ajout de voiture
                Scene scene = new Scene(root);

                // Obtenir la fenêtre actuelle à partir de l'événement
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                // Définir la nouvelle scène sur la fenêtre et l'afficher
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        ajouterButton.setOnAction(this::handle);

        loadData();
    }

    private void loadData() {
        // Récupérer les données à partir du service ou de la base de données
        ServiceVoiture service = new ServiceVoiture();
        List<Voiture> allVoitures = service.getAll();
        data.addAll(allVoitures);
        filteredData.addAll(allVoitures);

        // Afficher toutes les voitures initialement
        displayVoitures(filteredData);
    }

    private void displayVoitures(ObservableList<Voiture> voitures) {
        // Implémenter la logique pour afficher les voitures dans votre TilePane
        tilePane.getChildren().clear(); // Effacer les enfants existants

        for (Voiture voiture : voitures) {
            VBox card = new VBox();
            card.setPrefWidth(200);
            card.setPrefHeight(180);
            card.setSpacing(5);
            card.setPadding(new Insets(5));

            Label idLabel = new Label("ID: " + voiture.getId());
            Label marqueLabel = new Label("Marque: " + voiture.getMarque());
            Label modeleLabel = new Label("Modèle: " + voiture.getModele());
            Label numeroSerieLabel = new Label("Numéro de Série: " + voiture.getNumero_serie());
            Label carburantLabel = new Label("Type de Carburant: " + voiture.getType_carburant());
            Label immatriculationLabel = new Label("Numéro d'Immatriculation: " + voiture.getNumero_immatriculation());
            Label kilometrageLabel = new Label("Kilométrage: " + voiture.getKilometrage());
            Label couleurLabel = new Label("Couleur: " + voiture.getCouleur());
            Label prixAchatLabel = new Label("Prix d'Achat: " + voiture.getPrix_achat());
            Label prixActuelLabel = new Label("Prix Actuel: " + voiture.getPrix_actuel());
            Label carteGriseLabel = new Label("Carte Grise: " + voiture.getCarte_grise());
            Label nomImageLabel = new Label("Nom de l'Image: " + voiture.getNom_image());

            card.getChildren().addAll(idLabel, marqueLabel, modeleLabel, numeroSerieLabel, carburantLabel,
                    immatriculationLabel, kilometrageLabel, couleurLabel, prixAchatLabel, prixActuelLabel,
                    carteGriseLabel, nomImageLabel);
            tilePane.getChildren().add(card);
        }
    }

    @FXML
    private void searchFieldChanged() {
        String query = searchField.getText().trim().toLowerCase();

        filteredData.clear(); // Effacer les données filtrées existantes
        if (query.isEmpty()) {
            displayVoitures(data);
        } else {
            for (Voiture voiture : data) {
                if (String.valueOf(voiture.getId()).toLowerCase().contains(query)
                        || voiture.getMarque().toLowerCase().contains(query)
                        || voiture.getModele().toLowerCase().contains(query)
                        || voiture.getNumero_serie().toLowerCase().contains(query)
                        || voiture.getType_carburant().toLowerCase().contains(query)
                        || voiture.getNumero_immatriculation().toLowerCase().contains(query)
                        || String.valueOf(voiture.getKilometrage()).toLowerCase().contains(query)
                        || voiture.getCouleur().toLowerCase().contains(query)
                        || String.valueOf(voiture.getPrix_achat()).toLowerCase().contains(query)
                        || String.valueOf(voiture.getPrix_actuel()).toLowerCase().contains(query)
                        || voiture.getCarte_grise().toLowerCase().contains(query)
                        || voiture.getNom_image().toLowerCase().contains(query)) {
                    filteredData.add(voiture); // Ajouter les voitures correspondantes aux données filtrées
                }
            }
            displayVoitures(filteredData); // Afficher les données filtrées
        }
    }

    private void handle(ActionEvent event) {
        try {
            // Charger le fichier FXML de la page AjouterVoiture.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AjouterVoiture.fxml"));
            Parent root = loader.load();

            // Afficher la nouvelle scène
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void goToAjouterVoiture() {
        try {
            // Charger le fichier FXML de la page AjouterVoiture.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AjouterVoiture.fxml"));
            Parent root = loader.load();

            // Afficher la nouvelle scène
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void goToModifierVoiture(ActionEvent actionEvent) {
        // Récupérer la voiture sélectionnée depuis la liste
        Voiture selectedVoiture = (Voiture) tilePane.getChildren().stream()
                .filter(node -> node instanceof VBox)
                .map(node -> (VBox) node)
                .filter(vBox -> vBox.getStyle().contains("-fx-border-color: dodgerblue")) // Filtrer la voiture sélectionnée
                .map(vBox -> vBox.getChildren().stream()
                        .filter(child -> child instanceof Label)
                        .map(child -> ((Label) child).getText())
                        .toArray(String[]::new))
                .map(strings -> {
                    Voiture voiture = new Voiture();
                    voiture.setId(Integer.parseInt(strings[0].substring(4))); // Récupérer l'ID de la voiture
                    return voiture;
                })
                .findFirst().orElse(null);

        // Vérifier si une voiture est sélectionnée
        if (selectedVoiture != null) {
            try {
                // Charger le fichier FXML de la page de modification de voiture
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/ModifierVoiture.fxml"));
                Parent root = loader.load();

                // Récupérer le contrôleur de la page de modification
                ModifierVoiture controller = loader.getController();

                // Passer les détails de la voiture sélectionnée au contrôleur de la page de modification
                controller.loadVoitureDetails(selectedVoiture);

                // Afficher la nouvelle scène
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // Afficher un message si aucune voiture n'est sélectionnée
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aucune voiture sélectionnée");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez sélectionner une voiture à modifier.");
            alert.showAndWait();
        }
    }

}
