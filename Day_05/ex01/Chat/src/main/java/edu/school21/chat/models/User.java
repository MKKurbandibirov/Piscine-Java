package edu.school21.chat.models;

import java.util.List;
import java.util.Objects;

public class User {
    private Integer         id;
    private String          login;
    private String          password;
    private List<Chatroom>  createdRooms;
    private List<Chatroom>  userRooms;

    public User(Integer id, String login, String password, List<Chatroom> createdRooms, List<Chatroom> userRooms) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.createdRooms = createdRooms;
        this.userRooms = userRooms;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return id.equals(user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login +
                '}';
    }
}
