package com.example.jewelry.service;

import com.example.jewelry.exception.CustomDataException;
import com.example.jewelry.model.Invoice;
import com.example.jewelry.model.Response;
import com.example.jewelry.repository.InvoiceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class InvoiceService {
    @Autowired
    InvoiceRepository invoiceRepository;
    @Autowired
    StocksService stocksService;

    public Response saveInvoice(Invoice invoice) {
        if (invoiceRepository.existsById(invoice.getInvoiceNumber())) {
            log.info("Invoice already exists, redirecting to edit");
            return editInvoice(invoice);
        }
        log.info("Saving new invoice with number: {}", invoice.getInvoiceNumber());
        invoiceRepository.save(invoice);
        log.info("Invoice saved successfully, updating stocks");
        saveStocksForInvoice(invoice);
        return new Response(invoice, "Invoice saved successfully");
    }

    public Response fetchInvoiceByNumber(String invoiceNumber) {
        Invoice invoice = invoiceRepository.findById(invoiceNumber).orElseThrow(() -> new CustomDataException("Invoice not found"));
        log.info("Fetched invoice with number: {}", invoiceNumber);
        return new Response(invoice, "Invoice fetched successfully");
    }

    public Response getAllInvoices() {
        return new Response(invoiceRepository.findAll(), "Invoices fetched successfully");
    }

    public Response getInvoiceCount() {
        try {
            String count = invoiceRepository.findTopByOrderByInvoiceNumberDesc().getInvoiceNumber().split("/")[2];
            log.info("Total invoice count: {}", count);
            return new Response(Integer.parseInt(count), "Total invoice count fetched successfully");
        } catch (Exception e) {
            return new Response(0, "Total invoice count fetched successfully");
        }
    }

    public Response editInvoice(Invoice invoice) {
        if (!invoiceRepository.existsById(invoice.getInvoiceNumber())) {
            log.info("Invoice not found for update: {}", invoice.getInvoiceNumber());
            throw new CustomDataException("Invoice not found for update");
        }
        invoiceRepository.save(invoice);
        saveStocksForInvoice(invoice);
        log.info("Invoice updated successfully: {}", invoice.getInvoiceNumber());
        return new Response(invoice, "Invoice updated successfully");
    }

    private void saveStocksForInvoice(Invoice invoice) {
        log.info("Updating stocks for invoice: {}, items: {}", invoice.getInvoiceNumber(), invoice.getItems());
        stocksService.saveStocks(invoice.getItems().stream().map(Invoice.Item::getDescription).toList(), invoice.getItems().stream().map(Invoice.Item::getHsn).toList());
    }

    public void deleteInvoice(String invoiceNumber) {
        if (invoiceRepository.existsById(invoiceNumber)) {
            invoiceRepository.deleteById(invoiceNumber);
            log.info("Deleted invoice with id : {}", invoiceNumber);
        } else {
            log.info("Invoice : {} do not exist", invoiceNumber);
        }
    }
}
