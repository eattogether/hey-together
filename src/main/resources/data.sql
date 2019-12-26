insert into users(user_id, password, point) values ('mamook0', '1234', 500000);

insert into shop(delivery_tip, minimum_order_price, latitude, longitude) values (5000, 18000, 3.3, 5.5);
insert into shop(delivery_tip, minimum_order_price, latitude, longitude) values (3000, 15000, 1.1, 2.2);

insert into shop_menu(menu_name, price, shop_id) values ('떡볶이', 15000, 1);
insert into shop_menu(menu_name, price, shop_id) values ('직화', 25000, 1);

insert into article(dead_line, latitude, longitude, title, article_status, user_id) values (now(), 1.1, 2.2, '같이묵자', 'ACTIVE', 1);

insert into orders(article_id, shop_id, user_id) values (1, 1, 1);

insert into order_item(count, shop_menu_id, orders_id) values (2, 1, 1);
insert into order_item(count, shop_menu_id, orders_id) values (1, 2, 1);