package com.ginrad.finance.analyser.receipts.service.implementation;

import com.ginrad.finance.analyser.receipts.model.Merchant;
import com.ginrad.finance.analyser.receipts.model.MerchantStore;
import com.ginrad.finance.analyser.receipts.service.MerchantsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultMerchantsService implements MerchantsService {
    @Override
    public List<Merchant> listAllMerchants() {
        return null;
    }

    @Override
    public Merchant getMerchantById(String merchantId) {
        return null;
    }

    @Override
    public List<Merchant> listMerchantsWithName(String merchantName) {
        return null;
    }

    @Override
    public List<MerchantStore> listAllStores() {
        return null;
    }

    @Override
    public List<MerchantStore> listAllStoresForMerchant(String merchantId) {
        return null;
    }

    @Override
    public MerchantStore getMerchantStoreById(String storeId) {
        return null;
    }

    @Override
    public Merchant addMerchant(Merchant newMerchant) {
        return null;
    }

    @Override
    public Merchant updateMerchant(Merchant newMerchant) {
        return null;
    }

    @Override
    public MerchantStore addMerchantStore(MerchantStore newMerchantStore) {
        return null;
    }

    @Override
    public MerchantStore updateMerchantStore(MerchantStore newMerchantStore) {
        return null;
    }
}
