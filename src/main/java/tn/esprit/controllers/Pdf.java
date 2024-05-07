package tn.esprit.controllers;

import com.itextpdf.html2pdf.HtmlConverter;

import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Pdf {


 public  String  ref,client,couverture,debut,fin,engagement,prix,promotion ,totale ;


    public static String processTemplate(Map<String, String> data) throws Exception {
        String htmlTemplate = new String(Files.readAllBytes(Paths.get("C:\\PdfTemplate.html")));
        // Replace placeholders with actual data
        htmlTemplate = htmlTemplate.replace("{{ref}}", data.get("ref"));
        htmlTemplate = htmlTemplate.replace("{{client}}", data.get("client"));
        htmlTemplate = htmlTemplate.replace("{{couverture}}", data.get("couverture"));
        htmlTemplate = htmlTemplate.replace("{{debut}}", data.get("debut"));
        htmlTemplate = htmlTemplate.replace("{{fin}}", data.get("fin"));
        htmlTemplate = htmlTemplate.replace("{{engagement}}", data.get("engagement"));
        htmlTemplate = htmlTemplate.replace("{{prix}}", data.get("prix"));
        htmlTemplate = htmlTemplate.replace("{{promotion}}", data.get("promotion"));
        htmlTemplate = htmlTemplate.replace("{{tot}}", data.get("tot"));
        return htmlTemplate;
    }

    // Modified saveAsPdf method to accept the file path
    public static void saveAsPdf(String html, String outputFile) throws Exception {
        HtmlConverter.convertToPdf(html, new FileOutputStream(outputFile));
    }

    // Your existing saveAsPDF method
    private void saveAsPDF(Window window, String htmlContent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save PDF File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
        File file = fileChooser.showSaveDialog(window);
        if (file != null) {
            try {
                saveAsPdf(htmlContent, file.getAbsolutePath()); // Save PDF at the chosen location
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // Your existing download method
    public void download(String ref, String client, String couverture, String debut, String fin, String engagement, String prix, String promotion, String totale) {
        Map<String, String> data = new HashMap<>();
        data.put("ref", ref);
        data.put("client", client);
        data.put("couverture", couverture);
        data.put("debut", debut);
        data.put("fin", fin);
        data.put("engagement", engagement);
        data.put("prix", prix);
        data.put("promotion", promotion);
        data.put("tot", totale);

        try {
            String processedHtml = processTemplate(data);
            saveAsPDF(null, processedHtml); // Pass the window reference if available, otherwise pass null
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
