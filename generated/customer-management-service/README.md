# Customer Management Service

This is a minimal Spring Boot 3.5 project using Java 21.

## Features

- Spring Web
- Validation

## Build and Run

```bash
mvn clean package
java -jar target/customer-management-service-0.0.1-SNAPSHOT.jar
```

## API

- POST /customers : Creates a new customer record with validated customer details, generates a unique customer number, and returns the assigned customer number along with success or error codes.
