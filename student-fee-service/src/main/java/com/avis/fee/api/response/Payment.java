package com.avis.fee.api.response;

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
public class Payment {

	private Long paymentId;
	private String cardNumber;
	private String cardType;
	private String paymentType;
	private String paymentStatus;
	private BigDecimal totalAmount;
	private String currencyType;
	private LocalDateTime createdDate;
	private LocalDateTime modifiedDate;
}
