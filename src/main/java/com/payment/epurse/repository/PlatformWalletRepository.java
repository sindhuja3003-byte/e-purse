package com.payment.epurse.repository;

import com.payment.epurse.entity.PlatformWallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlatformWalletRepository extends JpaRepository<PlatformWallet, Long> {
    Optional<PlatformWallet> findByCurrency(String currency);
}
