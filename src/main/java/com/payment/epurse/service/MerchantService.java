package com.payment.epurse.service;

import com.payment.epurse.entity.Merchant;
import com.payment.epurse.entity.Product;
import com.payment.epurse.repository.MerchantRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MerchantService {

    private final MerchantRepository merchantRepository;

    public MerchantService(MerchantRepository merchantRepository) {
        this.merchantRepository = merchantRepository;
    }

    public Merchant getMerchantById(int merchantId) {
        return merchantRepository.getById(merchantId);
    }

}
