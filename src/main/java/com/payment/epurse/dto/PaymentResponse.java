package com.payment.epurse.dto;

import com.payment.epurse.entity.PaymentStatus;
import java.math.BigDecimal;

public record PaymentResponse(
        String utr,
        PaymentStatus status,
        String message,
        BigDecimal customerDebited,
        BigDecimal merchantCredited,
        BigDecimal platformFee
) {}