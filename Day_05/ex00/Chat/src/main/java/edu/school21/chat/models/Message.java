package edu.school21.chat.models;

import java.util.Date;
import java.util.Objects;

public class Message {
    private Integer     id;
    private User        author;
    private Chatroom    room;
    private String      text;
    private Date        date;

    public Message(Integer id, User author, Chatroom room, String text, Date date) {
        this.id = id;
        this.author = author;
        this.room = room;
        this.text = text;
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Message)) return false;
        Message message = (Message) o;
        return id.equals(message.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", author=" + author +
                ", room=" + room +
                ", text='" + text + '\'' +
                ", date=" + date +
                '}';
    }
}
