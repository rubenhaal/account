Account service

Launch as usual spring boot application: Using AccountApplication.java

Data.sql contains three users registered in the h2 database, with ids:
10001,10002, 10003


` insert into customer values(10001, 'Name1', 'Surname1');`
`insert into customer values(10002, 'Name2', 'Surname2');`
`insert into customer values(10003, 'name3', 'Jam');`

The OpenApi url for local environment is:
http://localhost:8080/swagger-ui/index.html

In Swagger all endpoint should be available:

- Customer Controller:

Create customer: POST : `/customer/create`
`CustomerDto{
id	[...]
name*	[...]
surname*	[...]
}`

Find customer by id: GET : `/customer/{customerId}`

- Account Controller: 

Create account: POST: `/accounts/createaccount` `AccountDto{
id	[...]
userId*	[...]
credit	[...]
}`

Find Account : GET : 
`/accounts/{accountId}`

- Transaction controller : POST : `/transactions/createTransaction` `TransactionDto{
  amount*	[...]
  accountId*	[...]
  }`