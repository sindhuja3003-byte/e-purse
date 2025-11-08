package com.payment.epurse.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("/epurse/wallet")
public class WalletController {

    @GetMapping("/getWalletDetails/{custId}")
    public ResponseEntity<?>  getWalletDetails(@PathVariable Long custId){
        return new ResponseEntity<>(HttpStatusCode.valueOf(HttpStatus.OK.value()));
    }

    @PutMapping("/updateWalletDetails/{custId}")
    public ResponseEntity<?>  updateWalletDetails(@RequestBody String custBody ,
                                                  @PathVariable Long custId){
        return new ResponseEntity<>(HttpStatusCode.valueOf(HttpStatus.OK.value()));
    }
}
