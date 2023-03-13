package com.ginrad.finance.analyser.receipts.model;

import java.util.List;

public class Product {

    private String description;
    private List<String> alternativeDescriptions;
    private String brand;
    private String category;    // eg, food 	TODO: Consider mechanisms to limit to defined list of categories.
    private String type;        // eg, milk		TODO: Consider mechanisms to limit to defined list of types.
}
