package com.avis.fee.api.response;

import lombok.*;
import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student {

	private Long studentId;
	private String studentName;
	private String mobileNumber;
	private String schoolName;
	private String grade;
	private LocalDateTime createdDate;
	private LocalDateTime modifiedDate;

}
