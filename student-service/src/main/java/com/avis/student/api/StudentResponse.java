package com.avis.student.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentResponse {

    private String studentId;

    private String studentName;

    private String mobileNumber;

    private String schoolName;

    private String grade;

}
