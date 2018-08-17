INSERT INTO movie VALUES (1, 18, 'Movie description', 'http://womo.ua/wp-content/uploads/2017/03/king-kong.jpg', 'Kong: Skull Island');
INSERT INTO movie VALUES (2, 18, 'Movie description', 'https://lh3.googleusercontent.com/Yr8-vZzeWTXuqqcOOTJtiHi_N9t5-6DLD87af8PI_BtcAPc-BL2A1ICnJt43TXjqs5gvpdSJbur94Q', 'Titanic');

INSERT INTO hall VALUES (1, 'hall #1');
INSERT INTO hall VALUES (2, 'hall #2');

INSERT INTO cinema.session VALUES (1, NOW(), 1, 1);
INSERT INTO cinema.session VALUES (2, NOW(), 2, 2);

INSERT INTO cinema.session VALUES (3, NOW() + INTERVAL 1 DAY, 2, 2);

INSERT INTO user VALUES (1, 'roydgaryshka@gmail.com', 'Vitia', '$2a$12$Vh2k3a2vvfZakWcOTXjfU.j8itYno7a9Y0VXscoCUfXoWWUrMTLgW', '+380502564213', 'USER');
INSERT INTO user VALUES (2, 'royd@gmail.com', 'Vanya', '$2a$12$Vh2k3a2vvfZakWcOTXjfU.j8itYno7a9Y0VXscoCUfXoWWUrMTLgW', '+380502564213', 'USER');

INSERT INTO session_users VALUES (1, 1);
INSERT INTO session_users VALUES (2, 2);