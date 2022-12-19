package com.avis.fee.fiegnclient;

import com.avis.fee.api.request.PaymentRequest;
import com.avis.fee.api.response.Payment;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class PaymentFeignClientFallbackFactory implements FallbackFactory<PaymentFeignClient> {

    @Override
    public PaymentFeignClient create(Throwable cause) {
        return new PaymentFeignClient() {
            @Override
            public Payment getPaymentDetails(Long paymentId) {
               throw new RuntimeException("Payment service is down, try after some time!");
            }

            @Override
            public Payment processPayment(PaymentRequest paymentRequest) {
                throw new RuntimeException("Payment service is down, try after some time!");
            }
        };
    }
}
