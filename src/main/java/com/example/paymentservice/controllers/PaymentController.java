package com.example.paymentservice.controllers;

import com.example.paymentservice.dtos.InitiatePaymentDto;
import com.example.paymentservice.services.PaymentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
public class PaymentController {
    PaymentService paymentService;

    PaymentController(PaymentService paymentService){
        this.paymentService = paymentService;
    }

    @PostMapping()
    public String initiatePayment(@RequestBody InitiatePaymentDto initiatePaymentDto){
        return paymentService.initiatePayment(initiatePaymentDto.getEmail(), initiatePaymentDto.getName(),
                initiatePaymentDto.getMobileNumber(), initiatePaymentDto.getOrderId(),
                initiatePaymentDto.getAmount());
    }

    @PostMapping("webhook")
    public String webhhookListner(@RequestBody String webhookEvent){
        System.out.println("webhook Listener event received: " + webhookEvent);
        return "OK";
    }

}
