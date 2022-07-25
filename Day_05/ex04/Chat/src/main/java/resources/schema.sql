CREATE TABLE users (
    id          SERIAL PRIMARY KEY,
    login       TEXT NOT NULL,
    password    TEXT NOT NULL
);

CREATE TABLE chatroom (
    id          SERIAL PRIMARY KEY,
    name        TEXT NOT NULL,
    owner       INTEGER NOT NULL REFERENCES users(id)
);

CREATE TABLE message (
    id          SERIAL PRIMARY KEY,
    author      INTEGER NOT NULL REFERENCES users(id),
    room        INTEGER NOT NULL REFERENCES chatroom(id),
    text        TEXT NOT NULL ,
    date        TIMESTAMP
);