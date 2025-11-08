package com.payment.epurse.service;

import com.payment.epurse.dto.NotificationRequest;
import com.payment.epurse.dto.PaymentProcessRequest;
import com.payment.epurse.dto.PaymentProcessResponse;
import com.payment.epurse.dto.Wallet;
import com.payment.epurse.entity.Account;
import com.payment.epurse.entity.Customer;
import com.payment.epurse.repository.AccountRepository;
import com.payment.epurse.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class WalletService {
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    private RestTemplate restTemplate;


    private static final String PAYMENT_API_URL = "http://external-payment-api/api/v1/transactions";


    public Wallet getWalletDetails(Long custId) {
        Wallet wallet = Wallet.builder().build();
        try {
            Optional<Account> account = accountRepository.findAccountByCustomerId(custId);
            Optional<Customer> customer = customerRepository.findById(custId);
            wallet.setCustDetails(customer.get());
            wallet.setAccountDetails(account.get());
        }catch (Exception ex){
            //custom exception
        }
        return wallet;
    }

    public Wallet updateWalletDetails(Wallet wallet) {
        try {
            Optional<Account> account = accountRepository.findAccountByCustomerId(wallet.getAccountDetails().getAccountId());
            Optional<Customer> customer = customerRepository.findById(wallet.getAccountDetails().getAccountId());
            account.get().setBalance(wallet.getAccountDetails().getBalance());
            wallet.setCustDetails(customer.get());
            wallet.setAccountDetails(account.get());
            accountRepository.save(account.get());
            customerRepository.save(customer.get());
        }catch (Exception ex){
            //custom exception
        }
        return wallet;
    }


    /*
     * This method will save the  bank account details of customer who is using wallet
     * */
    public Account saveAccount(Account account) {

        return Account.builder().build();
    }

    /*
     * This method will update the bank account details of wallet customer iff there are any
     * changes to account info
     * */
    @Transactional
    public Account updateAccountDetails(Account account) throws Exception {

        Account updatedAccount;
        try {
            Optional<Account> existingAccount =
                    accountRepository.findById(account.getAccountId());

            existingAccount.get().setCustomerId(account.getCustomerId());
            existingAccount.get().setBalance(account.getBalance());
            existingAccount.get().setDate(LocalDateTime.now());
            existingAccount.get().setBalance(account.getBalance());
            updatedAccount = accountRepository.save(existingAccount.get());
            return updatedAccount;
            /*
             * Catch all database related exception distinctly and  need to be handled using custom exception using @ControllerAdvice annotation
             * */
        } catch (Exception ex) {
            throw new Exception();
        }
    }

    /*
     * This method will save the details of customer who is using wallet
     * */
    public Customer saveCustomer(Customer customer) {

        return customer.builder().build();
    }

    /*
     * This method will update the details of wallet customer iff there are any
     * changes to account info
     * */
    @Transactional
    public Customer updateCustomer(Customer customer) throws Exception {
        Customer updatedCustomer;
        try {
            Optional<Customer> existingCustomer =
                    customerRepository.findById(customer.getCustomerId());

            existingCustomer.get().setCustomerId(customer.getCustomerId());
            existingCustomer.get().setEmail("nitym@gmail.com");
            updatedCustomer = customerRepository.save(existingCustomer.get());
            return updatedCustomer;
            /*
             * Catch all database related exception distinctly and  need to be handled using custom exception using @ControllerAdvice annotation
             * */
        } catch (Exception ex) {
            throw new Exception();
        }
    }

    public PaymentProcessResponse initiateTransaction(PaymentProcessRequest request) {

        Optional<Account> customerAccount = accountRepository.findAccountByCustomerId(request.getCustomerId());
        if (customerAccount.filter(account -> account.getBalance() >= request.getAmount()).isPresent()) {
            /*
             * Initiate the transaction for debiting the amount from wallet using TransactionType as DEBIT
             *
             * */
            PaymentProcessResponse response = processTransaction(request , "DEBIT");
            if(response.getStatus() == "SUCCESS") {
                processTransaction(request, "CREDIT");
                if(response.getStatus() == "SUCCESS") {
                    /*
                    * Notify the customer via mobile and email
                    * */
                    sendNotification(NotificationRequest.builder().build());
                }

            }

        } else {

            /*
            *  call to update wallet details to add the balance
            * */
        }
        /*
         *
         * */
        return null;
    }

    public PaymentProcessResponse processTransaction(PaymentProcessRequest request, String transactionType) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        request.setTransactionType(transactionType);
        headers.set("Authorization", "ksjhdkfbqehf2t46t668755775879vhrhyrhnrfyhrufhrfgrfgbeh756493erhhugfhuqbnasbkvqgydghasckm.sn8686jjfrhjhnkjjilweyugqr-6grv56g");
        HttpEntity<PaymentProcessRequest> requestEntity = new HttpEntity<>(request, headers);
        try {
            ResponseEntity<PaymentProcessResponse> responseEntity = restTemplate.postForEntity(
                    PAYMENT_API_URL,
                    requestEntity,
                    PaymentProcessResponse.class
            );
            if (responseEntity.getStatusCode().is2xxSuccessful() && responseEntity.getBody() != null) {
                return responseEntity.getBody();
            } else {
                throw new RuntimeException("Payment API returned status: " + responseEntity.getStatusCode());
            }

        } catch (RestClientException e) {
            // Handle the custom exception regarding payment failure
        }
        return null;
    }

    /*
    * Notify the customer and merchant with for credit and debit related transactions
    * */
    public void sendNotification(NotificationRequest notificationRequest) {

    }
}
