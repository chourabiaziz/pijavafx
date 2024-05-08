package tn.esprit.controllers;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;
import tn.esprit.services.ServiceConstat;
import java.util.Map;
public class DashboardConstatAdmin {


    @FXML
    private VBox dashboardContainer;

    @FXML
    private BarChart<String, Integer> barChart;

    @FXML
    private CategoryAxis xAxis;

    @FXML
    private NumberAxis yAxis;



    public void initialize() {
        // Retrieve statistics from the database
        Map<String, Integer> userConstats = new ServiceConstat().getConstatsPerUser();

        // Create a series for the bar chart
        XYChart.Series<String, Integer> series = new XYChart.Series<>();

        // Add data points to the series
        for (Map.Entry<String, Integer> entry : userConstats.entrySet()) {
            series.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
        }

        // Add the series to the bar chart
        barChart.getData().add(series);

    }
    }

