package com.ginrad.finance.analyser.receipts.service.implementation;

import com.ginrad.finance.analyser.receipts.model.Receipt;
import com.ginrad.finance.analyser.receipts.service.ReceiptsService;
import com.ginrad.finance.analyser.receipts.util.*;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import net.sourceforge.tess4j.TesseractException;
import org.springframework.stereotype.Service;

@Service
public class DefaultReceiptsService implements ReceiptsService {

    @Value("${store.receipts.images:/}")
    private String receiptImagesFilestoreBase;

    private static final String INBOX_PREFIX = "inbox";

    @Override
    public List<Receipt> listAllReceipts() {
        return null;
    }

    @Override
    public Receipt getReceiptById(String receiptId) {
        return null;
    }

    @Override
    public List<Receipt> listReceiptsByMerchant(String merchantId) {
        // TODO: Implement method listReceiptsByMerchant
        return null;
    }

    @Override
    public List<Receipt> listReceiptsByDateParams(Map<String, String> params) {
        // TODO: Implement method listReceiptsByDateParams
        return null;
    }

    @Override
    public Receipt handleReceiptFileUpload(InputStream stream, String originalImageReference)
            throws ImageAccessException, ImageProcessingException {
        try {
            String filePath = writeImageFileToDisk(stream, originalImageReference);
            String fileText = OcrReceiptConverter.doOcr(filePath, "eng");
            // TODO: Parameterise first pass language as per user preference

            // Date timestamp = ReceiptParseUtils.extractDateTryingMultipleFormats(fileText);
            FormattedDateTimeMatcher matcher = new FormattedDateTimeMatcher();
            String timestamp = matcher.match(fileText);

            if (timestamp == null) {
                throw new ParseException("Timestamp could not be extracted from text of converted image...", 0);
            }

            //SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.ENGLISH);
            //formatter.setTimeZone(TimeZone.getTimeZone("Europe/Vilnius"));
            //timestampStr = formatter.format(timestamp);

            return new Receipt.ReceiptBuilder(filePath)
                    .withConvertedText(fileText)
                    .withFormat(timestamp)
                    .build();

        } catch (IOException ioe) {
            throw new ImageAccessException(ioe);
        } catch (TesseractException te) {
            throw new ImageProcessingException(te);
        } catch (ParseException pe) {
            throw new ImageProcessingException(pe);
        }
    }

    private String writeImageFileToDisk(InputStream inputStream, String originalImageReference) throws IOException {
        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMddHHmmss-"));
        String fileName = date + originalImageReference;

        String filePath = receiptImagesFilestoreBase + INBOX_PREFIX + File.separator + fileName;

        // Copies inputStream to given path and generated name
        Files.copy(inputStream, Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
        return filePath;
    }

}
