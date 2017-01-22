use nasdaqtst;

set FOREIGN_KEY_CHECKS = 0;
truncate table transactions;
truncate table users;
set FOREIGN_KEY_CHECKS = 1;

insert into `users` (`email`, `balance`)
values
  ('bob@aol.com', 10),
  ('sue@aol.com', 50200),
  ('car@aol.com', 210);

insert into `transactions` (`action`, `symbol`, `quantity`, `amount`, `user_id`)
values
  ('BUY', 'AAPL', 9, 250.5, 1),
  ('BUY', 'AAPL', 7, 120.5, 1),
  ('BUY', 'GOOG', 5, 290.5, 1),
  ('BUY', 'AMZN', 3, 460.5, 1),
  ('SELL', 'AAPL', 1, 570.5, 1),
  ('SELL', 'AAPL', 1, 570.5, 1),

  ('BUY', 'AAPL', 11, 9850.5, 2),
  ('BUY', 'AAPL', 12, 9850.5, 2),
  ('BUY', 'IBM', 13, 1150.5, 2),
  ('BUY', 'ALL', 14, 9850.5, 2),
  ('SELL', 'AAPL', 4, 2420.5, 2);
