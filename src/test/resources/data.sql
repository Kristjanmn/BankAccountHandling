-- Create first account
INSERT INTO account (id) VALUES ('36ea0ece-4781-4586-b4bf-ae6cdd620d39');
-- Create account balances
INSERT INTO balance (id, currency, amount) VALUES ('5b479423-cfb9-432f-a7b6-1111a6e17d06', 0, 34.22); -- EUR
INSERT INTO balance (id, currency, amount) VALUES ('e77ff4bd-b53b-4a15-9302-20e2facb9c6f', 1, 59.66); -- USD
INSERT INTO balance (id, currency, amount) VALUES ('112a07b0-2d08-4f63-8d64-e2b54100515c', 2, 53.64); -- SEK
INSERT INTO balance (id, currency, amount) VALUES ('3983a85c-d244-46df-afd1-b08b1e8e9cf3', 3, 38.57); -- RUB
-- Attach balances to account
INSERT INTO account_balances (account_id, balances_id) values ('36ea0ece-4781-4586-b4bf-ae6cdd620d39', '5b479423-cfb9-432f-a7b6-1111a6e17d06');
INSERT INTO account_balances (account_id, balances_id) values ('36ea0ece-4781-4586-b4bf-ae6cdd620d39', 'e77ff4bd-b53b-4a15-9302-20e2facb9c6f');
INSERT INTO account_balances (account_id, balances_id) values ('36ea0ece-4781-4586-b4bf-ae6cdd620d39', '112a07b0-2d08-4f63-8d64-e2b54100515c');
INSERT INTO account_balances (account_id, balances_id) values ('36ea0ece-4781-4586-b4bf-ae6cdd620d39', '3983a85c-d244-46df-afd1-b08b1e8e9cf3');
-- End first account