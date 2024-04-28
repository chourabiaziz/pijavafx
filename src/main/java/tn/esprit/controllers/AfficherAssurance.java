
package tn.esprit.controllers;

        import javafx.collections.FXCollections;
        import javafx.collections.ObservableList;
        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;;
        import javafx.fxml.FXMLLoader;
        import javafx.fxml.Initializable;
        import javafx.scene.Node;
        import javafx.scene.Parent;
        import javafx.scene.Scene;
        import javafx.scene.control.*;
        import javafx.scene.control.Button;
        import javafx.scene.control.Label;
        import javafx.scene.control.TextField;
        import javafx.scene.control.cell.PropertyValueFactory;

        import javafx.scene.layout.*;
        import javafx.stage.Stage;
        import tn.esprit.models.Assurance;
        import tn.esprit.services.ServiceAssurance;
        import javafx.scene.image.Image; // Correct import for JavaFX Image


        import java.awt.*;
        import java.io.IOException;
        import java.io.InputStream;
        import java.net.URL;
        import java.util.List;
        import java.util.ResourceBundle;
        import javafx.geometry.Insets;

        import javax.swing.text.html.ImageView;


public class AfficherAssurance implements Initializable {


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

    @FXML
    private AnchorPane mainAnchorPane;

    private ObservableList<Assurance> data = FXCollections.observableArrayList();
    private ObservableList<Assurance> filteredData = FXCollections.observableArrayList();

    public void setAssurances(List<Assurance> assurances) {
        ObservableList<Node> children = tilePane.getChildren();
        children.clear();
        for (Assurance assurance : assurances) {
            VBox card = new VBox();
            card.setPrefWidth(200);
            card.setPrefHeight(180);
            card.setSpacing(5);


            Label nomLabel = new Label("Nom: " + assurance.getNom_assurance());
            Label adresseLabel = new Label("Adresse: " + assurance.getAdresse_assurance());
            Label codePostalLabel = new Label("Code Postal: " + assurance.getCode_postal_assurance());
            Label telephoneLabel = new Label("Téléphone: " + assurance.getTel_assurance());
            Label emailLabel = new Label("Email: " + assurance.getEmail_assurance());

            Button modifierButton = new Button("Modifier");
            modifierButton.setOnAction(event -> {
                try {
                    Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    currentStage.close();

                    // Charger le fichier FXML de la page ModifierAssurance.fxml
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/ModifierAssurance.fxml"));
                    Parent root = loader.load();

                    // Afficher la nouvelle scène
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            Button supprimerButton = new Button("Supprimer");
            supprimerButton.setOnAction(event -> {
                // Retrieve the assurance associated with this button
                Assurance assuranceToDelete = assurance;
                // Create an instance of the ServiceAssurance class
                ServiceAssurance serviceAssurance = new ServiceAssurance();

                // Perform the deletion operation
                boolean deleteSuccess = serviceAssurance.delete(assuranceToDelete);
                if (deleteSuccess) {
                    // If deletion is successful, remove the card from the UI
                    children.remove(card);
                    System.out.println("Assurance supprimée avec succès.");
                } else {
                    System.out.println("Échec de la suppression de l'assurance.");
                }
            });

            card.getChildren().addAll(nomLabel, adresseLabel, codePostalLabel, telephoneLabel, emailLabel, modifierButton, supprimerButton);
            children.add(card);


        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        InputStream imageStream = getClass().getResourceAsStream("/background.png");
        if (imageStream == null) {
            System.err.println("Resource not found: /resources/background.png");
        } else {
            Image imageBack = new Image(imageStream);
            BackgroundImage backgroundImage = new BackgroundImage(
                    imageBack,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.CENTER,
                    new BackgroundSize(100, 100, true, true, false, true)
            );
            mainAnchorPane.setBackground(new Background(backgroundImage));
        }


        // Ajouter le gestionnaire d'événements pour le bouton "Ajouter"
        ajouterButton.setOnAction(event -> {
            try {
                // Charger le fichier FXML de la page AjouterAssurance.fxml
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/AjouterAssurance.fxml"));
                Parent root = loader.load();

                // Afficher la nouvelle scène
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        loadData();


    }


    private void loadData() {
        // Retrieve data from your service or database
        ServiceAssurance service = new ServiceAssurance();
        List<Assurance> allAssurances = service.getAll();
        data.addAll(allAssurances);
        filteredData.addAll(allAssurances);

        // Display all assurances initially
        displayAssurances(filteredData);
    }
    private void displayAssurances(ObservableList<Assurance> assurances) {
        // Implement the logic to display assurances in your TilePane
        tilePane.getChildren().clear(); // Clear existing children

        for (Assurance assurance : assurances) {
            VBox card = new VBox();
            card.setPrefWidth(200);
            card.setPrefHeight(180);
            card.setSpacing(5);
            card.setPadding(new Insets(5));

            Label nomLabel = new Label("Nom: " + assurance.getNom_assurance());
            Label adresseLabel = new Label("Adresse: " + assurance.getAdresse_assurance());
            Label codePostalLabel = new Label("Code Postal: " + assurance.getCode_postal_assurance());
            Label telephoneLabel = new Label("Téléphone: " + assurance.getTel_assurance());
            Label emailLabel = new Label("Email: " + assurance.getEmail_assurance());

            card.getChildren().addAll(nomLabel, adresseLabel, codePostalLabel, telephoneLabel, emailLabel);
            tilePane.getChildren().add(card);
        }

    }

    @FXML
    private void searchFieldChanged() {
        String query = searchField.getText().trim().toLowerCase();

        filteredData.clear(); // Clear existing filtered data
        if (query.isEmpty()) {
            displayAssurances(data);
        } else {
            for (Assurance assurance : data) {

                if (assurance.getNom_assurance().toLowerCase().contains(query)) {
                    filteredData.add(assurance); // Add matching assurances to filtered data
                }
            }
            System.out.println("Filtered data size: " + filteredData.size());

            displayAssurances(filteredData); // Display filtered data
        }
    }



    }


