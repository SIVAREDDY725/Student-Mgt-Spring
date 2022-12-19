package com.avis.fee.api.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentFeeTypeResponse {
    private Long feeTypeId;

    private String feeTypeName;

    private BigDecimal feeAmount;
}
