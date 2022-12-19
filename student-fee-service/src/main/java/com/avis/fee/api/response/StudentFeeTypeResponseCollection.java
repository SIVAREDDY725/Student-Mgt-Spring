package com.avis.fee.api.response;

import com.avis.fee.api.request.StudentFeeTypeRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentFeeTypeResponseCollection {

    private List<StudentFeeTypeResponse> feeTypes;

}
