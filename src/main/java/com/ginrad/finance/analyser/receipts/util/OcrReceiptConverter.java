package com.ginrad.finance.analyser.receipts.util;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.io.File;

public class OcrReceiptConverter {
    public static String doOcr(String filePath, String language) throws TesseractException {
        File image = new File(filePath);
        System.out.println(image.toString());
        Tesseract tesseract = new Tesseract();
        tesseract.setDatapath("src/main/resources/tessdata");
        tesseract.setLanguage(language);
        tesseract.setPageSegMode(1);
        tesseract.setOcrEngineMode(1);
        String result = tesseract.doOCR(image);
        return result;
    }

    private OcrReceiptConverter() {}

    private static class SingletonHolder {
        public static final OcrReceiptConverter instance = new OcrReceiptConverter();
    }

    public static OcrReceiptConverter getInstance() {
        return SingletonHolder.instance;
    }
}
