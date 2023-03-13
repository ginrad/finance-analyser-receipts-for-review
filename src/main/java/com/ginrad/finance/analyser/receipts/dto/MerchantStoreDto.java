package com.ginrad.finance.analyser.receipts.dto;

import com.ginrad.finance.analyser.receipts.model.Merchant;

public class MerchantStoreDto {

    private String id;
    private String merchantId;
    private String merchantName;
    private String country;
    private String address;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean hasValidMerchantId() {
        return merchantId != null && !merchantId.isBlank();
    }
}
