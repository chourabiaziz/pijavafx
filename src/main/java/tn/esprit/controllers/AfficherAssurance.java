
package tn.esprit.controllers;

        import javafx.fxml.FXML;
        import javafx.fxml.Initializable;
        import javafx.scene.control.Alert;
        import javafx.scene.control.Button;
        import javafx.scene.control.Label;
        import javafx.scene.layout.GridPane;
        import javafx.scene.layout.VBox;
        import javafx.scene.text.Text;
        import tn.esprit.models.Assurance;
        import tn.esprit.services.ServiceAssurance;

        import java.net.URL;
        import java.util.ArrayList;
        import java.util.List;
        import java.util.ResourceBundle;


public class AfficherAssurance implements Initializable {

    private ServiceAssurance serviceAssurance = new ServiceAssurance();

    @FXML
    private GridPane gridPane;

    private List<Assurance> assurances; // Supposons que vous avez une liste d'assurances

    @FXML
    private Label nomLabel;

    @FXML
    private Label adresseLabel;

    @FXML
    private Label codePostalLabel;

    @FXML
    private Label telephoneLabel;

    @FXML
    private Label emailLabel;
    private String nom;
    private String adresse;
    private String codePostal;
    private String telephone;
    private String email;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Initialiser la liste d'assurances
        assurances = new ArrayList<>();

        // Afficher les données récupérées dans les labels correspondants
        nomLabel.setText(nom);
        adresseLabel.setText(adresse);
        codePostalLabel.setText(codePostal);
        telephoneLabel.setText(telephone);
        emailLabel.setText(email);
    }

    // Méthode pour récupérer les données de AjouterAssurance.fxml et les affecter aux variables correspondantes
    public void setDonnees(String nom, String adresse, String codePostal, String telephone, String email) {
        this.nom = nom;
        this.adresse = adresse;
        this.codePostal = codePostal;
        this.telephone = telephone;
        this.email = email;
    }

    void afficherAssurances(String string) {
        int row = 0;
        int col = 0;

        if (assurances != null) {
            for (Assurance assurance : assurances) {
                VBox carreau = createCarreau(assurance);
                gridPane.add(carreau, col, row);
                col++;
                if (col == 3) {
                    col = 0;
                    row++;
                }
            }
        } else {
            System.err.println("La liste d'assurances est vide ou non initialisée.");
        }
    }

    private VBox createCarreau(Assurance assurance) {
        VBox vbox = new VBox();
        vbox.getStyleClass().add("carreau"); // Appliquer des styles CSS si nécessaire

        Text nomAssurance = new Text("Nom: " + assurance.getNom_assurance());
        Text adresseAssurance = new Text("Adresse: " + assurance.getAdresse_assurance());
        Text codePostalAssurance = new Text("Code postal: " + assurance.getCode_postal_assurance());
        Text telephoneAssurance = new Text("Téléphone: " + assurance.getTel_assurance());
        Text emailAssurance = new Text("Email: " + assurance.getEmail_assurance());

        Button btnSupprimer = new Button("Supprimer");
        btnSupprimer.setOnAction(event -> supprimerAssurance(assurance));


        vbox.getChildren().addAll(nomAssurance, adresseAssurance, codePostalAssurance, telephoneAssurance, emailAssurance);
        return vbox;
    }

    private void supprimerAssurance(Assurance assurance) {
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
        }
    }



}


