# Loan Application

This Loan Request Service is used to store and retrieve loan request and who can apply for
one or more loans request for ING Customer.

Create a loan request. The service won’t receive many of these requests.
Calculate how much money a given customer identified by customer id has applied for, i.e. if a customer has applied for
three loans with amounts 1000, 2000 and 3000.25 then this operation should return 6000.25.
The service will receive many of these requests.

## About the Web Service

Loan request service Using Spring Boot with hibernate and H2 Database.

## H2 Database link

http://localhost:8080/h2-console/login.jsp

## To view Swagger 3 API docs

Run the server and browse to - http://localhost:8082/swagger-ui/index.html
Doc - http://localhost:8080/customer-loan-info

## Get information about service health check

http://localhost:8080/actuator/health

## Save Customer Loan Request

http://localhost:8080/loan/createLoan
{
"customerId": 1,
"customerFullName": "Customer Name",
"loanAmount": 11000.50
}

## Get Loan detail by CustomerId

http://localhost:8080/loan/1

