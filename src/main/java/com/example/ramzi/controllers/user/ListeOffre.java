package com.example.ramzi.controllers.user;

import com.example.ramzi.HelloApplication;
import com.example.ramzi.models.Offre;
import com.example.ramzi.services.OffreServices;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class ListeOffre {
    @FXML
    private ScrollPane ScrollePan;
    private  final OffreServices offreServices=new OffreServices();
    @FXML
    public void initialize() {
        try {
            loadOffre(offreServices.getAll());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void loadOffre(List<Offre> offres) {
        FlowPane donationFlowPane = new FlowPane();
        donationFlowPane.setStyle("-fx-pref-width: 450px; " +
                "-fx-pref-height: 547px;"+
                "-fx-background-color: rgba(0, 0, 8, 0); ");


        for (Offre offre : offres) {
            VBox cardContainer = createDonationVBox(offre);
            donationFlowPane.getChildren().add(cardContainer);
            FlowPane.setMargin(cardContainer, new Insets(10));
        }
        ScrollePan.setContent(donationFlowPane);

    }


    private VBox createDonationVBox(Offre offre) {
        VBox cardContainer = new VBox();
        cardContainer.setStyle("-fx-padding: 10px 10px 10px 10px;");
        cardContainer.setStyle(
                "-fx-background-color: #EFFCFF; " +
                        "-fx-border-radius: 5px; " +
                        "-fx-border-color: #9CCBD6; " +
                        "-fx-background-radius: 5px; " +
                        "-fx-border-width: 1px; ");


        Pane pane = new Pane();
        pane.setLayoutX(403.0);
        pane.setLayoutY(130.0);
        pane.setPrefHeight(300.0);
        pane.setPrefWidth(440.0);



        Label TitreLabel = new Label();
        TitreLabel.setLayoutX(43.0);
        TitreLabel.setLayoutY(15.0);
        TitreLabel.setPrefHeight(17.0);
        TitreLabel.setPrefWidth(200.0);
        TitreLabel.setText("Titre :"+offre.getTitre());

        Label AssuranceLabel = new Label();
        AssuranceLabel.setLayoutX(43.0);
        AssuranceLabel.setLayoutY(35.0);
        AssuranceLabel.setPrefHeight(17.0);
        AssuranceLabel.setPrefWidth(200.0);
        AssuranceLabel.setText("Assurance :"+offre.getAssurance());

        Label EmailLabel = new Label();
        EmailLabel.setLayoutX(43.0);
        EmailLabel.setLayoutY(55.0);
        EmailLabel.setPrefHeight(17.0);
        EmailLabel.setPrefWidth(99.0);
        EmailLabel.setText(String.valueOf("Email :"+offre.getEmail_assurance()));

        Label NumLabel = new Label();
        NumLabel.setLayoutX(43.0);
        NumLabel.setLayoutY(75.0);
        NumLabel.setPrefHeight(17.0);
        NumLabel.setPrefWidth(99.0);
        NumLabel.setText(String.valueOf("Numero :"+offre.getNum_assurance()));

        Label contentLabel = new Label();
        contentLabel.setLayoutX(43.0);
        contentLabel.setLayoutY(95.0);
        contentLabel.setPrefHeight(75.0);
        contentLabel.setPrefWidth(538.0);
        contentLabel.setText("Descreption : "+offre.getDescription());






        ImageView daitailleImageView = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/image/projects.png"))));
        daitailleImageView.setFitHeight(42.0);
        daitailleImageView.setFitWidth(35.0);
        daitailleImageView.setLayoutX(401.0);
        daitailleImageView.setLayoutY(10.0);
        daitailleImageView.setPickOnBounds(true);
        daitailleImageView.setPreserveRatio(true);

        daitailleImageView.setOnMouseClicked(event -> {
            Scene scene1 = ScrollePan.getScene();
            Stage stage1 = (Stage) scene1.getWindow();
            stage1.close();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/user/AddDevis.fxml"));
            Parent root = null;
            try {
                root = loader.load();
                AddDvis controller = loader.getController();
                controller.initialize(offre.getId());
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("Ajouter");
                stage.show();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        pane.getChildren().addAll(TitreLabel,AssuranceLabel,EmailLabel, contentLabel, NumLabel, daitailleImageView);
        cardContainer.getChildren().add(pane);
        return cardContainer;
    }
    @FXML
    void golistedevis() throws IOException {
        Scene scene1 = ScrollePan.getScene();
        Stage stage1 = (Stage) scene1.getWindow();
        stage1.close();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/user/ListDevis.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage=new Stage();
        stage.setTitle("");
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void golisteOffres() throws IOException {
        Scene scene1 = ScrollePan.getScene();
        Stage stage1 = (Stage) scene1.getWindow();
        stage1.close();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/user/ListeOffre.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage=new Stage();
        stage.setTitle("");
        stage.setScene(scene);
        stage.show();
    }

}
