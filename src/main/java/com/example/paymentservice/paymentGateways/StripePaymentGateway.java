package com.example.paymentservice.paymentGateways;

import com.stripe.Stripe;
import com.stripe.StripeClient;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentLink;
import com.stripe.param.PaymentLinkCreateParams;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class StripePaymentGateway implements PaymentGateway {

    private StripeClient stripeClient;
    public StripePaymentGateway(StripeClient stripeClient) {
        this.stripeClient = stripeClient;
    }
    @Override
    public String generatePaymentLink(String email, String name,
                                      String mobileNumber,
                                      String orderId, Long amount) {

        PaymentLink paymentLink = null;

        PaymentLinkCreateParams params =
                PaymentLinkCreateParams.builder()
                        .addLineItem(
                                PaymentLinkCreateParams.LineItem.builder()
                                        .setPrice(String.valueOf(amount))
                                        .setQuantity(1L)
                                        .build()
                        )
                        .build();
        try {
            paymentLink = stripeClient.paymentLinks().create(params);
        } catch (StripeException e) {
            throw new RuntimeException(e);
        }

        return paymentLink.getUrl();
    }
}
