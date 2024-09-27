# Charter Retailer Reward Program

This project is a reward program for the Charter Retailer platform. It allows users to track their transactions and earn rewards based on their purchases. This has been created keeping MVC architechture in mind


## Technologies Used
- Spring Boot 3.1.2
- Spring Data JPA (Hibernate 6 is the default JPA implementation)
- H2 in-memory database
- Lombok - to reduce boilerplate code for the entities and services
- ModelMapper - To map the entities to the DTOs and vice versa
- Maven
- Java 17
- JUnit 5

## Project Structure

The project is organized as follows:

- [src/main/java/com/charter/retailer/reward]: Contains the main code for the reward program.
- [src/main/java/com/charter/retailer/reward/configuration]: Configuration for ModelMapper to be used in DTO layer
- [src/main/java/com/charter/retailer/reward/controller]: Contains the controllers for transaction and reward services
- [src/main/java/com/charter/retailer/reward/services]: Contains the service class implementations for transaction and reward services
- [src/main/java/com/charter/retailer/reward/dto]: Converts Entities to DTO and vice versa to add a layer of protection so that end users can not know the schema of the data being stored in the DB.
- [src/main/java/com/charter/retailer/reward/entities]: Contains the entity classes for storing DB reponse into POJOs
- [src/main/java/com/charter/retailer/reward/exception]: To handle exceptions and edge cases in a RESTful manner
- [src/main/java/com/charter/retailer/reward/repositories]: JPA repository initialization and usage 
- [src/main/java/com/charter/retailer/reward/util]: Contains the constants for error messages
- [src/main/java/com/charter/retailer/reward/controller]: Contains the controllers for transaction and reward services
- [src/test/java/com/charter/retailer/reward/services]: JUnit Tests for the service layer

## API Endpoints

The reward program exposes the following API endpoints:

- `GET  /reward/betweenDate`: Accepts a startDate and endDate in the url and returns the reward user is eligible for during that period. eg: reward/betweenDate?startDate=2024-05-10&endDate=2024-08-12
- `GET  /reward/forLast/{n}/{time}`: Gets reward for last n time. This time can be day, week, month, year
eg: /reward/forLast/10/month  or  /reward/forLast/17/week
- `GET  /reward/fromDate`: Accept a date in the url and provides the reward the user has earned from that day till today. eg: /reward/fromDate?startDate=2024-09-10
- `GET  /reward/totalRewards`: Gets reward for the total amount spend on all the transactions
- `GET  /transaction/id/{id}`: Fetches transaction by Id.
- `GET  /transaction/allTransactions`: Gets list of all the transactions from the DB
- `GET  /transaction/betweenDate`: Accepts a startDate and endDate in the url and returns all the transactions performed by user during that period. eg: transaction/betweenDate?startDate=2024-05-10&endDate=2024-08-12
- `GET  /reward/fromDate`: Accept a date in the url and returns all the transactions performed by the user from that day till today. eg: /transaction/fromDate?startDate=2024-09-10
- `POST /transaction/createTransaction`: Creates a single transaction.
- `POST /transaction/createAllTransactions`: Creates a list of transactions.

## API Payloads

- To load the data into DB, you first need to hit API endpoint :
**POST - /transaction/createAllTransactions**

payload: 
<pre>
[
    {
        "id": 1,
        "transactionName": "Apple",
        "transactionAmount": 20.0,
        "transactionDate": "2024-01-01"
    },
    {
        "id": 2,
        "transactionName": "Banana",
        "transactionAmount": 15.0,
        "transactionDate": "2024-02-02"
    },
    {
        "id": 3,
        "transactionName": "Orange",
        "transactionAmount": 10.0,
        "transactionDate": "2024-03-03"
    },
    {
        "id": 4,
        "transactionName": "Grape",
        "transactionAmount": 25.0,
        "transactionDate": "2024-04-04"
    },
    {
        "id": 5,
        "transactionName": "Mango",
        "transactionAmount": 30.0,
        "transactionDate": "2024-05-05"
    },
    {
        "id": 6,
        "transactionName": "Kiwi",
        "transactionAmount": 18.0,
        "transactionDate": "2024-06-06"
    },
    {
        "id": 7,
        "transactionName": "Pineapple",
        "transactionAmount": 22.0,
        "transactionDate": "2024-07-07"
    },
    {
        "id": 8,
        "transactionName": "Strawberry",
        "transactionAmount": 28.0,
        "transactionDate": "2024-08-08"
    },
    {
        "id": 9,
        "transactionName": "Watermelon",
        "transactionAmount": 12.0,
        "transactionDate": "2024-09-09"
    },
    {
        "id": 10,
        "transactionName": "Blueberry",
        "transactionAmount": 35.0,
        "transactionDate": "2024-10-10"
    },
    {
        "id": 11,
        "transactionName": "Peach",
        "transactionAmount": 32.0,
        "transactionDate": "2024-11-11"
    },
    {
        "id": 12,
        "transactionName": "Cherry",
        "transactionAmount": 19.0,
        "transactionDate": "2024-12-12"
    },
    {
        "id": 13,
        "transactionName": "Plum",
        "transactionAmount": 24.0,
        "transactionDate": "2024-05-23"
    },
    {
        "id": 14,
        "transactionName": "Pear",
        "transactionAmount": 21.0,
        "transactionDate": "2024-02-15"
    },
    {
        "id": 15,
        "transactionName": "Apricot",
        "transactionAmount": 17.0,
        "transactionDate": "2024-07-18"
    },
    {
        "id": 16,
        "transactionName": "Avocado",
        "transactionAmount": 29.0,
        "transactionDate": "2023-04-04"
    },
    {
        "id": 17,
        "transactionName": "Raspberry",
        "transactionAmount": 33.0,
        "transactionDate": "2023-05-05"
    },
    {
        "id": 18,
        "transactionName": "Blackberry",
        "transactionAmount": 26.0,
        "transactionDate": "2023-06-06"
    },
    {
        "id": 19,
        "transactionName": "Cranberry",
        "transactionAmount": 23.0,
        "transactionDate": "2023-07-07"
    },
    {
        "id": 20,
        "transactionName": "Lemon",
        "transactionAmount": 16.0,
        "transactionDate": "2023-08-08"
    }
]
</pre>

- To add a single transaction to the DB, use :
**POST - /transaction/createTransaction**
payload
<pre>
    {
        "id": 21,
        "transactionName": "Papaya",
        "transactionAmount": 66.0,
        "transactionDate": "2024-09-15"
    }
</pre>

