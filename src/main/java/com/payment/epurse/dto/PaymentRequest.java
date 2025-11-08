package com.payment.epurse.dto;

import java.math.BigDecimal;

public record PaymentRequest(
        Long accountId,
        Long customerId,
        Long merchantId,
        Long productId,
        BigDecimal amount,
        String currency
) {}
