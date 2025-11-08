package com.payment.epurse.repository;

import com.payment.epurse.entity.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*author - aniket das*/
@Repository
public interface MerchantRepository extends JpaRepository<Merchant,Integer> {
}
