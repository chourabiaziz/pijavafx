package tn.esprit.navigation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tn.esprit.controllers.AjouterContrat;
import tn.esprit.controllers.ContratEdit;
import tn.esprit.controllers.FactureAdd;
import tn.esprit.controllers.FactureIndex;

import java.io.IOException;

public class Navigation {

    public void gotoedit(ActionEvent event , int id , String couverture, int engagement , String debut , int prix) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ContratEdit.fxml"));
        Parent root = null;
        try {
            Node source = (Node) event.getSource();
            root = loader.load();
            System.out.println("FXML file loaded successfully.");
            ContratEdit controller = loader.getController();
            controller.setId(id ,couverture , engagement , debut,prix);
            Stage stage = (Stage) source.getScene().getWindow();
            stage.setTitle("Modifier Contrat");
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
            stage.setTitle("Ajouter Contrat");
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
