package tn.esprit.navigation;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Navigation {

    public void goToAjouterVoiture(ActionEvent event) {
        chargerEtChangerScene(event, "/AjouterVoiture.fxml", "Ajouter une Voiture");
    }

    public void goToListePannes(ActionEvent event) {
        chargerEtChangerScene(event, "/ListePannes.fxml", "Liste des Pannes");
    }

    public void goToListeAteliers(ActionEvent event) {
        chargerEtChangerScene(event, "/ListeAteliers.fxml", "Liste des Ateliers");
    }

    public void goToAddVoiture(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/tn/esprit/views/VoitureAdd.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
        ((Node) event.getSource()).getScene().getWindow().hide();
    }

    private void chargerEtChangerScene(ActionEvent event, String cheminFxml, String titre) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(cheminFxml));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle(titre);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException("Erreur lors du chargement du fichier FXML", e);
        }
    }



    public void gotoshow(ActionEvent event , int id ,String cliente , String couverture, int engagement , String debut , String fin, int prix) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ContratShow.fxml"));
        Parent root = null;
        try {
            Node source = (Node) event.getSource();
            root = loader.load();
            System.out.println("FXML file loaded successfully.");
            ContratShow controller = loader.getController();
            controller.setId(id , cliente, couverture , engagement , debut,fin ,prix);
            Stage stage = (Stage) source.getScene().getWindow();
            stage.setTitle("Contrat");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void xxx(ActionEvent event , int id ,String cliente , String couverture, int engagement , String debut , String fin, int prix) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ContratShowAdmin.fxml"));
        Parent root = null;
        try {
            Node source = (Node) event.getSource();
            root = loader.load();
            System.out.println("FXML file loaded successfully.");
            ContratShowAdmin controller = loader.getController();
            controller.setId(id , cliente, couverture , engagement , debut,fin ,prix);
            Stage stage = (Stage) source.getScene().getWindow();
            stage.setTitle("Contrat");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
   public void goToFactureIndex(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FactureIndex.fxml"));
        Parent root = null;
        try {
            Node source = (Node) event.getSource();
            root = loader.load();
            System.out.println("FXML file loaded successfully.");
            FactureIndex controller = loader.getController();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.setTitle(" Facture Index");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
  public   void changeroute(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AjouterContrat.fxml"));
        Parent root = null;
        try {
            Node source = (Node) event.getSource();
            root = loader.load();
            System.out.println("FXML file loaded successfully.");
            AjouterContrat controller = loader.getController();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.setTitle("Ajouter Contrat");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void goToFactureAdd(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FactureAdd.fxml"));
        Parent root = null;
        try {
            Node source = (Node) event.getSource();
            root = loader.load();
            System.out.println("FXML file loaded successfully.");
            FactureAdd controller = loader.getController();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.setTitle("Ajouter Contrat");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

 
}

