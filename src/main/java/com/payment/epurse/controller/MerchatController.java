package com.payment.epurse.controller;


import com.payment.epurse.repository.MerchantRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController("/merchant")
public class MerchatController {


    private final MerchantRepository merchantRepository;


    public MerchatController(MerchantRepository merchantRepository) {
        this.merchantRepository = merchantRepository;
    }
}
