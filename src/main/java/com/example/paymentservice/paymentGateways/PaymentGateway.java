package com.example.paymentservice.paymentGateways;

import com.stripe.exception.StripeException;

public interface PaymentGateway {
    String generatePaymentLink(String email, String name, String mobileNumber,
                               String orderId, Long amount) ;
}
