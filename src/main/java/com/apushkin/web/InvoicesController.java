package com.apushkin.web;

import com.apushkin.dto.InvoiceDto;
import com.apushkin.model.Invoice;
import com.apushkin.service.InvoiceService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Validated
public class InvoicesController {
    private final InvoiceService invoiceService;

    public InvoicesController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping("/invoices")
//    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public List<Invoice> invoices() {
        return invoiceService.findAll();
    }

    @PostMapping("/invoices")
    public Invoice createInvoice(@RequestParam("user_id") String userId, @RequestParam Integer amount) {
        return invoiceService.create(userId, amount);
    }

    @PostMapping("/invoices/{userId}/{amount}")
    public Invoice createInvoice2(@PathVariable @NotBlank String userId, @PathVariable @Min(10) @Max(50) Integer amount) {
        return invoiceService.create(userId, amount);
    }

    @PostMapping("/invoices3")
    public Invoice createInvoice3(@RequestBody @Valid InvoiceDto invoiceDto) {
        return invoiceService.create(invoiceDto.getUserId(), invoiceDto.getAmount());
    }
}
