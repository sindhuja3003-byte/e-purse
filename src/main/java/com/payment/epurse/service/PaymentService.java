package com.payment.epurse.service;

import com.payment.epurse.constants.Constants;
import com.payment.epurse.dto.PaymentRequest;
import com.payment.epurse.dto.PaymentResponse;
import com.payment.epurse.entity.*;
import com.payment.epurse.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentService {


    private final AccountRepository accountRepository;
//    private final MerchantRepository merchantRepository;
    private final PlatformWalletRepository platformWalletRepository;
    private final PaymentRepository paymentRepository;

    @Transactional
    public PaymentResponse processPayment(PaymentRequest request) {
        // Basic validations
        if (request.amount() == null || request.amount().compareTo(BigDecimal.ZERO) <= 0) {
            return failure("Amount must be positive");
        }
        if (request.currency() == null || request.currency().isBlank()) {
            return failure("Currency is required");
        }

        String currency = request.currency().toUpperCase();

        // Lock and fetch account and merchant for safe concurrent updates
        Account account = accountRepository.findAccountByCustomerId(request.customerId())
                .orElse(null);
        if (account == null || !account.getCustomerId().equals(request.customerId())) {
            return failure("Invalid account/customer mapping");
        }

//        Merchant merchant = merchantRepository.findByIdForUpdate(request.merchantId())
//                .orElse(null);
//        if (merchant == null) {
//            return failure("Merchant not found");
//        }

        // Currency check (account & merchant must match request currency)
        if (!currency.equalsIgnoreCase(account.getCurrency())) {
            return failure("Account currency mismatch");
        }
//        if (!currency.equalsIgnoreCase(merchant.getCurrency())) {
//            return failure("Merchant currency mismatch");
//        }

        // Compute fee (merchant pays fee)
        BigDecimal amount = request.amount().setScale(2, RoundingMode.HALF_UP);
        BigDecimal fee = amount.multiply(Constants.FEE_RATE).setScale(2, RoundingMode.HALF_UP);
        BigDecimal merchantCredit = amount.subtract(fee).setScale(2, RoundingMode.HALF_UP);

        // Check sufficient balance (customer pays only the amount)
        if (account.getBalance().compareTo(amount) < 0) {
            return failure("Insufficient wallet balance");
        }

        // Get or create platform wallet for this currency
        PlatformWallet platformWallet = platformWalletRepository.findByCurrency(currency)
                .orElseGet(() -> platformWalletRepository.save(
                        PlatformWallet.builder()
                                .currency(currency)
                                .balance(BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP))
                                .build()
                ));

        // Perform updates
        account.setBalance(account.getBalance().subtract(amount));
//        merchant.setWalletBalance(merchant.getWalletBalance().add(merchantCredit));
        platformWallet.setBalance(platformWallet.getBalance().add(fee));

        // Persist updates
        accountRepository.save(account);
//        merchantRepository.save(merchant);
        platformWalletRepository.save(platformWallet);

        // Create payment record
        String utr = generateUtr();

        Payment payment = Payment.builder()
                .customerId(request.customerId())
                .merchantId(request.merchantId())
                .productId(request.productId())
                .utr(utr)
                .debit(amount)              // total debited from customer
                .credit(merchantCredit)     // net credited to merchant
                .fee(fee)                   // fee taken by platform
                .currency(currency)
                .status(PaymentStatus.SUCCESS)
                .updatedDateTime(LocalDateTime.now())
                .build();

        paymentRepository.save(payment);

        //Response
        return new PaymentResponse(
                utr,
                PaymentStatus.SUCCESS,
                "Payment processed successfully",
                amount,
                merchantCredit,
                fee
        );
    }

    private String generateUtr() {
        return UUID.randomUUID().toString().replace("-", "").substring(0, 16).toUpperCase();
    }

    private PaymentResponse failure(String message) {
        return new PaymentResponse(null, PaymentStatus.FAILED, message, null, null, null);
    }
}
