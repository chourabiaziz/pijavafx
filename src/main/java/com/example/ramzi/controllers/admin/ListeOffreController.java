package com.example.ramzi.controllers.admin;

import com.example.ramzi.HelloApplication;
import com.example.ramzi.models.Offre;
import com.example.ramzi.services.OffreServices;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import tn.esprit.controllers.ContratIndexAdmin;

import java.io.IOException;
import java.sql.SQLException;

public class ListeOffreController {
    @FXML
    private ListView<Offre> listeview;
    @FXML
    private Button dash;
    @FXML
    public void initialize() {
        dash.setOnAction(this::dash);
        afficher();

    }

    public void dash(ActionEvent event ) {
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


    private  final OffreServices offreServices=new OffreServices();
    public void afficher() {
        listeview.setCellFactory(param -> new ListCell<>() {
            private final HBox h1 = new HBox();
            private final Label titreLabel = new Label();
            private final Label assuranceLabel = new Label();
            private final Label emailLabel = new Label();
            private final Label numLabel = new Label();

            @Override
            protected void updateItem(Offre item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    titreLabel.setText(item.getTitre());
                    titreLabel.setPrefWidth(150.0);
                    assuranceLabel.setText(item.getAssurance());
                    assuranceLabel.setPrefWidth(150.0);
                    emailLabel.setText(item.getEmail_assurance());
                    emailLabel.setPrefWidth(150.0);
                    numLabel.setText(item.getNum_assurance());
                    numLabel.setPrefWidth(150.0);

                    Button editButton = new Button("Modifier");
                    editButton.setOnAction(event -> {

                        Scene scene1 = listeview.getScene();
                        Stage stage1 = (Stage) scene1.getWindow();
                        stage1.close();
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/admin/ModifierOffre.fxml"));
                        Parent root = null;
                        try {
                            root = loader.load();
                            ModifierOffre controller = loader.getController();
                            controller.initialize(getItem());
                            Stage stage = new Stage();
                            stage.setScene(new Scene(root));
                            stage.setTitle("Modifier");
                            stage.show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    });

                    Button deleteButton = new Button("Supprimer");
                    deleteButton.setOnAction(event -> {
                        try {
                            offreServices.supprimer(getItem().getId());
                            listeview.getItems().remove(getItem());
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    });

                    HBox buttonsBox = new HBox(editButton, deleteButton);
                    buttonsBox.setSpacing(10);
                    h1.setSpacing(10);
                    h1.getChildren().setAll(titreLabel, assuranceLabel, emailLabel, numLabel, buttonsBox);
                    setGraphic(h1);
                }
            }
        });
        try {
            listeview.getItems().setAll(offreServices.getAll());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void AddOffre() throws IOException {
        Scene scene1 = listeview.getScene();
        Stage stage1 = (Stage) scene1.getWindow();
        stage1.close();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/admin/AddOffre.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage=new Stage();
        stage.setTitle("");
        stage.setScene(scene);
        stage.show();
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


