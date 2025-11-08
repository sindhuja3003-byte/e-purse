package com.payment.epurse.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(
        name = "payment",
        indexes = {
                @Index(name = "idx_payment_utr", columnList = "utr"),
                @Index(name = "idx_payment_customer", columnList = "customerId"),
                @Index(name = "idx_payment_merchant", columnList = "merchantId")
        }
)
@Data                   // <-- Lombok: getters, setters, toString, equals, hashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long customerId;

    private Long merchantId;

    private Long productId;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdDateTime;

    private LocalDateTime updatedDateTime;

    @Column(nullable = false, unique = true, length = 50)
    private String utr;

    /** Debited from customer */
    @Column(precision = 19, scale = 2)
    private BigDecimal debit;

    /** Credited to merchant after fee */
    @Column(precision = 19, scale = 2)
    private BigDecimal credit;

    /** 2% fee deducted from merchant */
    @Column(precision = 19, scale = 2)
    private BigDecimal fee;

    @Column(length = 3)
    private String currency;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private PaymentStatus status;

    @PrePersist
    public void onCreate() {
        this.createdDateTime = LocalDateTime.now();
        this.updatedDateTime = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        this.updatedDateTime = LocalDateTime.now();
    }
}
