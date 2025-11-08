# e-purse
E-purse services which handles transactions digitally

We wanted to maintain three microservice like Wallet service, merchant service and Payment service

Wallet service contains wallet details(get put), customer details(get), account details(get), trascation details(get)

Merchant service(cart details) conatins product details(get, put), trasaction details (get)

Payment service conatins transaction details (get) internally hitting notification service to send message to customer and merchant.

Data model:

(Wallet service)
Account table- account id(pri), account number, customer id, wallet balance, date, debit, credit,utr, currency

Customer details- customer id(pri), customer name, dob, mobile number, email, address, product id(optional)

(Merchant service)
Product table- Name, product id, description, price, currency

merchant account details- merchant id, customer id, mail, mobile, amount, date, utr, debit, credit, product id(list)

payment table- id, customer id, merchant id, created dateTime, updated dateTime, utr, debit, credit, status

API:
1.GetProductDetails- fetches details of the product
restEndpoint- GET/getProductById/{ProductId)

2.UpdateProductDetails- update the product details
restEndpoint- PUT/updateProductById/{ProductId)

3.GetWalletDetails- fetches details of the customer wallet info
restEndpoint- GET/getCustomerById/{CustId)

4.UpdateWalletDetails- update details of the customer wallet info
restEndpoint- PUT/updateCustomerById/{CustId)

5.PaymentAPI(post)- validate the request and send it to payment process
restEndpoint- POST/paymentProcess
requestBody- {AccountId, customerId, merchantId, productId}
responseBody- {status} 

6.notificationAPI(post)- sent notification to merchant and customer
restEndpoint- POST/notifyUser
requestBody-{customerId, MerchantId}

7.UpdateAccountDetails- update the account details
restEndpoint- PUT/updateAcountById/{AccountId)

Tech stack:
Springboot, rest api, microservices, maven, java 17, Postgres, postman, git hub, junit, sonar,
spring data JPA, circuit breaker(hystrix), kafka, docker, CI/CD, Kubernates, jmeter, indexing on DB
API gateway, oauth, scheduler


user story 1- Wallet services(GetWalletDetails, UpdateWalletDetails, UpdateCustomerDetails, UpadteAccountDetails)
with UT

user story 2- Merchant services(UpdateMerchantAccountDetails, GetProductDetails, UpdateProductDetails, 
DeteleProductDetails) with UT

user story 3- payment services(PaymentAPI(post), notificationAPI(post)) with UT

user story 4- Integration of services and testing 

Extra:
Rewards points for each transactions, also based on transactions(more than particular amount)
monthly spend categorization
Use scheduler service to settle amount at the end of day to each merchant


