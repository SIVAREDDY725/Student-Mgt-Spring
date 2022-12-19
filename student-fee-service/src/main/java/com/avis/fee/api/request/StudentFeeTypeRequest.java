package com.avis.fee.api.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentFeeTypeRequest {
    @NotBlank
    private String feeTypeName;

    private BigDecimal feeAmount;
}
