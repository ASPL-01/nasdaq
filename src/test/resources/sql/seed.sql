use nasdaqtst;

set FOREIGN_KEY_CHECKS = 0;
truncate table transactions;
truncate table users;
set FOREIGN_KEY_CHECKS = 1;

insert into `users` (`email`, `balance`)
values
  ('bob@aol.com', 10),
  ('sue@aol.com', 50200);

insert into `transactions` (`action`, `symbol`, `quantity`, `amount`, `user_id`)
values
  ('BUY', 'AAPL', 3, 250.5, 1),
  ('SELL', 'GE', 2, 120.5, 1),
  ('BUY', 'AMZN', 5, 460.5, 1),
  ('SELL', 'MSFT', 11, 570.5, 1),
  ('BUY', 'GOOG', 6, 290.5, 1),
  ('SELL', 'IBM', 9, 1150.5, 2),
  ('BUY', 'ALL', 8, 9850.5, 2),
  ('BUY', 'CSCO', 4, 2420.5, 2);
