package com.avis.fee.api.request;

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
public class StudentFeeRequest {

    @NotNull
    private Long studentId;

    @NotBlank
    private String cardNumber;

    @NotBlank
    private String cardType;

    @NotBlank
    private String paymentType;

    @NotNull
    private BigDecimal totalAmount;

    @NotNull
    private String currency;

    private StudentFeeTypeCollection feeTypes;

}
