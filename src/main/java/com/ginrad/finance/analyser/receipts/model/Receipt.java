package com.ginrad.finance.analyser.receipts.model;

public class Receipt {

    private String id;
    private String imageReference;
    private String format;
    private String convertedText;

    private Receipt(ReceiptBuilder builder) {
        this.id = builder.id;
        this.imageReference = builder.imageReference;
        this.format = builder.format;
        this.convertedText = builder.convertedText;
    }

    public static class ReceiptBuilder {

        private String id;
        private final String imageReference;
        private String format;
        private String convertedText;

        // All mandatory parameters goes with this constructor
        public ReceiptBuilder(String imageReference) {
            this.imageReference = imageReference;
        }

        // Setters for optional parameters
        public ReceiptBuilder withId(String id) {
            this.id = id;
            return this;
        }

        public ReceiptBuilder withFormat(String format) {
            this.format = format;
            return this;
        }

        // Setters for optional parameters
        public ReceiptBuilder withConvertedText(String convertedText) {
            this.convertedText = convertedText;
            return this;
        }

        // Prepares and returns a Receipt object
        public Receipt build() {
            return new Receipt(this);
        }
    }

    public String getId() {
        return id;
    }

    public String getImageReference() {
        return imageReference;
    }

    public String getFormat() {
        return format;
    }

    public String getConvertedText() {
        return convertedText;
    }
}
