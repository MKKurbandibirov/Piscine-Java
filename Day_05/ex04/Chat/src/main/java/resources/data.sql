INSERT INTO users (login, password)
VALUES ('Bob', '123456');
INSERT INTO users (login, password)
VALUES ('Andrew', '123456');
INSERT INTO users (login, password)
VALUES ('Mike', '123456');
INSERT INTO users (login, password)
VALUES ('John', '123456');
INSERT INTO users (login, password)
VALUES ('Steve', '123456');

INSERT INTO chatroom (name, owner)
VALUES ('Work', 4);
INSERT INTO chatroom (name, owner)
VALUES ('Shop', 3);
INSERT INTO chatroom (name, owner)
VALUES ('Neighbourhood', 1);
INSERT INTO chatroom (name, owner)
VALUES ('Sport', 1);
INSERT INTO chatroom (name, owner)
VALUES ('Family', 5);

INSERT INTO message (author, room, text, date)
VALUES (1, 1, 'Today is my first working day', TO_TIMESTAMP('2021-05-22 11:30:20', 'YYYY-MM-DD HH:MI:SS'));
INSERT INTO message (author, room, text, date)
VALUES (2, 4, 'Hi! Lets go jogging!', TO_TIMESTAMP('2020-06-12 5:30:20', 'YYYY-MM-DD HH:MI:SS'));
INSERT INTO message (author, room, text, date)
VALUES (4, 3, 'Whats up everybody! How are you doing?', TO_TIMESTAMP('2022-06-30 9:12:20', 'YYYY-MM-DD HH:MI:SS'));
INSERT INTO message (author, room, text, date)
VALUES (5, 3, 'We are fine! And you?', TO_TIMESTAMP('2022-07-1 12:30:20', 'YYYY-MM-DD HH:MI:SS'));
INSERT INTO message (author, room, text, date)
VALUES (3, 2, 'Is someone here?', TO_TIMESTAMP('2021-12-30 11:30:20', 'YYYY-MM-DD HH:MI:SS'));
