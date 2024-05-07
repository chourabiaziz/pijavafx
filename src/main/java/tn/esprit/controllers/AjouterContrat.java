package tn.esprit.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import tn.esprit.models.Contrat;
import tn.esprit.models.Personne;
import tn.esprit.models.Role;
import tn.esprit.models.User;
import tn.esprit.services.ContratService;
import tn.esprit.services.ServiceUser;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class AjouterContrat implements Initializable {

    @FXML
    private TextField couverture;

    @FXML
    private DatePicker datedebut;

    @FXML
    private TextField engagement;

    @FXML
    private TextField prix;
    @FXML
    private TextField client;

    @FXML
    private Button retour;
    @FXML
    private Button annuler;

    @FXML
    private Button submit;
    @FXML
    private Text errdate;

    LocalDate currentDate = LocalDate.now();

    @FXML
    private ChoiceBox<User> chbox;

    @FXML
    void errdate(ActionEvent event) {

        if (datedebut.getValue().isBefore(currentDate)) {
            errdate.setText("date doit etre supérieur a current date");
            errdate.setFill(Color.RED);
                submit.setDisable(true);
        }else {
                submit.setDisable(false);
            errdate.setText("");
        }


    }

    @FXML
    private Text errprix;
    @FXML
    void errprix(KeyEvent event) {
        if (prix.getText().matches("[a-zA-Z]+") || prix.getText().isEmpty() ) {
            errprix.setText("champ ne peut pas etre vide ou contient des lettres inclus les espaces");
            errprix.setFill(Color.RED);
            submit.setDisable(true);

        } else {
            errprix.setText("");
            submit.setDisable(false);
        }
    }


    @FXML
    void submit(ActionEvent event) {
        Contrat c = new Contrat();
        ContratService cs = new ContratService();
        c.setCouverture(couverture.getText());
        c.setClient(chbox.getValue().getNom()+' '+chbox.getValue().getPrenom());
        c.setEngagement(Integer.parseInt(engagement.getText()));
        c.setPrix(Integer.parseInt(prix.getText()));
         c.setDebut(Date.valueOf(datedebut.getValue()));
        LocalDate finDate = c.getDebut().toLocalDate().plusMonths(c.getEngagement());
        c.setFin(Date.valueOf(finDate));

        try {
            cs.add(c);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succès");
            alert.setHeaderText("Contrat ajouté avec succès");

            Optional<ButtonType> result = alert.showAndWait();

// Check if the user clicked OK
            if (result.isPresent() && result.get() == ButtonType.OK) {
                goToContratList(event);
            } else {
                goToContratList(event);
            }
        }
        catch(Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Échec de l'ajout du contrat");
            alert.setContentText("Une erreur est survenue lors de l'ajout du contrat. Veuillez réessayer.");
            alert.showAndWait();
        }


    }



    private void addItem(User item) {
        // Add item to the existing list of items
        chbox.getItems().add(item);
    }
    @FXML
    void goToContratList(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ContratIndexAdmin.fxml"));
        Parent root ;
        try {
            Node source = (Node) event.getSource();
            root = loader.load();
            System.out.println("FXML file loaded successfully.");
            ContratIndexAdmin controller = loader.getController();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.setTitle("Modifier Contrat");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ServiceUser su = new ServiceUser();

        List<User> userList = null;
        try {
            userList = su.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        ObservableList<User> items = FXCollections.observableArrayList();

        for (User user : userList) {
            if(user.getRole() == Role.ROLE_USER){
                items.add(user);
            }

        }

        chbox.setItems(items);

        retour.setOnAction(this::goToContratList);
        annuler.setOnAction(this::goToContratList);
    }
}
