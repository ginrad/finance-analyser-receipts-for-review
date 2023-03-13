package com.ginrad.finance.analyser.receipts.service;

import com.ginrad.finance.analyser.receipts.model.Merchant;
import com.ginrad.finance.analyser.receipts.model.MerchantStore;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MerchantsService {
    public List<Merchant> listAllMerchants();
    public Merchant getMerchantById(String merchantId);
    public List<Merchant> listMerchantsWithName(String merchantName);
    public List<MerchantStore> listAllStores();
    public List<MerchantStore> listAllStoresForMerchant(String merchantId);
    public MerchantStore getMerchantStoreById(String storeId);

    public Merchant addMerchant(Merchant newMerchant);
    public Merchant updateMerchant(Merchant newMerchant);
    public MerchantStore addMerchantStore(MerchantStore newMerchantStore);
    public MerchantStore updateMerchantStore(MerchantStore newMerchantStore);

}
