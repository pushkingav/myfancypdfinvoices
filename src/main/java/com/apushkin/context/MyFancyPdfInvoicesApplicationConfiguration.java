package com.apushkin.context;

import com.apushkin.ApplicationLauncher;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackageClasses = ApplicationLauncher.class)
@PropertySource("classpath:/application.properties")
@PropertySource(value = "classpath:/application-${spring.profiles.active}.properties", ignoreResourceNotFound = true)
public class MyFancyPdfInvoicesApplicationConfiguration {
    @Bean
    public Faker faker() {
        return new Faker();
    }

    /*@Bean
    public UserService userService(Faker faker) {
        return new UserService(faker);
    }

    @Bean
    public InvoiceService invoiceService(UserService userService) {
        return new InvoiceService(userService);
    }*/

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
