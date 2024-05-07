package tn.esprit.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.net.URL;
import java.util.ResourceBundle;

public class Stats implements Initializable {



    @FXML
    private BarChart<String, Number> chart2;

    @FXML
    private CategoryAxis xAxis;

    @FXML
    private NumberAxis yAxis;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {





        ObservableList<String> xData = FXCollections.observableArrayList("A", "B", "C", "D");
        ObservableList<Number> yData = FXCollections.observableArrayList(10, 20, 30, 40);

        // Set data to X and Y axes
        xAxis.setCategories(xData);
        yAxis.setTickUnit(10); // Example: set tick unit for Y axis
        yAxis.setLabel("Y Axis Label"); // Example: set label for Y axis

        addDataToChart(xData, yData);
    }
    private void addDataToChart(ObservableList<String> xData, ObservableList<Number> yData) {
        // Assume you have a method to create series and add it to the chart
        // For simplicity, let's assume you only have one series
        chart2.getData().add(createSeries("Series 1", xData, yData));
    }

    private XYChart.Series<String, Number> createSeries(String name, ObservableList<String> xData, ObservableList<Number> yData) {
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName(name);
        for (int i = 0; i < xData.size(); i++) {
            series.getData().add(new XYChart.Data<>(xData.get(i), yData.get(i)));
        }
        return series;
    }
}
