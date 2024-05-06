package tn.esprit.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

public class StatsController {

    @FXML
    private TextField inputField;

    @FXML
    private TextField resultField;

    @FXML
    private void calculateMean() {
        try {
            String[] numbers = inputField.getText().split(",");
            DescriptiveStatistics stats = new DescriptiveStatistics();
            for (String number : numbers) {
                stats.addValue(Double.parseDouble(number.trim()));
            }
            double mean = stats.getMean();
            resultField.setText(String.format("%.2f", mean));
        } catch (Exception e) {
            resultField.setText("Invalid input");
        }
    }
}
