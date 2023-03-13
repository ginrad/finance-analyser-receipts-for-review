package com.ginrad.finance.analyser.receipts.service;

import com.ginrad.finance.analyser.receipts.model.Receipt;
import com.ginrad.finance.analyser.receipts.util.ImageAccessException;
import com.ginrad.finance.analyser.receipts.util.ImageProcessingException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

@Service
public interface ReceiptsService {

    public List<Receipt> listAllReceipts();
    public Receipt getReceiptById(String receiptId);
    public List<Receipt> listReceiptsByMerchant(String merchantId);
    public List<Receipt> listReceiptsByDateParams(Map<String, String> params);
    public Receipt handleReceiptFileUpload(InputStream stream, String originalImageReference)
            throws ImageAccessException, ImageProcessingException;
}
