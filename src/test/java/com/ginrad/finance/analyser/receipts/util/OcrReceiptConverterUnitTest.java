package com.ginrad.finance.analyser.receipts.util;

import org.junit.Assert;
import org.junit.Test;

public class OcrReceiptConverterUnitTest {

    private DateMatcher matcher = new FormattedDateTimeMatcher();

    @Test
    public void givenAnImageFileExistsInALocalLocation_whenUsingOcrReceiptConverter_thenTheFileIsConvertedToText() {
        String SAMPLE_IMAGE_1_FILEPATH = "src/test/resources/Java-Development-Kit-JDK.jpg";

        try {
            String fileText = OcrReceiptConverter.doOcr(SAMPLE_IMAGE_1_FILEPATH, "eng");
            Assert.assertTrue(fileText.length() > 0);
        } catch (Exception e) {
            Assert.assertFalse(false);
        }
    }
}

