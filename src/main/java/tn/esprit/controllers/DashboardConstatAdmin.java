package tn.esprit.controllers;

import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.layout.VBox;
import tn.esprit.services.ServiceConstat;
import java.util.Map;
public class DashboardConstatAdmin {

    @FXML
    private PieChart pieChart;

    public void initialize() {
        // Retrieve statistics from the database
        // Appeler la méthode getPieChart de ServiceConstat pour récupérer le PieChart
        PieChart chart = new ServiceConstat().getPieChart();

        // Effacer toute donnée existante dans le PieChart
        pieChart.getData().clear();

        // Ajouter les données du PieChart récupéré à partir de ServiceConstat
        pieChart.getData().addAll(chart.getData());
    }


}

