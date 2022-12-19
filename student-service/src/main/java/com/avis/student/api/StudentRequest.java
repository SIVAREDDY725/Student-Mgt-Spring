package com.avis.student.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentRequest {

    @NotBlank
    private String studentName;

    @NotBlank
    private String mobileNumber;

    @NotBlank
    private String schoolName;

    @NotBlank
    private String grade;
}
