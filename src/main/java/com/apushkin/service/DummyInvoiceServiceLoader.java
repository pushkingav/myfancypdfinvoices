package com.apushkin.service;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("dev")
public class DummyInvoiceServiceLoader {
    private InvoiceService invoiceService;

    public DummyInvoiceServiceLoader(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @PostConstruct
    public void init() {
        System.out.println("Generating dummy dev invoices...");
        invoiceService.create("someUser01", 35);
        invoiceService.create("someUser02", 20);
    }
}
