package tn.esprit.controllers.User;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import tn.esprit.models.User;
import tn.esprit.services.ServiceUser;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class Index implements Initializable {


    @FXML
    private ListView<User> listView;

     private ServiceUser sr = new ServiceUser();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("eee");
        try {
            List<User> userList = sr.getAll();
            ObservableList<User> observableList = FXCollections.observableArrayList(userList);
            listView.setItems(observableList);
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception
        }
    }
}
