package com.avis.fee.api.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentFeeTypeCollection {

    private List<StudentFeeTypeRequest> feeTypes;
}
