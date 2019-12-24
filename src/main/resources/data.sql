insert into shop(delivery_tip, minimum_order_price, latitude, longitude) values (5000, 18000, 3.4, 5.6);
insert into shop(delivery_tip, minimum_order_price, latitude, longitude) values (2000, 18000, 3.5, 5.7);
insert into shop(delivery_tip, minimum_order_price, latitude, longitude) values (3000, 12000, 3.6, 5.8);
insert into shop(delivery_tip, minimum_order_price, latitude, longitude) values (4000, 18000, 3.7, 5.9);
insert into shop(delivery_tip, minimum_order_price, latitude, longitude) values (5000, 7000, 3.8, 5.10);
insert into shop(delivery_tip, minimum_order_price, latitude, longitude) values (2000, 13000, 3.9, 5.11);
insert into shop(delivery_tip, minimum_order_price, latitude, longitude) values (3000, 14000, 1.1, 2.12);

insert into shop_menu(menu_name, price, shop_id) values ('떡볶이', 15000, 1);
insert into shop_menu(menu_name, price, shop_id) values ('오뎅', 3000, 1);
insert into shop_menu(menu_name, price, shop_id) values ('오징어 튀김', 4000, 1);
insert into shop_menu(menu_name, price, shop_id) values ('김말이', 4000, 1);
insert into shop_menu(menu_name, price, shop_id) values ('참치마요', 3000, 1);

insert into shop_menu(menu_name, price, shop_id) values ('1인 직화', 9000, 2);
insert into shop_menu(menu_name, price, shop_id) values ('2인 직화', 15000, 2);
insert into shop_menu(menu_name, price, shop_id) values ('3인 직화', 25000, 2);

insert into shop_menu(menu_name, price, shop_id) values ('짜장면', 5000, 3);
insert into shop_menu(menu_name, price, shop_id) values ('짬뽕', 4000, 3);
insert into shop_menu(menu_name, price, shop_id) values ('짬뽕밥', 4000, 3);
insert into shop_menu(menu_name, price, shop_id) values ('탕수육', 14000, 3);
insert into shop_menu(menu_name, price, shop_id) values ('유린기', 14000, 3);
insert into shop_menu(menu_name, price, shop_id) values ('짬짜면', 6000, 3);

insert into shop_menu(menu_name, price, shop_id) values ('김밥', 3500, 4);
insert into shop_menu(menu_name, price, shop_id) values ('참치김밥', 4000, 4);
insert into shop_menu(menu_name, price, shop_id) values ('띠드김밥', 4000, 4);
insert into shop_menu(menu_name, price, shop_id) values ('돈까스김밥', 4000, 4);
insert into shop_menu(menu_name, price, shop_id) values ('땡초김밥', 4000, 4);
insert into shop_menu(menu_name, price, shop_id) values ('스페샬김밥', 6000, 4);

insert into shop_menu(menu_name, price, shop_id) values ('1인 찜닭', 13500, 5);
insert into shop_menu(menu_name, price, shop_id) values ('2인 찜닭', 24000, 5);
insert into shop_menu(menu_name, price, shop_id) values ('3인 찜닭', 34000, 5);
insert into shop_menu(menu_name, price, shop_id) values ('4인 찜닭', 44000, 5);
insert into shop_menu(menu_name, price, shop_id) values ('치즈스틱', 4000, 5);

insert into shop_menu(menu_name, price, shop_id) values ('후라이드', 18000, 6);
insert into shop_menu(menu_name, price, shop_id) values ('쀼링클', 19000, 6);
insert into shop_menu(menu_name, price, shop_id) values ('뿌링클 순살', 20000, 6);
insert into shop_menu(menu_name, price, shop_id) values ('치즈볼', 5000, 6);
insert into shop_menu(menu_name, price, shop_id) values ('뿌링 치즈볼', 4000, 6);

insert into shop_menu(menu_name, price, shop_id) values ('황금 올리브', 18000, 7);
insert into shop_menu(menu_name, price, shop_id) values ('닭 껍데기', 3500, 7);
insert into shop_menu(menu_name, price, shop_id) values ('돼지 껍데기', 5000, 7);
insert into shop_menu(menu_name, price, shop_id) values ('치즈볼', 5000, 7);
insert into shop_menu(menu_name, price, shop_id) values ('치즈스틱', 4000, 7);

insert into user(user_id, password, point) values('mamook1', '1234', 30000)
insert into user(user_id, password, point) values('mamook2', '1234', 30000)
insert into user(user_id, password, point) values('mamook3', '1234', 30000)

insert into article(dead_line, latitude, longitude, title,article_status) values (now(), 1.1, 2.2, '같이묵자','ACTIVE');

insert into orders(article_id, shop_id) values (1, 1);

insert into order_item(count, shop_menu_id, orders_id) values (2, 1, 1);
insert into order_item(count, shop_menu_id, orders_id) values (1, 2, 1);

