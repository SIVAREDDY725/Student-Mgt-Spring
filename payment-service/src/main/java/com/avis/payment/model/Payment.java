package com.avis.payment.model;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Payment {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long paymentId;
	private String cardNumber;
	private String cardType;
	private String paymentType;
	private String paymentStatus;
	private BigDecimal totalAmount;
	private String currencyType;
	@Column(name = "created_date", nullable = false, updatable = false)
	@CreatedDate
	private LocalDateTime createdDate;

	@Column(name = "modified_date")
	@LastModifiedDate
	private LocalDateTime modifiedDate;
}
