package tn.esprit.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import tn.esprit.models.Voiture;
import tn.esprit.services.ServiceVoiture;

public class VoitureIndex implements Initializable {

    @FXML
    private TableView<Voiture> voitureTable;
    @FXML
    private TableColumn<Voiture, Integer> idColumn;
    @FXML
    private TableColumn<Voiture, String> marqueColumn;
    @FXML
    private TableColumn<Voiture, String> modeleColumn;
    @FXML
    private TableColumn<Voiture, String> numeroSerieColumn;
    @FXML
    private TableColumn<Voiture, String> typeCarburantColumn;
    @FXML
    private TableColumn<Voiture, String> numeroImmatriculationColumn;
    @FXML
    private TableColumn<Voiture, Integer> kilometrageColumn;
    @FXML
    private TableColumn<Voiture, String> couleurColumn;
    @FXML
    private TableColumn<Voiture, Double> prixAchatColumn;
    @FXML
    private TableColumn<Voiture, Double> prixActuelColumn;
    @FXML
    private TableColumn<Voiture, String> carteGriseColumn;
    @FXML
    private TableColumn<Voiture, String> nomImageColumn;
    @FXML
    private Button goToAjouterVoiture;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        marqueColumn.setCellValueFactory(new PropertyValueFactory<>("marque"));
        modeleColumn.setCellValueFactory(new PropertyValueFactory<>("modele"));
        numeroSerieColumn.setCellValueFactory(new PropertyValueFactory<>("numeroSerie"));
        typeCarburantColumn.setCellValueFactory(new PropertyValueFactory<>("typeCarburant"));
        numeroImmatriculationColumn.setCellValueFactory(new PropertyValueFactory<>("numeroImmatriculation"));
        kilometrageColumn.setCellValueFactory(new PropertyValueFactory<>("kilometrage"));
        couleurColumn.setCellValueFactory(new PropertyValueFactory<>("couleur"));
        prixAchatColumn.setCellValueFactory(new PropertyValueFactory<>("prixAchat"));
        prixActuelColumn.setCellValueFactory(new PropertyValueFactory<>("prixActuel"));
        carteGriseColumn.setCellValueFactory(new PropertyValueFactory<>("carteGrise"));
        nomImageColumn.setCellValueFactory(new PropertyValueFactory<>("nomImage"));

        List<Voiture> voitures = new ServiceVoiture().getAll();
        ObservableList<Voiture> observableList = FXCollections.observableArrayList(voitures);
        voitureTable.setItems(observableList);
    }

    @FXML
    public void goToAjouterVoiture(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/tn/esprit/views/VoitureAjouter.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}