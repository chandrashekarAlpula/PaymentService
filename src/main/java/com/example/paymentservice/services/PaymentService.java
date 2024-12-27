package com.example.paymentservice.services;

import com.example.paymentservice.paymentGateways.PaymentGateway;
import com.stripe.exception.StripeException;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private PaymentGateway paymentGateway;
    public PaymentService(PaymentGateway paymentGateway) {
        this.paymentGateway = paymentGateway;
    }
    public String initiatePayment(String email, String name, String mobileNumber,
                                String orderId, Long amount)  {

        /*  Ideal flow
            Call OrderService
            Get the amount
            Validate te amount
            Call payment gateway
            get thhe payment link
        */
        return paymentGateway.generatePaymentLink(email, name, mobileNumber, orderId, amount);

    }

}
