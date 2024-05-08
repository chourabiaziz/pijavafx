package com.example.ramzi.controllers.admin;

import com.example.ramzi.HelloApplication;
import com.example.ramzi.models.Dvis;
import com.example.ramzi.services.DvisServices;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class ListeDivis {
    @FXML
    private ListView<Dvis> listeview;
    private  final DvisServices dvisServices=new DvisServices();

    @FXML
    public void initialize() {
        afficher();
    }

    private void afficher() {
        listeview.setCellFactory(param -> new ListCell<>() {
            private final HBox h1 = new HBox();
            private final Label idLabel = new Label();
            private final Label nomLabel = new Label();
            private final Label prenomLabel = new Label();
            private final Label civiliteLabel = new Label();
            private final Label telephoneLabel = new Label();
            private final Label emailLabel = new Label();
            private final Label voitureLabel = new Label();
            private final Label matriculeLabel = new Label();
            private final Label puissanceLabel = new Label();
            private final Label cylindreLabel = new Label();
            private final Label descriptionLabel = new Label();
            private final Label statutLabel = new Label();
            private final Label couvertureLabel = new Label();
            private final Label offreIdLabel = new Label();

            @Override
            protected void updateItem(Dvis item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    idLabel.setText(String.valueOf(item.getId()));
                    idLabel.setPrefWidth(60.0);
                    nomLabel.setText(item.getNom());
                    nomLabel.setPrefWidth(60.0);
                    prenomLabel.setText(item.getPrenom());
                    prenomLabel.setPrefWidth(60.0);
                    civiliteLabel.setText(item.getCivilite());
                    civiliteLabel.setPrefWidth(60.0);
                    telephoneLabel.setText(String.valueOf(item.getTelephone()));
                    telephoneLabel.setPrefWidth(60.0);
                    emailLabel.setText(item.getEmail());
                    emailLabel.setPrefWidth(60.0);
                    voitureLabel.setText(item.getVoiture());
                    voitureLabel.setPrefWidth(60.0);
                    matriculeLabel.setText(item.getMatricule());
                    matriculeLabel.setPrefWidth(60.0);
                    puissanceLabel.setText(String.valueOf(item.getPuissance()));
                    puissanceLabel.setPrefWidth(60.0);
                    cylindreLabel.setText(String.valueOf(item.getCylindre()));
                    cylindreLabel.setPrefWidth(60.0);
                    descriptionLabel.setText(item.getDescription());
                    descriptionLabel.setPrefWidth(60.0);
                    statutLabel.setText(item.getStatut());
                    statutLabel.setPrefWidth(60.0);
                    couvertureLabel.setText(item.getCouverture());
                    couvertureLabel.setPrefWidth(60.0);
                    offreIdLabel.setText(String.valueOf(item.getOffre_id()));
                    offreIdLabel.setPrefWidth(60.0);

                    h1.setSpacing(5);
                    h1.getChildren().setAll(idLabel, nomLabel, prenomLabel, civiliteLabel, telephoneLabel, emailLabel, voitureLabel, matriculeLabel, puissanceLabel, cylindreLabel, descriptionLabel, statutLabel, couvertureLabel, offreIdLabel);
                    setGraphic(h1);
                }
            }
        });
        try {
            listeview.getItems().setAll(dvisServices.getAll());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void golisteoffre() throws IOException {
        Scene scene1 = listeview.getScene();
        Stage stage1 = (Stage) scene1.getWindow();
        stage1.close();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/admin/ListeOffre.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage=new Stage();
        stage.setTitle("");
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void golistDevi() throws IOException {
        Scene scene1 = listeview.getScene();
        Stage stage1 = (Stage) scene1.getWindow();
        stage1.close();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/admin/ListDvis.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage=new Stage();
        stage.setTitle("");
        stage.setScene(scene);
        stage.show();
    }
}
