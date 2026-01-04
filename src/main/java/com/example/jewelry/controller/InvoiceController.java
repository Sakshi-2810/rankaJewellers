package com.example.jewelry.controller;

import com.example.jewelry.model.Invoice;
import com.example.jewelry.model.Response;
import com.example.jewelry.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InvoiceController {
    @Autowired
    InvoiceService invoiceService;

    @GetMapping("/invoices/count")
    public ResponseEntity<Response> getInvoiceCount() {
        return ResponseEntity.ok(invoiceService.getInvoiceCount());
    }

    @GetMapping("/invoices")
    public ResponseEntity<Response> getAllInvoices() {
        return ResponseEntity.ok(invoiceService.getAllInvoices());
    }

    @GetMapping("/invoice/{invoiceNumber}")
    public ResponseEntity<Response> getInvoiceByNumber(@PathVariable String invoiceNumber) {
        return ResponseEntity.ok(invoiceService.fetchInvoiceByNumber(invoiceNumber));
    }

    @PostMapping("/invoice")
    public ResponseEntity<Response> saveInvoice(@RequestBody Invoice invoice) {
        return ResponseEntity.ok(invoiceService.saveInvoice(invoice));
    }

}
