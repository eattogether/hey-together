insert into shop(delivery_tip, minimum_order_price, latitude, longitude) values (5000, 18000, 3.3, 5.5);
insert into shop(delivery_tip, minimum_order_price, latitude, longitude) values (3000, 15000, 1.1, 2.2);

insert into menu(menu_name, price, shop_id) values ('떡볶이', 15000, 1);
insert into menu(menu_name, price, shop_id) values ('직화', 25000, 1);

insert into article(dead_line, latitude, longitude, title) values (now(), 1.1, 2.2, '같이묵자');