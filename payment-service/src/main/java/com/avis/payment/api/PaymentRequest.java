package com.avis.payment.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentRequest {

    @NotBlank
    private String cardNumber;

    @NotBlank
    private String cardType;

    @NotBlank
    private String paymentType;

    @NotNull
    private BigDecimal totalAmount;

    @NotBlank
    private String currencyType;
}
