package com.amigosecode.fraud.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "fraud_check")
public class FraudCheckHistory {
    @Id
    @SequenceGenerator(name = "fraud_sequence", sequenceName = "fraud_sequence")
    @GeneratedValue(generator = "fraud_sequence",
    strategy = GenerationType.SEQUENCE)
    private Long id;
    private Long customerId;
    private boolean isFraudster;
    private LocalDateTime createdAt;


}
