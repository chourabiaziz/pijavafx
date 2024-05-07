import com.itextpdf.html2pdf.HtmlConverter;

import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static String processTemplate(Map<String, String> data) throws Exception {
        // Read the HTML template file
//        String htmlTemplate = new String(Files.readAllBytes(Paths.get("C:\\index.html")));
        String htmlTemplate = new String(Files.readAllBytes(Paths.get("C:\\PdfTemplate.html")));

        // Replace the placeholder {Bill} with the actual bill name or title
        htmlTemplate = htmlTemplate.replace("{Bill}", data.get("zabour"));

        // Replace other placeholders with the provided data
        htmlTemplate = htmlTemplate.replace("{{ref}}", data.get("ref"));
        htmlTemplate = htmlTemplate.replace("{{client}}", data.get("client"));
        htmlTemplate = htmlTemplate.replace("{{couverture}}", data.get("couverture"));
        htmlTemplate = htmlTemplate.replace("{{debut}}", data.get("debut"));
        htmlTemplate = htmlTemplate.replace("{{fin}}", data.get("fin"));
        htmlTemplate = htmlTemplate.replace("{{engagement}}", data.get("engagement"));
        htmlTemplate = htmlTemplate.replace("{{prix}}", data.get("prix"));
         htmlTemplate = htmlTemplate.replace("{{promotion}}", data.get("promotion"));
        htmlTemplate = htmlTemplate.replace("{{totale}}", data.get("totale"));

        return htmlTemplate;
    }
    public static void saveAsPdf(String html, String outputFile) throws Exception {
        // Convert HTML to PDF and save to file
        HtmlConverter.convertToPdf(html, new FileOutputStream(outputFile));
    }
    public static void main(String[] args) {
        // Example usage
        Map<String, String> data = new HashMap<>();
        data.put("zabour", "My zebi zebi");
        data.put("ref", "13");
        data.put("client", "aziiiizzz chourabi");
        data.put("couverture", "tous risque");
        data.put("debut", "2024-05-01");
        data.put("fin", "2024-10-01");
        data.put("engagement", "5 Mois");
        data.put("prix", "250 TND");

        data.put("promotion", "100 %");
        data.put("totale", "47 TND");

        try {
            String processedHtml = processTemplate(data);
            saveAsPdf(processedHtml, "outpuaat.pdf");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

