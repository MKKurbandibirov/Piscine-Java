package edu.school21.chat.models;

import java.util.List;
import java.util.Objects;

public class Chatroom {
    private Integer         id;
    private String          name;
    private User            owner;
    private List<Message>   listOfMessages;

    public Chatroom(Integer id, String name, User owner, List<Message> listOfMessages) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.listOfMessages = listOfMessages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Chatroom)) return false;
        Chatroom chatroom = (Chatroom) o;
        return id.equals(chatroom.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Chatroom{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", owner=" + owner +
                ", listOfMessages=" + listOfMessages +
                '}';
    }
}
