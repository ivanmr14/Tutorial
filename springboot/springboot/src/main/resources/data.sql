INSERT INTO category(name) VALUES ('Eurogames');
INSERT INTO category(name) VALUES ('Ameritrash');
INSERT INTO category(name) VALUES ('Familiar');

INSERT INTO client(name) VALUES ('Jose');
INSERT INTO client(name) VALUES ('Fernando');
INSERT INTO client(name) VALUES ('Marc');

INSERT INTO author(name, nationality) VALUES ('Alan R. Moon', 'US');
INSERT INTO author(name, nationality) VALUES ('Vital Lacerda', 'PT');
INSERT INTO author(name, nationality) VALUES ('Simone Luciani', 'IT');
INSERT INTO author(name, nationality) VALUES ('Perepau Llistosella', 'ES');
INSERT INTO author(name, nationality) VALUES ('Michael Kiesling', 'DE');
INSERT INTO author(name, nationality) VALUES ('Phil Walker-Harding', 'US');

INSERT INTO game(title, age, category_id, author_id) VALUES ('On Mars', '14', 1, 2);
INSERT INTO game(title, age, category_id, author_id) VALUES ('Aventureros al tren', '8', 3, 1);
INSERT INTO game(title, age, category_id, author_id) VALUES ('1920: Wall Street', '12', 1, 4);
INSERT INTO game(title, age, category_id, author_id) VALUES ('Barrage', '14', 1, 3);
INSERT INTO game(title, age, category_id, author_id) VALUES ('Los viajes de Marco Polo', '12', 1, 3);
INSERT INTO game(title, age, category_id, author_id) VALUES ('Azul', '8', 3, 5);

INSERT INTO prestamo(game_id, client_id, fechacomienzo, fechadevolucion) values (1, 1, '2010-01-01', '2010-01-15');
INSERT INTO prestamo(game_id, client_id, fechacomienzo, fechadevolucion) values (2, 2, '2011-01-01', '2011-01-15');
INSERT INTO prestamo(game_id, client_id, fechacomienzo, fechadevolucion) values (3, 3, '2012-01-01', '2012-01-15');
INSERT INTO prestamo(game_id, client_id, fechacomienzo, fechadevolucion) values (4, 1, '2013-01-01', '2013-01-15');
INSERT INTO prestamo(game_id, client_id, fechacomienzo, fechadevolucion) values (5, 2, '2014-01-01', '2014-01-15');
INSERT INTO prestamo(game_id, client_id, fechacomienzo, fechadevolucion) values (6, 3, '2015-01-01', '2015-01-15');
INSERT INTO prestamo(game_id, client_id, fechacomienzo, fechadevolucion) values (1, 1, '2016-01-01', '2016-01-15');
INSERT INTO prestamo(game_id, client_id, fechacomienzo, fechadevolucion) values (2, 2, '2017-01-01', '2017-01-15');
INSERT INTO prestamo(game_id, client_id, fechacomienzo, fechadevolucion) values (3, 3, '2018-01-01', '2018-01-15');