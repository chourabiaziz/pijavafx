package tn.esprit.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import tn.esprit.models.Voiture;
import tn.esprit.services.ServiceVoiture;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AjouterVoiture<VoitureIndex> implements Initializable {
    @FXML
    private BarChart<String, Number> barChart;



    @FXML
    private TextField id;

    @FXML
    private DatePicker annee_fabrication;

    @FXML
    private TextField kilometrage;

    @FXML
    private TextField prix_achat;

    @FXML
    private TextField prix_actuel;

    @FXML
    private TextField marque;

    @FXML
    private TextField modele;

    @FXML
    private TextField numero_serie;

    @FXML
    private TextField type_carburant;

    @FXML
    private TextField numero_immatriculation;

    @FXML
    private TextField couleur;

    @FXML
    private TextField carte_grise;

    @FXML
    private TextField nom_image;

    @FXML
    private Button submit;

    @FXML
    void submit(ActionEvent event) {
        // Vérifier si les champs obligatoires sont vides
        if (marque.getText().isEmpty() || modele.getText().isEmpty() || kilometrage.getText().isEmpty() ||
                prix_achat.getText().isEmpty() || prix_actuel.getText().isEmpty() || numero_serie.getText().isEmpty() ||
                type_carburant.getText().isEmpty() || numero_immatriculation.getText().isEmpty() || couleur.getText().isEmpty() ||
                carte_grise.getText().isEmpty() || nom_image.getText().isEmpty() || annee_fabrication.getValue() == null) {
            // Afficher un message d'erreur si un champ obligatoire est vide
            showAlert(Alert.AlertType.ERROR, "Erreur", null, "Veuillez remplir tous les champs obligatoires !");
            return;
        }

        // Vérifier le format du kilométrage
        if (!kilometrage.getText().matches("\\d+")) {
            showAlert(Alert.AlertType.ERROR, "Erreur", null, "Le kilométrage doit être un nombre entier positif !");
            return;
        }

        // Vérifier le format des prix
        if (!prix_achat.getText().matches("\\d+(\\.\\d+)?") || !prix_actuel.getText().matches("\\d+(\\.\\d+)?")) {
            showAlert(Alert.AlertType.ERROR, "Erreur", null, "Les prix doivent être des nombres décimaux positifs !");
            return;
        }

        // Vérifier le format de l'année de fabrication
        // (assumant que l'année doit être dans le passé)
        if (annee_fabrication.getValue().getYear() >= java.time.LocalDate.now().getYear()) {
            showAlert(Alert.AlertType.ERROR, "Erreur", null, "L'année de fabrication doit être dans le passé !");
            return;
        }

        // Si toutes les vérifications passent, ajouter la voiture
        Voiture voiture = new Voiture();
        ServiceVoiture service = new ServiceVoiture(); // Assurez-vous d'avoir une classe ServiceVoiture pour gérer les opérations sur les voitures

        // Génération automatique de l'ID
        int newId = generateNewId(); // Méthode pour générer automatiquement un nouvel ID
        voiture.setId(newId);

        // Assignation des autres valeurs
        voiture.setKilometrage(Integer.parseInt(kilometrage.getText()));
        voiture.setPrix_achat(Double.parseDouble(prix_achat.getText()));
        voiture.setPrix_actuel(Double.parseDouble(prix_actuel.getText()));
        voiture.setMarque(marque.getText());
        voiture.setModele(modele.getText());
        voiture.setNumero_serie(numero_serie.getText());
        voiture.setType_carburant(type_carburant.getText());
        voiture.setNumero_immatriculation(numero_immatriculation.getText());
        voiture.setCouleur(couleur.getText());
        voiture.setCarte_grise(carte_grise.getText());
        voiture.setNom_image(nom_image.getText());
        voiture.setDate_achat(java.sql.Date.valueOf(annee_fabrication.getValue()));

        try {
            service.add(voiture);
            showAlert(Alert.AlertType.INFORMATION, "Succès", null, "Voiture ajoutée avec succès !");
        } catch(Exception e) {
            showAlert(Alert.AlertType.ERROR, "Erreur", null, "Échec de l'ajout de la voiture. Veuillez réessayer.");
        }
    }

    private int generateNewId() {
        // Implémentez ici la logique pour générer automatiquement un nouvel ID
        // Par exemple, vous pouvez récupérer le dernier ID de la base de données et l'incrémenter de 1
        // Pour l'instant, nous utilisons simplement une valeur aléatoire pour l'ID
        return (int) (Math.random() * 1000); // Remplacez cette logique par votre propre logique de génération d'ID
    }

    @FXML
    void goToVoitureList(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/VoitureIndex.fxml"));
        Parent root ;
        try {
            Node source = (Node) event.getSource();
            root = loader.load();
            System.out.println("Le fichier FXML a été chargé avec succès.");
            VoitureIndex controller = loader.getController();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.setTitle("Liste des Voitures");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void goToAjouterPanne(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ajouterpanne.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    Button btn1;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    void reset(ActionEvent event) {
        // Réinitialiser les champs de saisie
        marque.clear();
        modele.clear();
        numero_serie.clear();
        type_carburant.clear();
        numero_immatriculation.clear();
        couleur.clear();
        prix_achat.clear();
        annee_fabrication.setValue(null); // Réinitialiser la date d'achat
    }

    public void goToPanneList(ActionEvent actionEvent) {
        try {
            // Charger le fichier FXML de la liste des pannes (PanneIndex.fxml)
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/PanneIndex.fxml"));
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

    public void goToAjouterAtelier(ActionEvent actionEvent) {
        try {
            // Charger le fichier Fxml pour ajouter atelier
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ajouterAtelier.fxml"));
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

    public void goToAtelierList(ActionEvent actionEvent) {
        try {
            // Charger le fichier FXML de la liste des pannes (PanneIndex.fxml)
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/atelierIndex.fxml"));
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
    public void openChatbotPage() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/path/to/fxml/ChatbotView.fxml"));
            VBox chatbotPane = fxmlLoader.load();
            Scene scene = new Scene(chatbotPane);
            Stage stage = new Stage();
            stage.setTitle("Chatbot");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String headerText, String contentText) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.show();
    }

    public void goToModifierVoiture(ActionEvent actionEvent) {
        try {
            // Charger le fichier FXML de la liste des pannes (PanneIndex.fxml)
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/VoitureEdit.fxml"));
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

    public void goToSupprimerVoiture(ActionEvent actionEvent) {

    }

    public void goToAjouteVoiture(ActionEvent actionEvent) {
        try {
            // Charger le fichier FXML de la liste des pannes (PanneIndex.fxml)
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

    public void goToAjouterAteliert(ActionEvent actionEvent) {
        try {
            // Charger le fichier FXML de la liste des pannes (PanneIndex.fxml)
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ajouterAtelier.fxml"));
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

    public void calculateMean(ActionEvent actionEvent) {
        try {
            // Charger le fichier FXML de la liste des pannes (PanneIndex.fxml)
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/StatsView.fxml"));
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

    public void handleRequest(ActionEvent actionEvent) {
        try {
            // Charger le fichier FXML de la liste des pannes (PanneIndex.fxml)
            //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ChatBot.fxml"));
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/chatbotPage.fxml"));
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

    public void showStatistics(ActionEvent event) {
        try {
            // Charger la vue FXML du tableau de bord de statistiques
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/WorkshopStatisticsDashboard.fxml"));
            Parent root = loader.load();

            // Créer une nouvelle scène
            Scene scene = new Scene(root);

            // Créer une nouvelle fenêtre
            Stage stage = new Stage();
            stage.setTitle("Workshop Statistics");
            stage.setScene(scene);

            // Afficher la fenêtre
            stage.show();
        } catch (IOException e) {
            // Gérer les erreurs liées au chargement du fichier FXML
            e.printStackTrace();
        }
    }
}
