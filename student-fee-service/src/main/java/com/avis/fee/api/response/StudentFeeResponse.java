package com.avis.fee.api.response;

import com.avis.fee.api.request.StudentFeeTypeCollection;
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
public class StudentFeeResponse {

    private Long studentId;

    private Long receiptId;

    private Long paymentId;

    private String cardNumber;

    private String cardType;

    private String paymentType;

    private BigDecimal totalAmount;

    private String currency;

    private String studentName;

    private String schoolName;

    private String grade;

    private String paymentStatus;

    private StudentFeeTypeResponseCollection feeTypes;

}
