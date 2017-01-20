use nasdaqtst;

set FOREIGN_KEY_CHECKS = 0;
truncate table transactions;
truncate table users;
set FOREIGN_KEY_CHECKS = 1;

insert into `users` (`email`)
values
  ('bob@aol.com'),
  ('sue@aol.com');
