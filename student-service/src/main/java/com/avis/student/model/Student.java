package com.avis.student.model;

import javax.persistence.*;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long studentId;
	private String studentName;
	private String mobileNumber;
	private String schoolName;
	private String grade;

	@Column(name = "created_date", nullable = false, updatable = false)
	@CreatedDate
	private LocalDateTime createdDate;

	@Column(name = "modified_date")
	@LastModifiedDate
	private LocalDateTime modifiedDate;

}
