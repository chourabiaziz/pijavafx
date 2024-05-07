package tn.esprit.controllers.User;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import tn.esprit.controllers.ContratIndexAdmin;
import tn.esprit.models.Role;
import tn.esprit.models.User;
import tn.esprit.services.ServiceUser;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

public class AddUser {


    @FXML
    private TextField nomField;

    @FXML
    private TextField prenomField;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField phoneField;



    @FXML
    private void handleAddUserButtonClick() {
        try {
            String nom = nomField.getText();
            String prenom = prenomField.getText();
            String email = emailField.getText();
            String password = passwordField.getText();
            String phone = phoneField.getText();
            Role role = Role.ROLE_USER;

            if (nom.isEmpty() || prenom.isEmpty() || email.isEmpty() || password.isEmpty() || phone.isEmpty() ) {
                throw new Exception("All fields are required.");
            }

            if (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
                throw new Exception("Invalid email address.");
            }

            if (!phone.matches("^[2|5|9][0-9]{7}$")) {
                throw new Exception("Invalid phone number. Must start with 2, 5, or 9 and have 8 digits.");
            }

            // create a new User object based on the input fields
            User user = new User(
                    nom,
                    prenom,
                    Integer.parseInt(phone),
                    email,
                    password,
                    role
            );


            ServiceUser serviceUser = new ServiceUser();

            serviceUser.ajouter(user);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("User added successfully");
            alert.showAndWait();
           // this.goToContratList( );

            // clear input fields
            nomField.clear();
            prenomField.clear();
            emailField.clear();
            passwordField.clear();
            phoneField.clear();
             //conciergeAttributes.setVisible(false);
           // residentAttributes.setVisible(false);

        } catch (Exception e) {
            // show error message
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error adding user");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }



    @FXML
    private Button login ;
    @FXML
    private void initialize() throws SQLException {

         login.setOnAction(this::goToContratList);

}

    void goToContratList(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Login.fxml"));
        Parent root ;
        try {
            Node source = (Node) event.getSource();
            root = loader.load();
            System.out.println("FXML file loaded successfully.");
            Login controller = loader.getController();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.setTitle("contrat  ");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}