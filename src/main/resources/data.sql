-- CREATE DATABASE IF NOT EXISTS inditex_locker;

-- USE inditex_locker;

INSERT INTO products (name, stock) VALUES ('Tshirt', 10);
INSERT INTO products (name, stock) VALUES ('Trousers', 18);
INSERT INTO products (name, stock) VALUES ('Clips', 200);
INSERT INTO products (name, stock) VALUES ('Bag', 3);

INSERT INTO customers(name, addressx, addressy) VALUES ('Reese Johnson', 14, 37);
INSERT INTO customers(name, addressx, addressy) VALUES ('Morgan Rodriguez', 46, 44);
INSERT INTO customers(name, addressx, addressy) VALUES ('Morgan Brown', 31, 23);
INSERT INTO customers(name, addressx, addressy) VALUES ('Alex Brown', 10, 29);
INSERT INTO customers(name, addressx, addressy) VALUES ('Jamie Martinez', 43, 29);
INSERT INTO customers(name, addressx, addressy) VALUES ('Casey Smith', 43, 31);
INSERT INTO customers(name, addressx, addressy) VALUES ('Alex Brown', 42, 41);
INSERT INTO customers(name, addressx, addressy) VALUES ('Jordan Williams', 18, 6);
INSERT INTO customers(name, addressx, addressy) VALUES ('Riley Garcia', 9, 20);
INSERT INTO customers(name, addressx, addressy) VALUES ('Avery Davis', 4, 0);
INSERT INTO customers(name, addressx, addressy) VALUES ('Morgan Johnson', 31, 21);
INSERT INTO customers(name, addressx, addressy) VALUES ('Avery Brown', 25, 37);
INSERT INTO customers(name, addressx, addressy) VALUES ('Casey Davis', 15, 36);
INSERT INTO customers(name, addressx, addressy) VALUES ('Reese Jones', 11, 25);
INSERT INTO customers(name, addressx, addressy) VALUES ('Alex Garcia', 0, 12);
INSERT INTO customers(name, addressx, addressy) VALUES ('Taylor Williams', 34, 21);
INSERT INTO customers(name, addressx, addressy) VALUES ('Riley Williams', 2, 9);
INSERT INTO customers(name, addressx, addressy) VALUES ('Sam Williams', 38, 25);
INSERT INTO customers(name, addressx, addressy) VALUES ('Taylor Martinez', 48, 48);
INSERT INTO customers(name, addressx, addressy) VALUES ('Avery Rodriguez', 40, 32);
INSERT INTO customers(name, addressx, addressy) VALUES ('Jamie Martinez', 41, 27);
INSERT INTO customers(name, addressx, addressy) VALUES ('Alex Davis', 45, 14);
INSERT INTO customers(name, addressx, addressy) VALUES ('Avery Williams', 6, 49);
INSERT INTO customers(name, addressx, addressy) VALUES ('Alex Smith', 46, 21);
INSERT INTO customers(name, addressx, addressy) VALUES ('Morgan Jones', 24, 12);
INSERT INTO customers(name, addressx, addressy) VALUES ('Reese Miller', 20, 37);
INSERT INTO customers(name, addressx, addressy) VALUES ('Jamie Johnson', 4, 6);
INSERT INTO customers(name, addressx, addressy) VALUES ('Casey Garcia', 29, 20);
INSERT INTO customers(name, addressx, addressy) VALUES ('Jamie Johnson', 34, 30);
INSERT INTO customers(name, addressx, addressy) VALUES ('Morgan Rodriguez', 25, 26);
INSERT INTO customers(name, addressx, addressy) VALUES ('Taylor Jones', 1, 26);
INSERT INTO customers(name, addressx, addressy) VALUES ('Jordan Brown', 6, 23);
INSERT INTO customers(name, addressx, addressy) VALUES ('Reese Garcia', 36, 38);
INSERT INTO customers(name, addressx, addressy) VALUES ('Jamie Williams', 23, 40);
INSERT INTO customers(name, addressx, addressy) VALUES ('Riley Williams', 18, 8);
INSERT INTO customers(name, addressx, addressy) VALUES ('Riley Rodriguez', 40, 42);
INSERT INTO customers(name, addressx, addressy) VALUES ('Riley Williams', 18, 36);
INSERT INTO customers(name, addressx, addressy) VALUES ('Alex Miller', 43, 28);
INSERT INTO customers(name, addressx, addressy) VALUES ('Morgan Rodriguez', 17, 34);
INSERT INTO customers(name, addressx, addressy) VALUES ('Taylor Williams', 4, 12);
INSERT INTO customers(name, addressx, addressy) VALUES ('Sam Smith', 38, 8);
INSERT INTO customers(name, addressx, addressy) VALUES ('Avery Smith', 33, 35);
INSERT INTO customers(name, addressx, addressy) VALUES ('Jordan Williams', 34, 27);
INSERT INTO customers(name, addressx, addressy) VALUES ('Reese Johnson', 30, 15);
INSERT INTO customers(name, addressx, addressy) VALUES ('Jamie Miller', 15, 23);
INSERT INTO customers(name, addressx, addressy) VALUES ('Riley Miller', 17, 39);
INSERT INTO customers(name, addressx, addressy) VALUES ('Riley Smith', 38, 6);
INSERT INTO customers(name, addressx, addressy) VALUES ('Reese Garcia', 11, 17);
INSERT INTO customers(name, addressx, addressy) VALUES ('Jamie Brown', 46, 24);
INSERT INTO customers(name, addressx, addressy) VALUES ('Jordan Williams', 26, 3);

INSERT INTO lockers(addressx, addressy) VALUES (4, 44);
INSERT INTO lockers(addressx, addressy) VALUES (18, 46);
INSERT INTO lockers(addressx, addressy) VALUES (24, 6);
INSERT INTO lockers(addressx, addressy) VALUES (13, 26);
INSERT INTO lockers(addressx, addressy) VALUES (25, 7);
INSERT INTO lockers(addressx, addressy) VALUES (23, 23);
INSERT INTO lockers(addressx, addressy) VALUES (2, 17);
INSERT INTO lockers(addressx, addressy) VALUES (46, 43);
INSERT INTO lockers(addressx, addressy) VALUES (14, 30);
INSERT INTO lockers(addressx, addressy) VALUES (33, 32);
INSERT INTO lockers(addressx, addressy) VALUES (15, 33);
INSERT INTO lockers(addressx, addressy) VALUES (29, 34);
INSERT INTO lockers(addressx, addressy) VALUES (13, 16);
INSERT INTO lockers(addressx, addressy) VALUES (15, 13);
INSERT INTO lockers(addressx, addressy) VALUES (23, 22);
INSERT INTO lockers(addressx, addressy) VALUES (10, 13);
INSERT INTO lockers(addressx, addressy) VALUES (25, 34);
INSERT INTO lockers(addressx, addressy) VALUES (33, 45);
INSERT INTO lockers(addressx, addressy) VALUES (10, 12);
INSERT INTO lockers(addressx, addressy) VALUES (17, 11);
INSERT INTO lockers(addressx, addressy) VALUES (20, 25);
INSERT INTO lockers(addressx, addressy) VALUES (16, 1);
INSERT INTO lockers(addressx, addressy) VALUES (6, 35);
INSERT INTO lockers(addressx, addressy) VALUES (14, 40);
INSERT INTO lockers(addressx, addressy) VALUES (12, 36);
INSERT INTO lockers(addressx, addressy) VALUES (14, 42);
INSERT INTO lockers(addressx, addressy) VALUES (27, 9);
INSERT INTO lockers(addressx, addressy) VALUES (30, 45);
INSERT INTO lockers(addressx, addressy) VALUES (30, 19);
INSERT INTO lockers(addressx, addressy) VALUES (26, 48);

INSERT INTO obstacles(addressx, addressy) VALUES (21, 36);
INSERT INTO obstacles(addressx, addressy) VALUES (26, 17);
INSERT INTO obstacles(addressx, addressy) VALUES (35, 46);
INSERT INTO obstacles(addressx, addressy) VALUES (6, 50);
INSERT INTO obstacles(addressx, addressy) VALUES (17, 31);
INSERT INTO obstacles(addressx, addressy) VALUES (17, 4);
INSERT INTO obstacles(addressx, addressy) VALUES (33, 34);
INSERT INTO obstacles(addressx, addressy) VALUES (37, 29);
INSERT INTO obstacles(addressx, addressy) VALUES (38, 2);
INSERT INTO obstacles(addressx, addressy) VALUES (45, 41);
INSERT INTO obstacles(addressx, addressy) VALUES (15, 41);
INSERT INTO obstacles(addressx, addressy) VALUES (18, 5);
INSERT INTO obstacles(addressx, addressy) VALUES (12, 18);
INSERT INTO obstacles(addressx, addressy) VALUES (29, 13);
INSERT INTO obstacles(addressx, addressy) VALUES (40, 7);
INSERT INTO obstacles(addressx, addressy) VALUES (31, 7);
INSERT INTO obstacles(addressx, addressy) VALUES (31, 6);
INSERT INTO obstacles(addressx, addressy) VALUES (3, 38);
INSERT INTO obstacles(addressx, addressy) VALUES (8, 31);
INSERT INTO obstacles(addressx, addressy) VALUES (8, 20);
INSERT INTO obstacles(addressx, addressy) VALUES (32, 23);
INSERT INTO obstacles(addressx, addressy) VALUES (1, 34);
INSERT INTO obstacles(addressx, addressy) VALUES (25, 15);
INSERT INTO obstacles(addressx, addressy) VALUES (35, 49);
INSERT INTO obstacles(addressx, addressy) VALUES (28, 38);
INSERT INTO obstacles(addressx, addressy) VALUES (35, 45);
INSERT INTO obstacles(addressx, addressy) VALUES (28, 25);
INSERT INTO obstacles(addressx, addressy) VALUES (32, 25);
INSERT INTO obstacles(addressx, addressy) VALUES (35, 6);
INSERT INTO obstacles(addressx, addressy) VALUES (12, 28);
