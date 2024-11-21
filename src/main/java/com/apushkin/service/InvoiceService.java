package com.apushkin.service;

import com.apushkin.model.Invoice;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class InvoiceService {
    private final List<Invoice> invoices = new CopyOnWriteArrayList<>();

    public List<Invoice> findAll() {
        return Collections.unmodifiableList(invoices);
    }

    public Invoice create(String userId, Integer amount) {
        // Real invoice pdf creation and storing on cdn server
        Invoice invoice = new Invoice(userId, amount, "http://www.africau.edu/images/default/sample.pdf");
        invoices.add(invoice);
        return invoice;
    }
}
