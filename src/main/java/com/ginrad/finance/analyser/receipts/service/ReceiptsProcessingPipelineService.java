package com.ginrad.finance.analyser.receipts.service;

import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
public interface ReceiptsProcessingPipelineService {

    public void handleNewReceipt(InputStream stream);


    public String storeFromMultipartUpload(InputStream stream);
    public String convertToText();
}
