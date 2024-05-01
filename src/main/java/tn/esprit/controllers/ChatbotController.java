package tn.esprit.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.charset.StandardCharsets;

public class ChatbotController {
    @FXML
    private TextArea chatArea;
    @FXML
    private TextField inputField;

    private HttpClient httpClient = HttpClient.newHttpClient();
    private final String apiKey = System.getenv("");
    private final String apiEndpoint = "https://api.openai.com/v1/chat/completions";

    public void sendMessage() {
        String message = inputField.getText(); // Get text from inputField
        if (!message.isEmpty()) {
            String response = callChatGPTAPI(message); // Call the ChatGPT API with the message
            chatArea.appendText("You: " + message + "\n"); // Append the user message to the chat area
            chatArea.appendText("ChatGPT: " + response + "\n"); // Append the API response to the chat area
            inputField.clear(); // Clear the input field after sending the message
        }
    }

    private String callChatGPTAPI(String input) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiEndpoint))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + apiKey)
                .POST(BodyPublishers.ofString(
                        "{\"model\": \"gpt-3.5-turbo\", \"messages\": [{\"role\": \"user\", \"content\": \"" + input + "\"}]}",
                        StandardCharsets.UTF_8))
                .build();

        try {
            HttpResponse<String> response = httpClient.send(request, BodyHandlers.ofString());
            return response.body(); // Return the API response
        } catch (Exception e) {
            return "Error communicating with the API: " + e.getMessage(); // Handle exceptions properly
        }
    }
}
