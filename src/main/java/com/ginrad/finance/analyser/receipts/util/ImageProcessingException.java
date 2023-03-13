package com.ginrad.finance.analyser.receipts.util;

public class ImageProcessingException extends Exception {
    public ImageProcessingException(Throwable cause) {
        super("Error while processing image... ", cause);
    }
}
