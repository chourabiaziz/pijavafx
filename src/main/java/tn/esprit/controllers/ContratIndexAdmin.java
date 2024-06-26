package tn.esprit.controllers;

import com.example.ramzi.controllers.admin.ListeDivis;
import com.example.ramzi.controllers.admin.ListeOffreController;
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
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Callback;
import tn.esprit.controllers.User.DashbordAdmin;
import tn.esprit.controllers.User.ModifyUser;
import tn.esprit.models.Contrat;
import tn.esprit.navigation.Navigation;
import tn.esprit.services.ContratService;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class ContratIndexAdmin implements Initializable {
    Navigation n = new Navigation() ;
    @FXML
    private ListView<Contrat> listView;


    private ContratService contratService;
    private List<Contrat> listContrat;


    @FXML
    private Button offre;
    @FXML
    private Button constat;
    @FXML
    private Button devis;
    @FXML
    private Button dash;
    @FXML
    private Button panne;
    @FXML
    private Button atelier;
    @FXML
    private Button voiture;
    @FXML
    private Button profile;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        contratService = new ContratService();
        listContrat = new ArrayList<>();

        facture.setOnAction(this::factureindex);
        offre.setOnAction(this::offre);
        constat.setOnAction(this::constat);
        dash.setOnAction(this::x);

        devis.setOnAction(this::devis);
        add.setOnAction(this::changeroute);
      //  panne.setOnAction(this::panne);
      atelier.setOnAction(this::assurance);
//
        voiture.setOnAction(this::voiture);
       profile.setOnAction(this::profil);




        listView.setCellFactory(new Callback<ListView<Contrat>, ListCell<Contrat>>() {
            @Override
            public ListCell<Contrat> call(ListView<Contrat> contratListView) {
                return new ContratIndexAdmin.ContratListCell();
            }
        });
        afficherContrats();
    }

    private void voiture(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/VoitureIndexAdmin.fxml"));
        Parent root = null;
        try {
            Node source = (Node) event.getSource();
            root = loader.load();
            System.out.println("FXML file loaded successfully.");
            VoitureIndexAdmin controller = loader.getController();

            Stage stage = new Stage();
            stage.setTitle("Facture");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    private void assurance(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherAssuranceAdmin.fxml"));
        Parent root = null;
        try {
            Node source = (Node) event.getSource();
            root = loader.load();
            System.out.println("FXML file loaded successfully.");
            AfficherAssuranceAdmin controller = loader.getController();

            Stage stage = new Stage();
            stage.setTitle("Facture");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void constat(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherConstat.fxml"));
        Parent root = null;
        try {
            Node source = (Node) event.getSource();
            root = loader.load();
            System.out.println("FXML file loaded successfully.");
            AfficherConstat controller = loader.getController();

            Stage stage = new Stage();
            stage.setTitle("Facture");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void x(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/dashbordAdmin.fxml"));
        Parent root = null;
        try {
            Node source = (Node) event.getSource();
            root = loader.load();
            System.out.println("FXML file loaded successfully.");
            DashbordAdmin controller = loader.getController();

            Stage stage = (Stage) source.getScene().getWindow();
            stage.setTitle("Facture");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void profil(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UserEdit.fxml"));
        Parent root = null;
        try {
            Node source = (Node) event.getSource();
            root = loader.load();
            System.out.println("FXML file loaded successfully.");
            ModifyUser controller = loader.getController();


            Stage stage =new Stage() ;
            stage.setTitle("Facture");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void factureindex(ActionEvent event ) {
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
    public void offre(ActionEvent event ) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/admin/ListeOffre.fxml"));
        Parent root = null;
        try {
            Node source = (Node) event.getSource();
            root = loader.load();
            System.out.println("FXML file loaded successfully.");
            ListeOffreController controller = loader.getController();

            Stage stage = (Stage) source.getScene().getWindow();
            stage.setTitle("Facture");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void devis(ActionEvent event ) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/admin/ListDvis.fxml"));
        Parent root = null;
        try {
            Node source = (Node) event.getSource();
            root = loader.load();
            System.out.println("FXML file loaded successfully.");
            ListeDivis controller = loader.getController();

            Stage stage = (Stage) source.getScene().getWindow();
            stage.setTitle("Facture");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private TextField search;

    @FXML
    private Pagination pagination;
    private static final int ITEMS_PER_PAGE = 1;
    private static final int TOTAL_ITEMS = 100;
    @FXML
    private Button facture;


    public class ContratListCell extends ListCell<Contrat> {
        public void xxx(ActionEvent event , int id ,String cliente , String couverture, int engagement , String debut , String fin, int prix) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ContratShowAdmin.fxml"));
            Parent root = null;
            try {
                Node source = (Node) event.getSource();
                root = loader.load();
                System.out.println("FXML file loaded successfully.");
                ContratShowAdmin controller = loader.getController();
                controller.setId(id , cliente, couverture , engagement , debut,fin ,prix);
                Stage stage = (Stage) source.getScene().getWindow();
                stage.setTitle("Contrat");
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        protected void updateItem(Contrat contrat, boolean empty) {
            super.updateItem(contrat, empty);

            if (empty || contrat == null) {
                setText(null);
                setGraphic(null);
            } else {
                // Create an HBox to hold the contract details
                HBox card = new HBox();

                Label idLabel = new Label("Contrat N°" + contrat.getId());
                Label client = new Label("pour " + contrat.getClient());
                String couvertureText = contrat.getCouverture() != null ? contrat.getCouverture() : "";
                Label couvertureLabel = new Label( "" + couvertureText);
                String debutText = contrat.getDebut() != null ? contrat.getDebut().toString() : "";
                Label debutLabel = new Label("De " + debutText);
                String finText = contrat.getFin() != null ? contrat.getFin().toString() : "";
                Label finLabel = new Label("Jusqu'a " + finText);
                Label prixLabel = new Label(contrat.getPrix() + " DT");
                prixLabel.setStyle("-fx-font-weight: bold;");
                Button button1 = new Button("Modifier");
                Button button0 = new Button("Consulter");
                Button button2 = new Button("Supprimer");

                add.setStyle("-fx-background-color: #cacaca; -fx-padding: 10px; -fx-border-radius: 50px;");

                button0.setStyle("-fx-background-color: #cacaca; -fx-padding: 10px; -fx-border-radius: 50px;");
                button1.setStyle("-fx-background-color: #6e6e6e; -fx-padding: 10px; -fx-border-radius: 50px;");
                button2.setStyle("-fx-background-color: #ef1a1a; -fx-padding: 10px; -fx-border-radius: 50px;");

                idLabel.setMinWidth(70);
                idLabel.setMaxWidth(70);
                client.setMinWidth(70);
                client.setMaxWidth(70);
                debutLabel.setMinWidth(70);
                debutLabel.setMaxWidth(70);
                couvertureLabel.setMinWidth(70);
                couvertureLabel.setMaxWidth(70);
                finLabel.setMinWidth(70);
                finLabel.setMaxWidth(70);
                HBox buttonBox = new HBox(button0,button1, button2);
                buttonBox.setStyle("-fx-spacing: 10px;");
                card.getChildren().addAll(idLabel, client, prixLabel, couvertureLabel, debutLabel, finLabel, buttonBox);
//buttons actions
                button1.setOnAction(event -> {


                    int id = contrat.getId() ;int engagement = contrat.getEngagement() ;String couverture = contrat.getCouverture() ;
                    int prix = contrat.getPrix() ; String cliente = contrat.getClient();
                    String debut= contrat.getDebut().toString();

                    n.gotoedit(event, id , cliente, couverture,engagement, debut,prix);

                });
                button0.setOnAction(event -> {


                    int id = contrat.getId() ;int engagement = contrat.getEngagement() ;String couverture = contrat.getCouverture() ;
                    int prix = contrat.getPrix() ; String cliente = contrat.getClient();
                    String debut= contrat.getDebut().toString();String fin= contrat.getFin().toString();

                    xxx(event, id , cliente, couverture,engagement, debut,fin,prix);

                });
                button2.setOnAction(event -> {
                   contratService.delete(contrat.getId());
                   afficherContrats();
                });




                card.setStyle("-fx-alignment: center ;-fx-background-color: #6d89ef; -fx-padding: 10px; -fx-spacing: 10px;");
                setGraphic(card);
            }
        }}
    private void refreshContrats() {
        listView.getItems().clear(); // Clear the existing items
        listContrat.clear(); // Clear the list of contracts
        listContrat.addAll(contratService.getAll()); // Get the updated list of contracts
        ObservableList<Contrat> observableList = FXCollections.observableList(listContrat);
        listView.setItems(observableList); // Set the updated list to the ListView
    }


    @FXML
    public   void changeroute(ActionEvent event) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AjouterContrat.fxml"));
        Parent root = null;
        try {
            Node source = (Node) event.getSource();
            root = loader.load();
            System.out.println("FXML file loaded successfully.");
            AjouterContrat controller = loader.getController();
            Stage stage = (Stage) source.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Ajouter Contrat");

            // Set stage to full screen

            // Retrieve screen dimensions
            Rectangle2D screenBounds = Screen.getPrimary().getBounds();

            // Set stage width and height to screen dimensions
            stage.setWidth(screenBounds.getWidth());
            stage.setHeight(screenBounds.getHeight());

            stage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);

        }

    }

    private void afficherContrats() {
        // Fetch all contracts
        listContrat = contratService.getAll();

        // Calculate total pages for pagination
        int totalPageCount = (int) Math.ceil((double) listContrat.size() / ITEMS_PER_PAGE);
        pagination.setPageCount(totalPageCount);

        // Set up pagination to update ListView
        pagination.setPageFactory(pageIndex -> {
            int fromIndex = pageIndex * ITEMS_PER_PAGE;
            int toIndex = Math.min(fromIndex + ITEMS_PER_PAGE, listContrat.size());
            ObservableList<Contrat> pageContrats = FXCollections.observableArrayList(listContrat.subList(fromIndex, toIndex));
            listView.setItems(pageContrats);
            return new VBox(); // Placeholder, the actual content is set dynamically
        });
    }

    @FXML
    void search(KeyEvent event) {
        String searched = search.getText().trim().toLowerCase(); // Get the text from the search field

        if (!searched.isEmpty()) {

            List<Contrat> filteredContrats = listContrat.stream().filter(contrat ->
                            contrat.getClient().toLowerCase().contains(searched) ||
                                    String.valueOf(contrat.getPrix()).contains(searched) ||
                                    String.valueOf(contrat.getEngagement()).contains(searched) ||
                                    contrat.getCouverture().toLowerCase().contains(searched)
                    )
                    .collect(Collectors.toList());


            listView.getItems().clear();
            listView.getItems().addAll(filteredContrats);
        } else {

            afficherContrats();

        }
    }













    @FXML
    Button add ;

}
