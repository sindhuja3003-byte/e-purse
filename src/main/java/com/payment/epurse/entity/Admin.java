package com.payment.epurse.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "admin")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adminId;

    private Long accountId;
    private Double balance;
    private Double credit;
    private Double debit;
    private String utr;
    private LocalDateTime date;
    private Long merchantId;
    private String description;
}