# Charter Retailer Reward Program

This project is a reward program for the Charter Retailer platform. It allows users to track their transactions and earn rewards based on their purchases.

## Project Structure

The project is organized as follows:

- [src/main/java/com/charter/retailer/reward]: Contains the main code for the reward program.
- [src/main/resources]: Contains configuration files.


## API Endpoints

The reward program exposes the following API endpoints:

- `POST /transaction/createTransaction`: Creates a single transaction.
- `POST /transaction/createAllTransactions`: Creates a list of transactions.
- `GET  /transaction/id/{id}`: Fetches transaction by Id.
- `GET  /transaction/month/{month}`: Fetches transaction by Month.
- `GET  /transaction/allTransactions`: Gets list of all the transactions from the DB
- `GET  /reward/month/{month}`: Gets reward for the total amount spend on transactions for the given month

## API Payloads

- To load the data into DB, you first need to hit API endpoint :
POST - /transaction/createAllTransactions

payload: 
[
    {
        "transactionName": "Bread",
        "transactionAmount": 50,
        "transactionMonth": 1
    },
    {
        "transactionName": "Peanut Butter",
        "transactionAmount": 500,
        "transactionMonth": 1
    },
    {
        "transactionName": "Chocholate Powder",
        "transactionAmount": 80,
        "transactionMonth": 1
    },
    {
        "transactionName": "Cashew",
        "transactionAmount": 700,
        "transactionMonth": 1
    },
    {
        "transactionName": "Almond",
        "transactionAmount": 500,
        "transactionMonth": 1
    },
    {
        "transactionName": "Honey",
        "transactionAmount": 450,
        "transactionMonth": 1
    }
    {
        "transactionName": "Egg",
        "transactionAmount": 100,
        "transactionMonth": 2
    },
    {
        "transactionName": "Hazelnut",
        "transactionAmount": 1000,
        "transactionMonth": 2
    },
    {
        "transactionName": "Figs",
        "transactionAmount": 900,
        "transactionMonth": 2
    },
    {
        "transactionName": "Walnut",
        "transactionAmount": 880,
        "transactionMonth": 2
    }
]

- To add a single transaction to the DB, use :
POST - /transaction/createTransaction
    {
        "transactionName": "Peanut Butter",
        "transactionAmount": 500,
        "transactionMonth": 1
    }


