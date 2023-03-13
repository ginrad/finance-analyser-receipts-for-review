package com.ginrad.finance.analyser.receipts.model;

import java.util.ArrayList;
import java.util.List;

public class Merchant {

    private String id;
    private String name;
    private List<MerchantStore> stores;

    public Merchant(String id, String name, List<MerchantStore> stores) {
        this.name = name;
        this.stores = stores;
    }

    public Merchant(String id, String name) {
        this(id, name, new ArrayList<>());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MerchantStore> getStores() {
        return stores;
    }

    public void setStores(List<MerchantStore> stores) {
        this.stores = stores;
    }

    public void addStore(MerchantStore store) {
        this.stores.add(store);
    }
}
