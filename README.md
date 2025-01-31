# Bank Account Handling REST API

## Purpose
### The purpose of this project is made to showcase my skills with Java and Spring Framework.

---
# Setup Instructions
## Command line
### Run command in project directory
``
.\mvnw spring-boot:run
``

## Docker
### Run command in project directory
``
docker-compose up
``

---
# API Documentations
## Account endpoints
#### Get all accounts
```
method              GET
endpoint            /account
description         Get all available accounts
response            CustomResponse
request example     localhost:8080/account
response example    {
                        "success":true,
                        "message":"success",
                        "object":[
                            {
                                "id":"472aa230-3654-4c74-a1c4-0ef66ebc04cd",
                                "balances":[
                                    {"currency":"EUR",
                                    "amount":0.0
                                },{
                                    "currency":"USD",
                                    "amount":0.0
                                },{
                                    "currency":"SEK",
                                    "amount":0.0
                                },{
                                    "currency":"RUB",
                                    "amount":0.0
                                }]
                            }
                        ]
                    }
```

#### Get new account
```
method              GET
endpoint            /account/new
description         Get new account
response            CustomResponse
request example     localhost:8080/account/new
response example    {
                        "success":true,
                        "message":"success",
                        "object":{
                            "id":"472aa230-3654-4c74-a1c4-0ef66ebc04cd",
                            "balances":[
                                {
                                    "currency":"EUR",
                                    "amount":0.0
                                },{
                                    "currency":"USD",
                                    "amount":0.0
                                },{
                                    "currency":"SEK",
                                    "amount":0.0
                                },{
                                    "currency":"RUB",
                                    "amount":0.0
                                }
                            ]
                        }
                    }
```

#### Get new account
```
method              GET
endpoint            /account/{id}
description         Get account by ID
response            CustomResponse
request example     localhost:8080/account/472aa230-3654-4c74-a1c4-0ef66ebc04cd
response example    {
                        "success":true,
                        "message":"success",
                        "object":{
                            "id":"472aa230-3654-4c74-a1c4-0ef66ebc04cd",
                            "balances":[
                                {
                                    "currency":"EUR",
                                    "amount":0.0
                                },{
                                    "currency":"USD",
                                    "amount":0.0
                                },{
                                    "currency":"SEK",
                                    "amount":0.0
                                },{
                                    "currency":"RUB",
                                    "amount":0.0
                                }
                            ]
                        }
                    }
```

#### Deposit money
```
method              POST
endpoint            /account/deposit
description         Deposit money into account
response            CustomResponse
boy                 DepositDTO
request example     localhost:8080/account/deposit
request body        {
                        "accountId": "13bd56d4-d682-49b3-aef2-2aca534ee5aa",
                        "currency": "SEK",
                        "amount": 20.59
                    }
response example    {
                        "success": true,
                        "message": "success",
                        "object": {
                            "id": "13bd56d4-d682-49b3-aef2-2aca534ee5aa",
                            "balances": [
                                {
                                    "currency": "EUR",
                                    "amount": 0.0
                                },
                                {
                                    "currency": "USD",
                                    "amount": 0.0
                                },
                                {
                                    "currency": "SEK",
                                    "amount": 20.59
                                },
                                {
                                    "currency": "RUB",
                                    "amount": 0.0
                                }
                            ]
                        }
                    }
```


#### Withdraw money
```
method              POST
endpoint            /account/withdraw
description         Withdraw money from account
response            CustomResponse
body                WithdrawDTO
request example     localhost:8080/account/withdraw
request body        {
                        "accountId": "13bd56d4-d682-49b3-aef2-2aca534ee5aa",
                        "currency": "SEK",
                        "amount": 17.25
                    }
response example    {
                        "success": true,
                        "message": "success",
                        "object": {
                            "id": "13bd56d4-d682-49b3-aef2-2aca534ee5aa",
                            "balances": [
                                {
                                    "currency": "EUR",
                                    "amount": 0.0
                                },
                                {
                                    "currency": "USD",
                                    "amount": 0.0
                                },
                                {
                                    "currency": "SEK",
                                    "amount": 3.34
                                },
                                {
                                    "currency": "RUB",
                                    "amount": 0.0
                                }
                            ]
                        }
                    }
```

#### Exchange currency
```
method              POST
endpoint            /account/exchange
description         Exchange money in account from one currency to another
response            CustomResponse
body                ExchangeDTO
request example     localhost:8080/account/exchange
request body        {
                        "accountId": "13bd56d4-d682-49b3-aef2-2aca534ee5aa",
                        "fromCurrency": "SEK",
                        "toCurrency": "USD",
                        "amount": 26.33
                    }
response example    {
                        "success": true,
                        "message": "success",
                        "object": {
                            "id": "13bd56d4-d682-49b3-aef2-2aca534ee5aa",
                            "balances": [
                                {
                                    "currency": "EUR",
                                    "amount": 0.0
                                },
                                {
                                    "currency": "USD",
                                    "amount": 2.4090923160494837
                                },
                                {
                                    "currency": "SEK",
                                    "amount": 11.510000000000005
                                },
                                {
                                    "currency": "RUB",
                                    "amount": 0.0
                                }
                            ]
                        }
                    }
```

## External call endpoints
#### Get random status code
```
method              GET
endpoint            /external
description         Get random status code from httpstat.us API
response            CustomResponse
request example     localhost:8080/external
response example    {
                        "success":true,
                        "message":"{\"code\":278,\"description\":\"278 Unknown Code\"}",
                        "object":null
                    }
```

#### Get status code
```
method              GET
endpoint            /external/{code}
description         Get desired status code
response            CustomResponse
request example     localhost:8080/external/202
response example    {
                        "success":true,
                        "message":"{\"code\":202,\"description\":\"Accepted\"}",
                        "object":null
                    }
```

#### Send request for all status codes
```
method              GET
endpoint            /external/all
description         Send request for all status codes
response            CustomResponse
request example     localhost:8080/external/all
response example    {
                        "success":true,
                        "message":"Made request for all supported codes",
                        "object":null
                    }
```

---
# Models
## Here are a list of models (Data Transfer Objects) used
### Client -> Server
#### Deposit
```
class               DepositDTO
description         Object used to deposit money into account
fields:
    accountId       account UUID, indicates which account the money gets deposited into
    currency        which currency money is being deposited
    amount          amount of money to deposit
```

#### Withdraw
```
class               WithdrawDTO
description         Object used to withdraw money from account
fields:
    accountId       account UUID, indicates which account the money gets withdrawn from
    currency        which currency money is being withdrawn
    amount          amount of money to withdraw
```

#### Exchange
```
class               ExchangeDTO
description         Object used to exchange money for one currency to another
fields:
    accountId       account UUID, indicates which account performs money exchange
    fromCurrency    which currency to exchange from
    toCurrency      which currency to exchange to
    amount          amount of money to exchange
```

### Server -> Client
#### CustomResponse
```
class               io.nqa.commons.CustomResponse
description         Object used to send response to client.
fields:
    success         boolean indicating if client action was successfully performed
    message         plain text message provided to client
    object          object provided to client, a would-be body for regular request
```

#### Account
```
class               AccountDTO
description         Object used to send client overview of bank account and balance
fields:
    id              account UUID
    balances        list of BalanceDTO
```

#### Balance
```
class               BalanceDTO
description         Object used to send client overview of bank account balance
fields:
    currency        balance currency
    amount          amount of money in account in this currency
```

---
# Notes
For fixed currency conversion rates, I decided to use price for one gram of gold for each used currency at the same point in time.<br>
For example, when converting 50 USD to EUR, assuming that 1 gram of gold is worth 59.66 USD or 34.22 EUR...
```
50 (amount of USD) / 59.66 (USD for 1g gold) = 0.838082.. grams of gold
34.22 (EUR for 1g gold) * 0.838082.. (grams of gold) = 28.67918.. EUR
```