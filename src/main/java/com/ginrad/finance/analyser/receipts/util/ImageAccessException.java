package com.ginrad.finance.analyser.receipts.util;

public class ImageAccessException extends Exception {
    public ImageAccessException(Throwable cause) {
        super("Error while accessing image... ", cause);
    }
}
