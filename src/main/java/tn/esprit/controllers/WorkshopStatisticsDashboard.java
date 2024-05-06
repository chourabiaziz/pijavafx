//package tn.esprit.controllers;
//
//import javafx.application.Application;
//import javafx.scene.Scene;
//import javafx.scene.chart.*;
//import javafx.scene.control.Label;
//import javafx.scene.layout.VBox;
//import javafx.stage.Stage;
//import tn.esprit.utils.MyDataBase;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//public class WorkshopStatisticsDashboard extends Application {
//
//    private Connection connection;
//
//    @Override
//    public void start(Stage primaryStage) {
//        primaryStage.setTitle("Workshop Statistics Dashboard");
//
//        // Connexion à la base de données
//        connection = MyDataBase.getInstance().getCnx();
//
//        // Création des graphiques
//        PieChart breakdownPieChart = createBreakdownPieChart();
//        BarChart<String, Number> availabilityBarChart = createAvailabilityBarChart();
//        LineChart<String, Number> repairTrendLineChart = createRepairTrendLineChart();
//        BarChart<String, Number> costBarChart = createCostBarChart();
//
//        // Création des étiquettes
//        Label breakdownLabel = new Label("Breakdown by Type");
//        Label availabilityLabel = new Label("Car Availability by Model");
//        Label repairTrendLabel = new Label("Monthly Repair Trends");
//        Label costLabel = new Label("Average Repair Costs by Type");
//
//        // Création de la disposition
//        VBox vbox = new VBox();
//        vbox.getChildren().addAll(breakdownLabel, breakdownPieChart, availabilityLabel, availabilityBarChart,
//                repairTrendLabel, repairTrendLineChart, costLabel, costBarChart);
//
//        primaryStage.setScene(new Scene(vbox, 800, 600));
//        primaryStage.show();
//    }
//
//    private PieChart createBreakdownPieChart() {
//        PieChart pieChart = new PieChart();
//        try {
//            String query = "SELECT type, COUNT(*) as count FROM pannes GROUP BY type";
//            PreparedStatement statement = connection.prepareStatement(query);
//            ResultSet resultSet = statement.executeQuery();
//
//            while (resultSet.next()) {
//                String type = resultSet.getString("type");
//                int count = resultSet.getInt("count");
//                pieChart.getData().add(new PieChart.Data(type, count));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return pieChart;
//    }
//
//    private BarChart<String, Number> createAvailabilityBarChart() {
//        BarChart<String, Number> barChart = new BarChart<>(new CategoryAxis(), new NumberAxis());
//        try {
//            String query = "SELECT modele, COUNT(*) as count FROM voitures GROUP BY modele";
//            PreparedStatement statement = connection.prepareStatement(query);
//            ResultSet resultSet = statement.executeQuery();
//
//            while (resultSet.next()) {
//                String model = resultSet.getString("modele");
//                int count = resultSet.getInt("count");
//// Create a new series
//                XYChart.Series<String, Number> series = new XYChart.Series<>();
//
//// Add data to the series
//                series.getData().add(new XYChart.Data<>(model, count));
//
//// Add the series to your bar chart
//                barChart.getData().add(series);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return barChart;
//    }
//
//    private LineChart<String, Number> createRepairTrendLineChart() {
//        LineChart<String, Number> lineChart = new LineChart<>(new CategoryAxis(), new NumberAxis());
//        try {
//            String query = "SELECT MONTH(date_reparation) as month, COUNT(*) as count FROM reparations GROUP BY MONTH(date_reparation)";
//            PreparedStatement statement = connection.prepareStatement(query);
//            ResultSet resultSet = statement.executeQuery();
//
//            XYChart.Series<String, Number> series = new XYChart.Series<>();
//            while (resultSet.next()) {
//                int month = resultSet.getInt("month");
//                int count = resultSet.getInt("count");
//                series.getData().add(new XYChart.Data<>(String.valueOf(month), count));
//            }
//            lineChart.getData().add(series);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return lineChart;
//    }
//
//    private BarChart<String, Number> createCostBarChart() {
//        BarChart<String, Number> barChart = new BarChart<>(new CategoryAxis(), new NumberAxis());
//        try {
//            String query = "SELECT type_panne, AVG(cout) as average_cost FROM reparations GROUP BY type_panne";
//            PreparedStatement statement = connection.prepareStatement(query);
//            ResultSet resultSet = statement.executeQuery();
//
//            while (resultSet.next()) {
//                String type = resultSet.getString("type_panne");
//                double averageCost = resultSet.getDouble("average_cost");
//// Create a new series
//                XYChart.Series<String, Number> series = new XYChart.Series<>();
//
//// Add data to the series
//                series.getData().add(new XYChart.Data<>(type, averageCost));
//
//// Add the series to your bar chart
//                barChart.getData().add(series);            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return barChart;
//    }
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//}
