package com.payment.epurse.controller;

import com.payment.epurse.dto.PaymentProcessRequest;
import com.payment.epurse.dto.PaymentProcessResponse;
import com.payment.epurse.dto.Wallet;
import com.payment.epurse.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("/epurse/wallet")
public class WalletController {

    @Autowired
    WalletService walletService;

    @GetMapping("/getWalletDetails/{custId}")
    public ResponseEntity<Wallet>  getWalletDetails(@PathVariable Long custId){
        Wallet wallet =  walletService.getWalletDetails(custId);
        return new ResponseEntity<>(wallet, HttpStatus.OK);
    }

    @PostMapping("/createWalletDetails")
    public ResponseEntity<?>  createWallet(@RequestBody Wallet wallet){

        return new ResponseEntity<>(HttpStatusCode.valueOf(HttpStatus.OK.value()));
    }

    @PutMapping("/updateWalletDetails/{custId}")
    public ResponseEntity<?>  updateWalletDetails(@RequestBody String custBody ,
                                                  @PathVariable Long custId){
        return new ResponseEntity<>(HttpStatusCode.valueOf(HttpStatus.OK.value()));
    }

    @PostMapping
    public ResponseEntity<PaymentProcessResponse> initiateTransaction(@RequestBody PaymentProcessRequest paymentProcessRequest){
        PaymentProcessResponse paymentProcessResponse = walletService.initiateTransaction(paymentProcessRequest);
        return new ResponseEntity<>(paymentProcessResponse, HttpStatus.OK);
    }
}
