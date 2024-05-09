package tn.esprit.controllers.User;

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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import tn.esprit.controllers.ContratIndexAdmin;
import tn.esprit.controllers.FactureIndexAdmin;
import tn.esprit.models.Contrat;
import tn.esprit.models.User;
import tn.esprit.services.ContratService;
import tn.esprit.services.ServiceUser;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class DashbordAdmin implements Initializable
{
    @FXML
    private Button contrat;

    @FXML
    private Button facture;
    @FXML
    private ListView<User> listView;

    private ServiceUser contratService;
    private List<User> listContrat;





    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        facture.setOnAction(this::facture);
        contrat.setOnAction(this::Contrat);
        contratService = new ServiceUser();

        listContrat = new ArrayList<>();


        ;


        listView.setCellFactory(new Callback<ListView<User>, ListCell<User>>() {
            @Override
            public ListCell<User> call(ListView<User> contratListView) {
                return new DashbordAdmin.ContratListCell();
            }
        });
        afficherContrats();
    }



    @FXML
    private Pagination pagination;
    private static final int ITEMS_PER_PAGE = 5;
    private static final int TOTAL_ITEMS = 100;



    private class ContratListCell extends ListCell<User> {

        @Override
        protected void updateItem(User contrat, boolean empty) {
            super.updateItem(contrat, empty);
            if (empty || contrat == null) {
                setText(null);
                setGraphic(null);
            } else {
                // Create an HBox to hold the contract details
                HBox card = new HBox();

                Label idLabel = new Label("Ref N°" + contrat.getId());
                Label client = new Label("Email " + contrat.getMail());




                String finText = contrat.getNom() + " - " + contrat.getPrenom();
                Label finLabel = new Label( finText);
                Label prixLabel = new Label(contrat.getNumber() + " ");
                prixLabel.setStyle("-fx-font-weight: bold;");
                Button button1 = new Button("Modifier");
                Button button0 = new Button("Consulter");
                Button button2 = new Button("Supprimer");
                button0.setStyle("-fx-background-color: #cacaca; -fx-padding: 10px; -fx-border-radius: 50px;");
                button1.setStyle("-fx-background-color: #6e6e6e; -fx-padding: 10px; -fx-border-radius: 50px;");
                button2.setStyle("-fx-background-color: #ef1a1a; -fx-padding: 10px; -fx-border-radius: 50px;");
                idLabel.setMinWidth(70);
                idLabel.setMaxWidth(70);
                client.setMinWidth(70);
                client.setMaxWidth(70);
                finLabel.setMinWidth(70);
                finLabel.setMaxWidth(70);
                HBox buttonBox = new HBox(button0,button1, button2);
                buttonBox.setStyle("-fx-spacing: 10px;");
                card.getChildren().addAll(idLabel, client, prixLabel,  finLabel, buttonBox);
//buttons actions
                button1.setOnAction(event -> {
//                    int id = contrat.getId() ;int engagement = contrat.getEngagement() ;String couverture = contrat.getCouverture() ;
//                    int prix = contrat.getPrix() ; String cliente = contrat.getClient();
//                    String debut= contrat.getDebut().toString();
//
//                    n.gotoedit(event, id , cliente, couverture,engagement, debut,prix);

                });
                button0.setOnAction(event -> {


//                    int id = contrat.getId() ;int engagement = contrat.getEngagement() ;String couverture = contrat.getCouverture() ;
//                    int prix = contrat.getPrix() ; String cliente = contrat.getClient();
//                    String debut= contrat.getDebut().toString();String fin= contrat.getFin().toString();
//
//                    n.gotoshow(event, id , cliente, couverture,engagement, debut,fin,prix);

                });
                button2.setOnAction(event -> {
                    try {
                        contratService.supprimer(contrat.getId());
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }

                });


                card.setStyle("-fx-alignment: center ;-fx-background-color: #6d89ef; -fx-padding: 10px; -fx-spacing: 10px;");
                setGraphic(card);
            }
        }}





    private void afficherContrats() {
        // Fetch all contracts
        try {
            listContrat = contratService.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Calculate total pages for pagination
        int totalPageCount = (int) Math.ceil((double) listContrat.size() / ITEMS_PER_PAGE);
        pagination.setPageCount(totalPageCount);

        // Set up pagination to update ListView
        pagination.setPageFactory(pageIndex -> {
            int fromIndex = pageIndex * ITEMS_PER_PAGE;
            int toIndex = Math.min(fromIndex + ITEMS_PER_PAGE, listContrat.size());
            ObservableList<User> pageContrats = FXCollections.observableArrayList(listContrat.subList(fromIndex, toIndex));
            listView.setItems(pageContrats);
            return new VBox(); // Placeholder, the actual content is set dynamically
        });
    }








































    public void facture(ActionEvent event ) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FactureIndexAdmin.fxml"));
        Parent root = null;
        try {
            Node source = (Node) event.getSource();
            root = loader.load();
            System.out.println("FXML file loaded successfully.");
            FactureIndexAdmin controller = loader.getController();

            Stage stage = (Stage) source.getScene().getWindow();
            stage.setTitle("Facture");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void Contrat(ActionEvent event ) {
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
}
