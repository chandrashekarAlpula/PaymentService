package com.example.paymentservice.paymentGateways;

import com.razorpay.*;
import org.json.JSONObject;
import com.razorpay.RazorpayClient;
import org.springframework.stereotype.Component;

@Component
public class RazorPaymentGateway implements PaymentGateway {
    RazorpayClient razorpayClient;
    public RazorPaymentGateway(RazorpayClient razorpayClient) {
        this.razorpayClient = razorpayClient;
    }

    @Override
    public String generatePaymentLink(String email, String name, String mobileNumber,
                                      String orderId, Long amount) {

        //this gets create in config
        //RazorpayClient razorpay = new RazorpayClient("[YOUR_KEY_ID]", "[YOUR_KEY_SECRET]");
        JSONObject paymentLinkRequest = new JSONObject();
        paymentLinkRequest.put("amount",amount);
        paymentLinkRequest.put("currency","INR");
        paymentLinkRequest.put("accept_partial",true);
        paymentLinkRequest.put("first_min_partial_amount",1000);
        paymentLinkRequest.put("expire_by",1734097200);
        paymentLinkRequest.put("reference_id",orderId);
        paymentLinkRequest.put("description","Payment for oreder" +orderId);
        JSONObject customer = new JSONObject();
        customer.put("name",name);
        customer.put("contact",mobileNumber);
        customer.put("email",email);
        paymentLinkRequest.put("customer",customer);
        JSONObject notify = new JSONObject();
        notify.put("sms",true);
        notify.put("email",true);
        paymentLinkRequest.put("notify",notify);
        paymentLinkRequest.put("reminder_enable",false);
        JSONObject notes = new JSONObject();
        notes.put("Notes","Payment for Amazon");
        paymentLinkRequest.put("notes",notes);
        paymentLinkRequest.put("callback_url","https://www.google.com");
        paymentLinkRequest.put("callback_method","get");

        PaymentLink paymentLink = null;
        try {
            paymentLink = razorpayClient.paymentLink.create(paymentLinkRequest);
            return paymentLink.get("short_url").toString();
        } catch (RazorpayException e) {
            // Handle Exception
           throw new RuntimeException(e);
        }

    }
}
