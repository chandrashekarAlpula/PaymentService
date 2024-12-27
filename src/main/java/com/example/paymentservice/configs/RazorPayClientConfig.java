package com.example.paymentservice.configs;

import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
public class RazorPayClientConfig {
    @Value("${razor.pay.key}")
    private String razorPayKey;
    @Value("${razor.pay.secret}")
    private String razorPaySecret;

    @Bean
    RazorpayClient CreateRazorpayClient() throws RazorpayException {
        return new RazorpayClient(razorPayKey, razorPaySecret);
    }
}
