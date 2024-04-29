package tn.esprit.utils;


import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
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

}
