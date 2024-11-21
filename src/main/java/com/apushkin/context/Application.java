package com.apushkin.context;

import com.apushkin.service.InvoiceService;
import com.apushkin.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;

public class Application {
    public static final UserService userService = new UserService();
    public static final InvoiceService invoiceService = new InvoiceService(userService);
    public static final ObjectMapper objectMapper = new ObjectMapper();

    public static final Faker faker = new Faker();
}
