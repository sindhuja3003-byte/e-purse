package com.payment.epurse.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController("/epurse/wallet")
public class WalletController {

    @GetMapping("/getWalletDetails/{custId}")
    public ResponseEntity<?>  getWalletDetails(@PathVariable Long custId){
        return new ResponseEntity<>(HttpStatusCode.valueOf(HttpStatus.OK.value()));
    }

}
