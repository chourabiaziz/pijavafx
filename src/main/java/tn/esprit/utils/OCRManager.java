package tn.esprit.utils;


import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.io.File;
import java.io.IOException;

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

}
