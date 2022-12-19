package com.avis.fee.model;

import javax.persistence.*;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@Entity
@EntityListeners(AuditingEntityListener.class)
public class StudentFee {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long receiptId;
	private Long paymentId;
	private Long studentId;
	private String paymentStatus;
	private BigDecimal totalAmount;
	private String currency;

	@OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinColumn(name = "receiptId")
	private List<StudentFeeTypes> feeTypes;

	@Column(name = "created_date", nullable = false, updatable = false)
	@CreatedDate
	private LocalDateTime createdDate;

	@Column(name = "modified_date")
	@LastModifiedDate
	private LocalDateTime modifiedDate;

}
