package tn.esprit.controllers;

import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.ResourceBundle;

public class ChatbotIntegration implements Initializable {


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();

        // Charger le script du chatbot dans WebView
        webEngine.loadContent("<script type=\"text/javascript\">\n" +
                "      (function (d, s, id) {\n" +
                "        var js, el = d.getElementsByTagName(s)[0];\n" +
                "        if (d.getElementById(id)) return;\n" +
                "        js = d.createElement(s);\n" +
                "        js.async = true;\n" +
                "        js.src = 'https://s3.ap-south-1.amazonaws.com/conferbot.defaults/dist/v1/widget.min.js';\n" +
                "        js.id = id;\n" +
                "        js.charset = 'UTF-8';\n" +
                "        el.parentNode.insertBefore(js, el);\n" +
                "        js.onload = function () {\n" +
                "          var w = window.ConferbotWidget(\"65e92445b2b701438767598c\", \"fullpage_chat\");\n" +
                "        };\n" +
                "      })(document, 'script', 'conferbot-js');\n" +
                "    </script>");

        // Créer une scène contenant le WebView
        VBox root = new VBox(webView);
        Scene scene = new Scene(root, 800, 600);

        // Configurer la scène et afficher la fenêtre principale

    }

}
