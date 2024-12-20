package com.apushkin.web;

import com.apushkin.context.MyFancyPdfInvoicesApplicationConfiguration;
import com.apushkin.model.Invoice;
import com.apushkin.service.InvoiceService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.util.List;

public class MyFancyPdfInvoicesServlet extends HttpServlet {
    private InvoiceService invoiceService;
    private ObjectMapper objectMapper;

    @Override
    public void init() {
        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext(MyFancyPdfInvoicesApplicationConfiguration.class);
        ctx.registerShutdownHook();
        this.invoiceService = ctx.getBean(InvoiceService.class);
        this.objectMapper = ctx.getBean(ObjectMapper.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (req.getRequestURI().equalsIgnoreCase("/")) {
            resp.setContentType("text/html");
            resp.getWriter().print("""
                    <html>
                        <body>
                            <h1>Hello World</h1>
                            <p>This is my very first, embedded Tomcat, HTML Page!</p>
                        </body>
                    </html>""");
        } else if (req.getRequestURI().equalsIgnoreCase("/invoices")) {
            resp.setContentType("application/json; charset=UTF-8");
            List<Invoice> invoices = invoiceService.findAll();
            objectMapper.writeValue(resp.getWriter(), invoices);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (req.getRequestURI().equalsIgnoreCase("/invoices")) {
            String userId = req.getParameter("user_id");
            Integer amount = Integer.valueOf(req.getParameter("amount"));

            Invoice invoice = invoiceService.create(userId, amount);

            resp.setContentType("application/json; charset=UTF-8");
            String json = objectMapper.writeValueAsString(invoice);
            resp.getWriter().print(json);

        } else {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
