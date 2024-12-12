package com.example.paymentservice.paymentGateways;

public interface PaymentGateway {
    String generatePaymentLink(String email, String name, String mobileNumber,
                               String orderId, Long amount);
}
