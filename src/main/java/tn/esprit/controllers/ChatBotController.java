package tn.esprit.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ChatBotController {

    @FXML
    private TextArea chatArea;

    @FXML
    private TextField messageField;

    @FXML
    private Button sendButton;

    private final String API_KEY = "ecabed1f6cmsh83453c0745fde56p19b3c7jsn58682417f59b";

    public void sendMessage() {
        // Get the user's message from the message field
        String message = messageField.getText();

        // Append the user's message to the chat area
        chatArea.appendText("You: " + message + "\n");

        // Construct the JSON request
        String jsonRequest = "{\r\n    \"messages\": [\r\n        {\r\n            \"role\": \"user\",\r\n            \"content\": \"" + message + "\"\r\n        }\r\n    ],\r\n    \"web_access\": false\r\n}";

        // Build the HTTP request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://open-ai21.p.rapidapi.com/chatgpt"))
                .header("content-type", "application/json")
                .header("X-RapidAPI-Key", API_KEY)
                .header("X-RapidAPI-Host", "open-ai21.p.rapidapi.com")
                .method("POST", HttpRequest.BodyPublishers.ofString(jsonRequest))
                .build();

        // Send the HTTP request and handle the response
        HttpClient.newHttpClient().sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(this::handleResponse)
                .exceptionally(e -> {
                    chatArea.appendText("Error occurred: " + e.getMessage() + "\n");
                    return null;
                });

        // Set focus back to the message field after sending the message
        messageField.requestFocus();
    }


    private void handleResponse(String responseBody) {
        // Update UI with the response
        chatArea.appendText(responseBody + "\n");
    }
}