package com.payment.epurse.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(
        name = "platform_wallet",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_platform_wallet_currency",
                        columnNames = "currency"
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlatformWallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 3, nullable = false)
    private String currency;

    @Column(precision = 19, scale = 2, nullable = false)
    private BigDecimal balance;
}
