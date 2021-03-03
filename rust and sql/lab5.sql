SELECT first_name, last_name FROM customers WHERE state = 'California';

SELECT name FROM items WHERE price > 10;

SELECT items.name, purchases.p_date from items, purchases WHERE items.i_id = purchases.i_id AND purchases.p_date = ‘2020-11-27’;

SELECT customers.first_name, customers.last_name FROM customers, purchases WHERE customers.c_id = purchases.c_id AND purchases.p_date = ‘2020-11-27’;

SELECT customers.first_name, customers.last_name FROM customers, purchases, items WHERE customers.c_id = purchases.c_id AND purchases.i_id = items.i_id AND items.price > 100 AND purchases.p_date = ‘2020-11-27’;