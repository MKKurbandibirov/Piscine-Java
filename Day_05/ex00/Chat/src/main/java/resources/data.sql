INSERT INTO users (login, password)
VALUES ('Bob', '1111');
INSERT INTO users (login, password)
VALUES ('Andrew', '1112');
INSERT INTO users (login, password)
VALUES ('Mike', '1113');
INSERT INTO users (login, password)
VALUES ('John', '1114');
INSERT INTO users (login, password)
VALUES ('Steve', '1115');

INSERT INTO chatroom (name, owner)
VALUES ('room1', 1);
INSERT INTO chatroom (name, owner)
VALUES ('room2', 2);
INSERT INTO chatroom (name, owner)
VALUES ('room3', 3);
INSERT INTO chatroom (name, owner)
VALUES ('room4', 4);
INSERT INTO chatroom (name, owner)
VALUES ('room5', 5);

INSERT INTO message (author, room, text, date)
VALUES (1, 2, 'Hello1!', TO_TIMESTAMP('2017-03-31 9:30:20', 'YYYY-MM-DD HH:MI:SS'));
INSERT INTO message (author, room, text, date)
VALUES (1, 2, 'Hello2!', TO_TIMESTAMP('2018-03-31 9:30:20', 'YYYY-MM-DD HH:MI:SS'));
INSERT INTO message (author, room, text, date)
VALUES (1, 2, 'Hello3!', TO_TIMESTAMP('2019-03-31 9:30:20', 'YYYY-MM-DD HH:MI:SS'));
INSERT INTO message (author, room, text, date)
VALUES (1, 2, 'Hello4!', TO_TIMESTAMP('2020-03-31 9:30:20', 'YYYY-MM-DD HH:MI:SS'));
INSERT INTO message (author, room, text, date)
VALUES (1, 2, 'Hello5!', TO_TIMESTAMP('2021-03-31 9:30:20', 'YYYY-MM-DD HH:MI:SS'));
