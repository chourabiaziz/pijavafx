
package tn.esprit.controllers;

        import javafx.fxml.FXML;
        import javafx.fxml.Initializable;
        import javafx.scene.control.Alert;
        import javafx.scene.control.Button;
        import javafx.scene.layout.GridPane;
        import javafx.scene.layout.VBox;
        import javafx.scene.text.Text;
        import tn.esprit.models.Assurance;

        import java.net.URL;
        import java.util.List;
        import java.util.ResourceBundle;


public class AfficherAssurance implements Initializable {

    @FXML
    private GridPane gridPane;

    private List<Assurance> assurances; // Supposons que vous avez une liste d'assurances

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    void afficherAssurances(String string) {
        int row = 0;
        int col = 0;

        for (Assurance assurance : assurances) {
            VBox carreau = createCarreau(assurance);
            gridPane.add(carreau, col, row);
            col++;
            if (col == 3) { // Vous pouvez ajuster ce nombre pour changer le nombre de carreaux par ligne
                col = 0;
                row++;
            }
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
       // btnSupprimer.setOnAction(event -> supprimerAssurance(assurance));


        vbox.getChildren().addAll(nomAssurance, adresseAssurance, codePostalAssurance, telephoneAssurance, emailAssurance);
        return vbox;
    }





}


