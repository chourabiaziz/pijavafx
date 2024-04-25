package tn.esprit.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import tn.esprit.models.Atelier;
import tn.esprit.services.ServiceAtelier;

import java.util.List;

public class AtelierIndex {

    @FXML
    private TableView<Atelier> atelierTableView;

    @FXML
    private TableColumn<Atelier, Integer> idColumn;

    @FXML
    private TableColumn<Atelier, String> nomColumn;

    @FXML
    private TableColumn<Atelier, String> adresseColumn;

    @FXML
    private TableColumn<Atelier, String> telephoneColumn;

    @FXML
    private TableColumn<Atelier, String> specialiteColumn;

    @FXML
    private TableColumn<Atelier, String> avisColumn;

    @FXML
    private TableColumn<Atelier, String> heureOuvertureColumn;

    @FXML
    private TableColumn<Atelier, String> heureFermetureColumn;

    private ServiceAtelier serviceAtelier;

    public AtelierIndex() {
        serviceAtelier = new ServiceAtelier();
    }

    @FXML
    public void initialize() {
        // Configurer les colonnes pour afficher les données de l'atelier
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        adresseColumn.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        telephoneColumn.setCellValueFactory(new PropertyValueFactory<>("numero_telephone"));
        specialiteColumn.setCellValueFactory(new PropertyValueFactory<>("specialite"));
        avisColumn.setCellValueFactory(new PropertyValueFactory<>("avis"));
        heureOuvertureColumn.setCellValueFactory(new PropertyValueFactory<>("heureOuverture"));
        heureFermetureColumn.setCellValueFactory(new PropertyValueFactory<>("heureFermeture"));

        // Charger les données des ateliers dans le TableView
        loadAteliers();
    }

    private void loadAteliers() {
        // Récupérer la liste des ateliers depuis la base de données
        List<Atelier> ateliers = serviceAtelier.getAll();

        // Ajouter les ateliers au TableView
        atelierTableView.getItems().addAll(ateliers);
    }
}
