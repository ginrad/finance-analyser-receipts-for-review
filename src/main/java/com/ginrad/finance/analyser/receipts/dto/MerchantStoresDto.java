package com.ginrad.finance.analyser.receipts.dto;

import java.util.ArrayList;
import java.util.List;

public class MerchantStoresDto {
    private List<MerchantStoreDto> stores;

    public MerchantStoresDto() {
        stores = new ArrayList<>();
    }

    public List<MerchantStoreDto> getStores() {
        return stores;
    }

    public void addStore(MerchantStoreDto store) {
        this.stores.add(store);
    }
}
