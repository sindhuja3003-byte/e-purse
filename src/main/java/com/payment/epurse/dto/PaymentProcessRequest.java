package com.payment.epurse.dto;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentProcessRequest {
    private  Long customerId;
    private  Long merchantId;
    private  int  amount;
    private String transactionType;
}
