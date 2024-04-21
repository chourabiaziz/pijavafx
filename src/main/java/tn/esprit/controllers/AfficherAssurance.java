
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
        import javafx.scene.control.cell.PropertyValueFactory;

        import javafx.scene.layout.TilePane;
        import javafx.scene.layout.VBox;
        import javafx.stage.Stage;
        import tn.esprit.models.Assurance;
        import tn.esprit.services.ServiceAssurance;

        import java.awt.*;
        import java.io.IOException;
        import java.net.URL;
        import java.util.List;
        import java.util.ResourceBundle;
        import javafx.geometry.Insets;


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
    public void setAssurances(List<Assurance> assurances) {
        ObservableList<Node> children = tilePane.getChildren();
        children.clear();
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

                        Assurance assuranceToDelete = assurance;

                        // Supprimer l'assurance de la base de données en utilisant le service
                        boolean deleteSuccess = serviceAssurance.delete(assuranceToDelete);
                        if (deleteSuccess) {
                            // Si la suppression réussit, supprimer également la carte de l'interface utilisateur
                            children.remove(card);
                            System.out.println("Assurance supprimée avec succès.");
                        } else {
                            System.out.println("Échec de la suppression de l'assurance.");
                        }

                    }
                    );


            card.getChildren().addAll(nomLabel, adresseLabel, codePostalLabel, telephoneLabel, emailLabel,modifierButton,supprimerButton);
            children.add(card);
        }
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {

  /*
    @FXML
    private void supprimerAssurance(ActionEvent event) {

        Assurance assurance = (Assurance) supprimerButton.getUserData();

        boolean success = serviceAssurance.delete(assurance);
        if (success) {
            // Actualiser l'affichage des assurances après la suppression
        } else {
            // Afficher un message d'erreur en cas d'échec de la suppression
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Erreur lors de la suppression de l'assurance !");
            alert.showAndWait();
        }  */
    }




    }


