-- customer table
insert into customer (name, surname, address, phone_number) values ('Seçkin', 'Ağırbaş', 'Alibeyköy', '05001112233');
insert into customer (name, surname, address, phone_number) values ('Mahmut', 'Acar'   , 'Kartal'   , '05002221133');
insert into customer (name, surname, address, phone_number) values ('Merve' , 'Özkan'  , 'Beşiktaş' , '05003332211');
insert into customer (name, surname, address, phone_number) values ('Aysel' , 'Tekin'  , 'Kadıköy'  , '05002223311');
insert into customer (name, surname, address, phone_number) values ('Orhan' , 'Yavuz'  , 'Ataşehir' , '05003331122');

-- product table
insert into product (name, description, price) values ('FoodHorse Olive Oil', 'FoodHorse brand olive oil 1L', 11.50);
insert into product (name, description, price) values ('FoodHorse Rice', 'FoodHorse brand rice 2.5kg', 13.75);
insert into product (name, description, price) values ('FoodHorse Milk', 'FoodHorse brand whole milk 1L', 3.75);
insert into product (name, description, price) values ('FoodHorse Kosher Dill Pickles', 'FoodHorse brand pickles 680g', 4.75);
insert into product (name, description, price) values ('FoodHorse Strawberry Jam', 'FoodHorse Strawberry Jam 380g', 6.45);

-- branch table
insert into branch (name, address) values ('Alibeyköy Center', 'Alibeyköy');
insert into branch (name, address) values ('Kadıköy Center'  , 'Kadıköy');
insert into branch (name, address) values ('Kadıköy Pier'   , 'Kadıköy');
insert into branch (name, address) values ('Beşiktaş Square' , 'Beşiktaş');
insert into branch (name, address) values ('Taksim Square'   , 'Taksim');


-- storing table
insert into storing (p_id, b_id, stock_quantity) values (1, 1, 50);
insert into storing (p_id, b_id, stock_quantity) values (2, 1, 50);
insert into storing (p_id, b_id, stock_quantity) values (3, 1, 50);
insert into storing (p_id, b_id, stock_quantity) values (2, 2, 50);
insert into storing (p_id, b_id, stock_quantity) values (3, 2, 50);
insert into storing (p_id, b_id, stock_quantity) values (4, 2, 50);
insert into storing (p_id, b_id, stock_quantity) values (3, 3, 50);
insert into storing (p_id, b_id, stock_quantity) values (4, 3, 50);
insert into storing (p_id, b_id, stock_quantity) values (5, 3, 50);
insert into storing (p_id, b_id, stock_quantity) values (4, 4, 50);
insert into storing (p_id, b_id, stock_quantity) values (5, 4, 50);
insert into storing (p_id, b_id, stock_quantity) values (5, 5, 50);


