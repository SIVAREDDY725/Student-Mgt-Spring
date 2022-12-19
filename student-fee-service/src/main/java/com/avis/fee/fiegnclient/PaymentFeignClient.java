package com.avis.fee.fiegnclient;

import com.avis.fee.api.response.Payment;
import com.avis.fee.api.request.PaymentRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(name = "PAYMENT-SERVICE", configuration = FeignConfiguration.class,
		fallbackFactory = PaymentFeignClientFallbackFactory.class)
public interface PaymentFeignClient {

	@GetMapping("/payment/{paymentId}")
	Payment getPaymentDetails(@PathVariable(value = "paymentId") Long paymentId);

	@PostMapping("/payment/process")
	Payment processPayment(@RequestBody PaymentRequest paymentRequest);

}
