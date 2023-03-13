package com.ginrad.finance.analyser.receipts.controller;

import com.ginrad.finance.analyser.receipts.dto.MerchantDto;
import com.ginrad.finance.analyser.receipts.dto.MerchantStoreDto;
import com.ginrad.finance.analyser.receipts.model.Merchant;
import com.ginrad.finance.analyser.receipts.model.MerchantStore;
import com.ginrad.finance.analyser.receipts.service.MerchantsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.text.ParseException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

//Controller
@RestController
//@RequestMapping(path = "merchants", produces = "application/json")
@RequestMapping(path = "merchants")
public class MerchantsController {

    @Autowired
    MerchantsService merchantsService;

    @Autowired
    private ModelMapper modelMapper;

    /* GET mappings */
    @GetMapping(path = "")
    public List<MerchantDto> listAllMerchants() {
        return merchantsService.listAllMerchants()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    // TODO: Implement pagination on listAllMerchants

    @GetMapping(path = "/{merchantId}")
    public MerchantDto getMerchant(@PathVariable String merchantId) {
        return convertToDto(merchantsService.getMerchantById(merchantId));
    }

    @GetMapping(path = "/stores")
    public List<MerchantStoreDto> listAllStores(@RequestParam(required = false) String merchantId) {
        List<MerchantStore> stores;
        if (merchantId != null && !merchantId.isBlank()) {
            stores = merchantsService.listAllStoresForMerchant(merchantId);
        } else {
            stores = merchantsService.listAllStores();
        }

        return stores.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    // TODO: Extend the list of request params to allow filtering by other fields

    @GetMapping(path = "/stores/{storeId}")
    public MerchantStoreDto getMerchantStore(@PathVariable String storeId) {
        return convertToDto(merchantsService.getMerchantStoreById(storeId));
    }

    /* POST/PUT mappings */
    @PostMapping(path = "")
    @ResponseStatus(HttpStatus.CREATED)
    public MerchantDto addMerchant(@RequestBody MerchantDto newMerchantDto) {
        try {
            Merchant merchant = convertToModel(newMerchantDto);
            Merchant postCreated = merchantsService.addMerchant(merchant);
            return convertToDto(postCreated);
        } catch (ParseException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Could not understand the request body", e);
        }
    }

    @PutMapping(path = "/{merchantId}")
    @ResponseStatus(HttpStatus.OK)
    public void replaceMerchant(@RequestBody MerchantDto merchantDto, @PathVariable String merchantId) {
        if (!Objects.equals(merchantId, merchantDto.getId())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id in request body does not match Id in path");
        }
        try {
            Merchant merchant = convertToModel(merchantDto);
            merchantsService.updateMerchant(merchant);
        } catch (ParseException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Could not understand the request body", e);
        }
    }

    @PostMapping(path = "/stores")
    @ResponseStatus(HttpStatus.CREATED)
    public MerchantStoreDto addMerchantStore(@RequestBody MerchantStoreDto newMerchantStoreDto, @PathVariable String merchantId) {
        try {
            MerchantStore merchantStore = convertToModel(newMerchantStoreDto);
            MerchantStore postCreated = merchantsService.addMerchantStore(merchantStore);
            return convertToDto(postCreated);
        } catch (ParseException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Could not understand the request body", e);
        }
    }

    @PutMapping(path = "/stores/{storeId}")
    @ResponseStatus(HttpStatus.OK)
    public void replaceMerchantStore(@RequestBody MerchantStoreDto merchantStoreDto, @PathVariable String storeId) {
        if (!Objects.equals(storeId, merchantStoreDto.getId())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id in request body does not match Id in path");
        }
        try {
            MerchantStore merchantStore = convertToModel(merchantStoreDto);
            merchantsService.updateMerchantStore(merchantStore);
        } catch (ParseException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Could not understand the request body", e);
        }
    }

    /* DTO conversion methods */
    private MerchantDto convertToDto(Merchant merchant) {
        MerchantDto merchantDto = modelMapper.map(merchant, MerchantDto.class);
        return merchantDto;
    }

    private MerchantStoreDto convertToDto(MerchantStore store) {
        MerchantStoreDto merchantStoreDto = modelMapper.map(store, MerchantStoreDto.class);
        merchantStoreDto.setMerchantName(store.getMerchant().getName());
        return merchantStoreDto;
    }

    private Merchant convertToModel(MerchantDto merchantDto) throws ParseException {
        Merchant merchant = modelMapper.map(merchantDto, Merchant.class);
        return merchant;
    }

    private MerchantStore convertToModel(MerchantStoreDto merchantStoreDto) throws ParseException {
        Merchant merchant = null;
        if (merchantStoreDto.hasValidMerchantId()) {
            merchant = merchantsService.getMerchantById(merchantStoreDto.getMerchantId());
        }
        MerchantStore merchantStore = new MerchantStore
                .MerchantStoreBuilder(merchant, merchantStoreDto.getCountry(), merchantStoreDto.getAddress())
                .withId(merchantStoreDto.getId())
                .build();

        return merchantStore;
    }
}
