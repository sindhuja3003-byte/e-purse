package com.payment.epurse.dto;

import lombok.*;

import java.math.BigDecimal;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentProcessRequest {
    private  Long customerId;
    private  Long merchantId;
    private BigDecimal amount;
    private String transactionType;
}
