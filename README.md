# Student-Fee-collection microservices

The services in this project are designed with microservice architecture.

Each microservice exposes REST API interfaces that can be accessed through OpenAPI endpoint.

http://localhost:8080/student/swagger-ui.html

http://localhost:8082/feePayment/swagger-ui.html

http://localhost:8081/payment/swagger-ui.html

## Pre-Requisites
OpenJdk-17

## Usage
 1. Start `eureka-service`. Default port is 8761.
 2. Start `gateway-service`
 3. Start all microservices: `payment-service`, `student-fee-service`, `student-service`
 4. Add some test data to `student-service`
 5. Send Fee payment request to `student-fee-service` for testing the flow.
 6. To test services use postman collection file.
