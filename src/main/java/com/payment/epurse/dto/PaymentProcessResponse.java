package com.payment.epurse.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class PaymentProcessResponse {
    private  String status;
    private String transactionType;
    private  Long customerId;
    private  Long merchantId;
    private  int  amount;
    private  int utrNo;
}
