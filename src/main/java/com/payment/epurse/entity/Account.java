package com.payment.epurse.entity;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "account")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Account {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long accountId;

        private String accountNumber;
        private Long customerId;
        private Double balance;
        private Double debit;
        private Double credit;
        private String currency;
        private LocalDateTime date;
    }


