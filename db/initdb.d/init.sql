CREATE TABLE hello_world
(
    id    varchar(10) PRIMARY KEY,
    hello varchar(30)
);

INSERT INTO hello_world(id, hello)
VALUES ('1', 'Hello, world!'),
       ('2', 'Bonjour, monde!'),
       ('3', 'Hola, mundo!'),
       ('4', 'Olá, mundo!'),
       ('5', 'こんにちは、世界！');

