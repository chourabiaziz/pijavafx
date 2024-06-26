
package tn.esprit.controllers;

        import javafx.application.Platform;
        import javafx.collections.FXCollections;
        import javafx.collections.ObservableList;
        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.fxml.FXMLLoader;
        import javafx.fxml.Initializable;
        import javafx.geometry.Rectangle2D;
        import javafx.scene.Node;
        import javafx.scene.Parent;
        import javafx.scene.Scene;
        import javafx.scene.control.*;

        import javafx.scene.control.Button;
        import javafx.scene.control.Label;
        import javafx.scene.control.ScrollPane;
        import javafx.scene.control.TextField;
        import javafx.scene.layout.*;
        import javafx.scene.paint.Color;
        import javafx.stage.Screen;
        import javafx.stage.Stage;
        import tn.esprit.models.Assurance;
        import tn.esprit.services.ServiceAssurance;
        import javafx.scene.image.Image; // Correct import for JavaFX Image


        import java.awt.*;
        import java.io.IOException;
        import java.io.InputStream;
        import java.net.URI;
        import java.net.URISyntaxException;
        import java.net.URL;
        import java.util.ArrayList;
        import java.util.List;
        import java.util.ResourceBundle;
        import javafx.geometry.Insets;
        import tn.esprit.utils.SMSManager;


public class AfficherAssuranceAdmin implements Initializable {


    @FXML
    private Button ajouterButton;

    @FXML
    private Button EvoieMail;
    @FXML
    private Button back;

    @FXML
    private TilePane tilePane;
    @FXML
    private TextField searchField;


    @FXML
    private AnchorPane mainAnchorPane;

    private ObservableList<Assurance> data = FXCollections.observableArrayList();
    private ObservableList<Assurance> filteredData = FXCollections.observableArrayList();

    public void setAssurances(List<Assurance> assurances) {
        ObservableList<Node> children = tilePane.getChildren();
        children.clear();
        for (Assurance assurance : assurances) {

            VBox card = new VBox();
            card.setPrefWidth(300);
            card.setPrefHeight(250);
            card.setSpacing(10);
            card.setPadding(new Insets(20));
            card.setStyle(" -fx-border-color: #FFFFFF; -fx-border-width: 2px; -fx-border-radius: 10px;");

            String imagePath = "/Image/backCard.jpg";

            BackgroundImage backgroundImage = new BackgroundImage(
                    new Image(imagePath, 300, 250, false, true),
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.DEFAULT,
                    BackgroundSize.DEFAULT

            );

            // Set the background image to the card
            card.setBackground(new Background(backgroundImage));

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

        InputStream imageStream = getClass().getResourceAsStream("/Image/background.png");
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
            back.setOnAction(this::back);
        }

        // Ajouter le gestionnaire d'événements pour le bouton "Ajouter"
        ajouterButton.setOnAction(event -> {
            try {
                // Charger le fichier FXML de la page AjouterAssurance.fxml
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/AjouterAssuranceAdmin.fxml"));
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

        // Ajouter le gestionnaire d'événements pour le bouton "Ajouter"
     /*   EvoieMail.setOnAction(event -> {
            List<String> emailAddresses = getEmailAddressesFromDatabase();

            Thread emailListThread = new Thread(() -> {
                Platform.runLater(() -> showEmailListWindow(emailAddresses));
            });
            emailListThread.start();
        });*/

        loadData();


    }

    private void back(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ContratIndexAdmin.fxml"));
       // Parent root = null;
        try {

            Node source = (Node) event.getSource();

// ajoutervoiture -> client // admin -> VoitureIndex
            Stage primaryStage = (Stage) source.getScene().getWindow();

            Parent root = loader.load();
            Scene scene = new Scene(root );
            primaryStage.setTitle("AssureEase");
            primaryStage.setScene(scene);
            Rectangle2D screenBounds = Screen.getPrimary().getBounds();
            primaryStage.setWidth(screenBounds.getWidth());
            primaryStage.setHeight(screenBounds.getHeight());



            primaryStage.show();



        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

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
            card.setPrefWidth(300);
            card.setPrefHeight(250);
            card.setSpacing(10);
            card.setPadding(new Insets(20));
            card.setStyle(" -fx-border-color: #FFFFFF; -fx-border-width: 2px; -fx-border-radius: 10px;");

            String imagePath = "/Image/backCard.jpg";

            BackgroundImage backgroundImage = new BackgroundImage(
                    new Image(imagePath, 300, 250, false, true),
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.DEFAULT,
                    BackgroundSize.DEFAULT
            );

            // Set the background image to the card
            card.setBackground(new Background(backgroundImage));



            Label nomLabel = new Label("Nom: " + assurance.getNom_assurance());
            nomLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 16px;");
            nomLabel.setTextFill(Color.GRAY);

            Label adresseLabel = new Label("Adresse: " + assurance.getAdresse_assurance());
            adresseLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 16px;");
            adresseLabel.setTextFill(Color.GRAY);

            Label codePostalLabel = new Label("Code Postal: " + assurance.getCode_postal_assurance());
            codePostalLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 16px;");
            codePostalLabel.setTextFill(Color.GRAY);

            Label telephoneLabel = new Label("Téléphone: " + assurance.getTel_assurance());
            telephoneLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 16px;");
            telephoneLabel.setTextFill(Color.GRAY);

            Label emailLabel = new Label("Email: " + assurance.getEmail_assurance());
            emailLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 16px;");
            emailLabel.setTextFill(Color.GRAY);

            card.getChildren().addAll(nomLabel, adresseLabel, codePostalLabel, telephoneLabel, emailLabel);
            tilePane.getChildren().add(card);
            tilePane.setHgap(10); // Horizontal gap between cards
            tilePane.setVgap(10);
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

/*
    private List<String> getEmailAddressesFromDatabase() {
        // Retrieve email addresses from the database using your service class
        ServiceAssurance service = new ServiceAssurance();
        List<Assurance> assurances = service.getAll();

        // Extract email addresses from the list of assurances
        List<String> emailAddresses = new ArrayList<>();
        for (Assurance assurance : assurances) {
            emailAddresses.add(assurance.getEmail_assurance());
        }
        return emailAddresses;
    }
*/

/*
    private void showEmailListWindow(List<String> emailAddresses) {
        // Create a new stage for the email list window
        Stage emailListStage = new Stage();
        emailListStage.setTitle("Liste des adresses e-mail");
        // Create a VBox to hold the email addresses
        VBox emailListVBox = new VBox();
        emailListVBox.setSpacing(10);

        // Add labels for each email address
        for (String emailAddress : emailAddresses) {
            Hyperlink emailLink = new Hyperlink(emailAddress);

            emailLink.setOnAction(event -> {
                try {
                    Desktop desktop = Desktop.getDesktop();
                    // Create a URI with the mailto: scheme and the clicked email address
                    URI uri = new URI("mailto:" + emailAddress);
                    desktop.mail(uri);

                } catch (IOException | URISyntaxException e){e.printStackTrace();}

                SMSManager smsManager = new SMSManager();
                String recipientPhoneNumber = "+21655331628";
                String message = "vous devez envoyer votre constat à votre assurance.";
                smsManager.sendSMS(recipientPhoneNumber, message);
            });

            emailListVBox.getChildren().add(emailLink);

        }



        // Create a scroll pane to contain the VBox
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(emailListVBox);
        scrollPane.setFitToWidth(true);

        // Create a scene and set it on the stage
        Scene scene = new Scene(scrollPane, 300, 400);
        emailListStage.setScene(scene);
        emailListStage.show();


    }
*/




}


