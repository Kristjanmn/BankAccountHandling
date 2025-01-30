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
## Currency endpoints

---
# Notes
For fixed currency conversion rates, I decided to use price for one gram of gold for each used currency at the same point in time.<br>
For example, when converting 50 USD to EUR, assuming that 1 gram of gold is worth 59.66 USD or 34.22 EUR...
```
50 (amount of USD) / 59.66 (USD for 1g gold) = 0.838082.. grams of gold
34.22 (EUR for 1g gold) * 0.838082.. (grams of gold) = 28.67918.. EUR
```