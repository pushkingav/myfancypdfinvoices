package com.apushkin.service;

import com.apushkin.model.Invoice;
import com.apushkin.model.User;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class InvoiceService {
    private final UserService userService;
    private final List<Invoice> invoices = new CopyOnWriteArrayList<>();
    private String cdnUrl;

    public InvoiceService(UserService userService, @Value("${cdn.url}") String cdnUrl) {
        this.userService = userService;
        this.cdnUrl = cdnUrl;
    }

    @PostConstruct
    public void init() {
        System.out.println("Fetching pdf template from S3...");
        //TODO - fetch a real template from S3
    }

    @PreDestroy
    public void destroy() {
        System.out.println("Deleting local pdf template...");
        //TODO - actual deletion of pdf template(s)
    }

    public List<Invoice> findAll() {
        return Collections.unmodifiableList(invoices);
    }

    public Invoice create(String userId, Integer amount) {
        User user = userService.findById(userId);
        if (user == null) {
            throw new IllegalStateException();
        }
        // Real invoice pdf creation and storing on cdn server
        Invoice invoice = new Invoice(userId, amount, cdnUrl + "/images/default/sample.pdf");
        invoices.add(invoice);
        return invoice;
    }
}
