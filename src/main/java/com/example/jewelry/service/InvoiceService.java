package com.example.jewelry.service;

import com.example.jewelry.exception.CustomDataException;
import com.example.jewelry.model.Invoice;
import com.example.jewelry.model.Response;
import com.example.jewelry.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvoiceService {
    @Autowired
    InvoiceRepository invoiceRepository;
    @Autowired
    StocksService stocksService;

    public Response saveInvoice(Invoice invoice) {
        if (invoiceRepository.existsById(invoice.getInvoiceNumber())) {
            return editInvoice(invoice);
        }
        invoiceRepository.save(invoice);
        saveStocksForInvoice(invoice);
        return new Response(invoice, "Invoice saved successfully");
    }

    public Response fetchInvoiceByNumber(String invoiceNumber) {
        Invoice invoice = invoiceRepository.findById(invoiceNumber).orElseThrow(() -> new CustomDataException("Invoice not found"));
        return new Response(invoice, "Invoice fetched successfully");
    }

    public Response getAllInvoices() {
        return new Response(invoiceRepository.findAll(), "Invoices fetched successfully");
    }

    public Response getInvoiceCount() {
        long count = invoiceRepository.count();
        return new Response(count, "Total invoice count fetched successfully");
    }

    public Response editInvoice(Invoice invoice) {
        if (!invoiceRepository.existsById(invoice.getInvoiceNumber())) {
            throw new CustomDataException("Invoice not found for update");
        }
        invoiceRepository.save(invoice);
        saveStocksForInvoice(invoice);
        return new Response(invoice, "Invoice updated successfully");
    }

    private void saveStocksForInvoice(Invoice invoice) {
        stocksService.saveStocks(invoice.getItems().stream().map(Invoice.Item::getDescription).toList());
    }
}
