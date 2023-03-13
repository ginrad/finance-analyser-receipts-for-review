package com.ginrad.finance.analyser.receipts.controller;

import com.ginrad.finance.analyser.receipts.model.Receipt;
import com.ginrad.finance.analyser.receipts.service.ReceiptsService;
import com.ginrad.finance.analyser.receipts.util.ImageAccessException;
import com.ginrad.finance.analyser.receipts.util.ImageProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

//Controller
@RestController
//@RequestMapping(path = "receipts", produces = "application/json")
@RequestMapping(path = "receipts")
public class ReceiptsController {

    @Value("${store.receipts.images:/}")
    private String receiptImagesFilestoreBase;

    @Value("${spring.servlet.multipart.max-file-size:1MB}")
    private String maxFileSize;

    @Autowired
    ReceiptsService receiptsService;

    @GetMapping(path = "")
    public List<Receipt> listAllReceipts() {
        return receiptsService.listAllReceipts();
        //return Arrays.asList(String.format("GET all receipts..."));
    }
    // TODO: Implement pagination on listReceiptMetadataForAll

    @GetMapping(path = "/{receiptId}")
    public Receipt getReceiptById(@PathVariable String receiptId) {
        return receiptsService.getReceiptById(receiptId);
        //return String.format("GET receipt: %s", receiptId);
    }

    @GetMapping(path = "/merchants/{merchantId}")
    public List<Receipt> listReceiptsByMerchant(@PathVariable String merchantId) {
        //throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED, "listReceiptsByMerchant");
        return Arrays.asList(
                new Receipt.ReceiptBuilder(
                        String.format("listReceiptsByMerchant: %s", merchantId))
                        .build());
    }
    // TODO: Make call to service once the appropriate service method has been implemented.
    // TODO: Implement pagination on listReceiptsByMerchant

    @GetMapping(path = "/dates")
    public List<Receipt> listReceiptsByDateParams(@RequestParam Map<String, String> allParams) {
        //throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED, "listReceiptsByDateParams");
        return Arrays.asList(
                new Receipt.ReceiptBuilder(
                        String.format("listReceiptsByDateParams: %s", allParams.entrySet()))
                        .build());
    }
    // TODO: Make call to service once the appropriate service method has been implemented.
    // TODO: Implement pagination on listReceiptsByDateParams

    @PostMapping(path = "")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> handleReceiptFileUpload(@RequestParam("file") MultipartFile file) {
        try {
            // Dereference to Spring's multipartfile inputStream to hide from the service
            //  implementation the method by which raw receipt data was obtained.
            Receipt receipt = receiptsService.handleReceiptFileUpload(
                    file.getInputStream(),
                    file.getOriginalFilename());

            if (receipt == null) {
                return ResponseEntity
                        .badRequest()
                        .body("Error performing POST request: no object generated");
            }

            return ResponseEntity
                    .created(URI.create(String.format("/%s", receipt.getId())))
                    .body(receipt.getFormat());

        } catch (IOException ioe) {
            ioe.printStackTrace();
            return ResponseEntity
                    .internalServerError()
                    .body(String.format("Error performing POST request: %s", ioe.getMessage()));
        } catch (ImageAccessException iae) {
            iae.printStackTrace();
            return ResponseEntity
                    .internalServerError()
                    .body(String.format("Error performing POST request: %s", iae.getMessage()));
        } catch (ImageProcessingException ipe) {
            ipe.printStackTrace();
            return ResponseEntity
                    .unprocessableEntity()
                    .body(String.format("Error performing POST request: %s", ipe.getMessage()));
        }
    }
}
