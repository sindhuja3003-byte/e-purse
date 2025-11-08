package com.payment.epurse.dto;

import com.payment.epurse.entity.Account;
import com.payment.epurse.entity.Customer;
import jakarta.persistence.Id;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Wallet {

    private Account accountDetails;
    private Customer custDetails;
}
