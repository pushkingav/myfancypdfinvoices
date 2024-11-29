package com.apushkin.context;

import com.apushkin.service.InvoiceService;
import com.apushkin.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyFancyPdfInvoicesApplicationConfiguration {
    @Bean
    public Faker faker() {
        return new Faker();
    }

    @Bean
    public UserService userService(Faker faker) {
        return new UserService(faker);
    }

    @Bean
    public InvoiceService invoiceService(UserService userService) {
        return new InvoiceService(userService);
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
