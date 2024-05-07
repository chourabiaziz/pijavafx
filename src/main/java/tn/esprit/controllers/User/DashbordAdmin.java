package tn.esprit.controllers.User;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import tn.esprit.controllers.ContratIndexAdmin;
import tn.esprit.controllers.FactureIndexAdmin;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashbordAdmin implements Initializable
{
    @FXML
    private Button contrat;

    @FXML
    private Button facture;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        facture.setOnAction(this::facture);
        contrat.setOnAction(this::Contrat);
    }







    public void facture(ActionEvent event ) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FactureIndexAdmin.fxml"));
        Parent root = null;
        try {
            Node source = (Node) event.getSource();
            root = loader.load();
            System.out.println("FXML file loaded successfully.");
            FactureIndexAdmin controller = loader.getController();

            Stage stage = (Stage) source.getScene().getWindow();
            stage.setTitle("Facture");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void Contrat(ActionEvent event ) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ContratIndexAdmin.fxml"));
        Parent root = null;
        try {
            Node source = (Node) event.getSource();
            root = loader.load();
            System.out.println("FXML file loaded successfully.");
            ContratIndexAdmin controller = loader.getController();

            Stage stage = (Stage) source.getScene().getWindow();
            stage.setTitle("Facture");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
