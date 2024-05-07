package tn.esprit.controllers;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Account;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import netscape.javascript.JSObject;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class Payment  implements Initializable {


    @FXML
    private TextField cardNumberField;

    @FXML
    private TextField cardholderNameField;

    @FXML
    private TextField countryField;

    @FXML
    private TextField cvcField;

    @FXML
    private TextField expirationMonthField;

    @FXML
    private TextField expirationYearField;

    @FXML
    private Text prix;

    @FXML
    private Button retour;

    @FXML
    private Button submit;

    public int pr ;


    public void setId(int pr){
        this.pr = pr ;
        updateUi();
    }

    private void updateUi() {
        prix.setText(pr+"");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        cardNumberField.setOnKeyReleased(event -> validateCardNumber());
        expirationMonthField.setOnKeyReleased(event -> validateExpirationMonth());
        expirationYearField.setOnKeyReleased(event -> validateExpirationYear());
        cardholderNameField.setOnKeyReleased(event -> validateCardholderName());
        countryField.setOnKeyReleased(event -> validateCountry());
        cvcField.setOnKeyReleased(event -> validateCVC());
        submit.setOnAction(this::processPayment);

    }
    private void validateCardNumber() {
        String input = cardNumberField.getText();
        // Validate card number (e.g., 16 digits)
        if (!Pattern.matches("\\d{16}", input)) {
            cardNumberField.setStyle("-fx-border-color: red;");
        } else {
            cardNumberField.setStyle("-fx-border-color: none;");
        }
    }

    private void validateExpirationMonth() {
        String input = expirationMonthField.getText();
        // Validate expiration month (e.g., 2 digits between 01 and 12)
        if (!Pattern.matches("^(0[1-9]|1[0-2])$", input)) {
            expirationMonthField.setStyle("-fx-border-color: red;");
        } else {
            expirationMonthField.setStyle("-fx-border-color: none;");
        }
    }

    private void validateExpirationYear() {
        String input = expirationYearField.getText();
        // Validate expiration year (e.g., 4 digits)
        if (!Pattern.matches("\\d{4}", input)) {
            expirationYearField.setStyle("-fx-border-color: red;");
        } else {
            expirationYearField.setStyle("-fx-border-color: none;");
        }
    }

    private void validateCardholderName() {
        String input = cardholderNameField.getText();
        // No specific validation for cardholder name in this example
        // You can add your own validation logic as needed
    }

    private void validateCountry() {
        String input = countryField.getText();
        // No specific validation for country in this example
        // You can add your own validation logic as needed
    }

    private void validateCVC() {
        String input = cvcField.getText();
        // Validate CVC (e.g., 3 digits)
        if (!Pattern.matches("\\d{3}", input)) {
            cvcField.setStyle("-fx-border-color: red;");
        } else {
            cvcField.setStyle("-fx-border-color: none;");
        }
    }
    private void processPayment(ActionEvent event) {
        try {
            Stripe.apiKey = "sk_test_51P9mzx08ZPBTxeQJ65MEjAfcnQfq8I8ekDhjcFD0ZPnD2AbwvbCSDm8cKNX7TwoexbTCNJTL3zKY8aNV5ujXgrPg00x9JUN7K6";
            PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                    .setAmount(1000L) // Amount in cents (e.g., $10.00)
                    .setCurrency("usd")
                    .build();
            PaymentIntent intent = PaymentIntent.create(params);
            System.out.println("Payment successful. PaymentIntent ID: " + intent.getId());
            Parent root ;
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Succès");
            alert.setHeaderText("Payment successful. PaymentIntent ID:" + intent.getId());
            alert.showAndWait();
            Node source = (Node) event.getSource();

            Stage stage = (Stage) source.getScene().getWindow();
                stage.close();


        } catch (StripeException e) {
             System.out.println("Payment failed. Error: " + e.getMessage());
        }
    }






}




