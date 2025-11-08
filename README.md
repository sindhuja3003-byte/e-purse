# e-purse
E-purse services which handles transactions digitally

We wanted to maintain three microservice like Wallet service, merchant service and Payment service

Wallet service contains wallet details(get put), customer details(get), account details(get), trascation details(get)

Merchant service(cart details) conatins product details(get, put), trasaction details (get)

Payment service conatins trasaction details (get) internally hitting notification service to send message to customer and merchant.

Data model:

(Wallet service)
Account table- account id(pri), acctount number, customer id, account balance, date, debit, credit, currency

Customer details- customer id(pri), customer name, dob, mobile number, email, address, product id(optional)

Admin table- admin id, account id, account balance, credit, debit, date, merchant id, description

(Merchant service)
Product table- Name, product id, description, price, currency

merchant account details- merchant id, customer id, mail,mobile, account balance, date, debit, credit, product id(list)

API:
1.GetProductDetails- fetches details of the product
2.UpdateProductDetails- update the product details
3.DeteleProductDetails- delete product details
4.GetWalletDetails- fetches details of the customer wallet info
5.UpdateWalletDetails- update details of the customer wallet info
6.UpdateCustomerDetails- update the customer details
7.PaymentAPI(post)- validate the request and send it to payment process
8.notificationAPI(post)- sent notification to merchant and customer
9.UpadteAccountDetails- update the account details
10.UpdateMerchantAccountDetails- update merchant details

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


