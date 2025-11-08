package com.payment.epurse.entity;

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
    @NonNull
    private String accountNumber;
    private String utr;
    private Long customerId;
    private Double balance;
    private Double debit;
    private Double credit;
    private String currency;
    private LocalDateTime date;
}
