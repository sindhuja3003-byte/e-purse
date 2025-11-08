package com.payment.epurse.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "merchant")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Merchant {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int merchantId;
    private String merchantName;
    private String mail;
    private int mobileNumber;
    private double accountBalence;
    private Date date;
    private double credit;
    private double debit;
    private Product productId;
}
