package tn.esprit.utils;


import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OCRManager {
    private static ITesseract tesseract;

    public OCRManager() {

        tesseract = new Tesseract();
        tesseract.setDatapath("C:\\Users\\maryo\\IdeaProjects\\tesseract-main\\tessdata");

    }

    public static String getTextFromImage(File imageFile) throws IOException, TesseractException {

        // Extract the text from the image using tesseract
        return tesseract.doOCR(imageFile);
    }

    public static String getNomFromImage(File imageFile) throws IOException, TesseractException {
        // Extract the text from the image using Tesseract
        String text = tesseract.doOCR(imageFile);

        // Search for the "Nom :" label and extract the name
        int indexNom = text.indexOf("Nom");
        if (indexNom != -1) {
            String nomText = text.substring(indexNom + "Nom".length()).trim();
            // Find the index of ':' after "Nom :"
            int colonIndex = nomText.indexOf(':');
            if (colonIndex != -1) {
                // Extract the text after ':' and trim leading/trailing spaces
                String name = nomText.substring(colonIndex + 1).trim();
                // If there's a new line, only get text before it
                int newlineIndex = name.indexOf('\n');
                if (newlineIndex != -1) {
                    name = name.substring(0, newlineIndex);
                }
                return name;
            } else {
                // If ':' is not found, return the entire text after "Nom :"
                return nomText;
            }
        }

        return ""; // Return an empty string if the name is not found
    }

    public static String getPrenomFromImage(File imageFile) throws IOException, TesseractException {
        // Extract the text from the image using Tesseract
        String text = tesseract.doOCR(imageFile);

        // Search for the "Nom :" label and extract the name
        int indexNom = text.indexOf("Prénom");
        if (indexNom != -1) {
            String nomText = text.substring(indexNom + "Prénom".length()).trim();
            // Find the index of ':' after "Nom :"
            int colonIndex = nomText.indexOf(':');
            if (colonIndex != -1) {
                // Extract the text after ':' and trim leading/trailing spaces
                String name = nomText.substring(colonIndex + 1).trim();
                // If there's a new line, only get text before it
                int newlineIndex = name.indexOf('\n');
                if (newlineIndex != -1) {
                    name = name.substring(0, newlineIndex);
                }
                return name;
            } else {
                // If ':' is not found, return the entire text after "Nom :"
                return nomText;
            }
        }

        return ""; // Return an empty string if the name is not found
    }

    public static String getCinFromImage(File imageFile) throws IOException, TesseractException {
        String text = tesseract.doOCR(imageFile);

        // Définir un motif pour rechercher les chiffres de la carte d'identité
        Pattern pattern = Pattern.compile("\\b\\d{11}\\b"); // Correspond à un groupe de 8 chiffres

        // Créer un objet Matcher pour rechercher le motif dans le texte
        Matcher matcher = pattern.matcher(text);

        // Vérifier s'il y a une correspondance
        if (matcher.find()) {
            // Extraire et retourner la première correspondance de chiffres
            return matcher.group();
        }

        // Retourner une chaîne vide si aucun chiffre de carte d'identité n'est trouvé
        return "";
    }


}
