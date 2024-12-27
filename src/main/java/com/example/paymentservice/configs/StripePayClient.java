package com.example.paymentservice.configs;

import com.stripe.StripeClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StripePayClient {
    @Value("${stripe.pay.key}")
    private String stripePayKey;
    @Value("${stripe.pay.secret}")
    private String stripePaySecret;

    @Bean
    public StripeClient createStripeClient() {
        return  new StripeClient(stripePaySecret);
    }
}
