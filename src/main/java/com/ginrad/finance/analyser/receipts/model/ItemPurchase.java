package com.ginrad.finance.analyser.receipts.model;

public abstract class ItemPurchase {

    private Product product;
    private Float unitPrice;
    private Float totalPrice;
    private String currency;    // TODO: Consider using Enum
}
