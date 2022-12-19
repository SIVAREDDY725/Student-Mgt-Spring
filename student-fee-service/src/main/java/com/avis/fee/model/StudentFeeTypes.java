package com.avis.fee.model;

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
public class StudentFeeTypes {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long feeTypeId;
    private String feeTypeName;
    private BigDecimal feeAmount;
    private String currency;

    @Column(name = "created_date", nullable = false, updatable = false)
    @CreatedDate
    private LocalDateTime createdDate;

    @Column(name = "modified_date")
    @LastModifiedDate
    private LocalDateTime modifiedDate;
}
