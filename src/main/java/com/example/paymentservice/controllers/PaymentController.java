package com.example.paymentservice.controllers;

import com.example.paymentservice.dtos.InitiatePaymentDto;
import com.example.paymentservice.services.PaymentService;
import com.example.paymentservice.services.ProductService;
import com.stripe.exception.StripeException;
import lombok.Getter;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
public class PaymentController {
    PaymentService paymentService;
    private ProductService productService;

    PaymentController(PaymentService paymentService, ProductService productService) {
        this.paymentService = paymentService;
        this.productService = productService;
    }

    @PostMapping()
    public String initiatePayment(@RequestBody InitiatePaymentDto initiatePaymentDto)  {
        return paymentService.initiatePayment(initiatePaymentDto.getEmail(), initiatePaymentDto.getName(),
                initiatePaymentDto.getMobileNumber(), initiatePaymentDto.getOrderId(),
                initiatePaymentDto.getAmount());
    }

    @PostMapping("webhook")
    public String webhhookListner(@RequestBody String webhookEvent){
        System.out.println("webhook Listener event received: " + webhookEvent);
        return "OK";
    }

    @GetMapping("/product/{id}")
    public String getProduct(@PathVariable Long id){
        return productService.getProduct(id);

    }

}
