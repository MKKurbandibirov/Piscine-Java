package edu.school21.sockets.models;

import java.time.LocalDateTime;
import java.util.Objects;

public class Message {
    private Long author_id;
    private String text;
    private LocalDateTime time;

    public Message() {}

    public Message(Long author_id, String text, LocalDateTime time) {
        this.author_id = author_id;
        this.text = text;
        this.time = time;
    }

    public Long getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(Long author_id) {
        this.author_id = author_id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Message)) return false;
        Message message = (Message) o;
        return author_id.equals(message.author_id) && Objects.equals(text, message.text) && Objects.equals(time, message.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(author_id);
    }

    @Override
    public String toString() {
        return "Message{" +
                "author_id=" + author_id +
                ", text='" + text + '\'' +
                ", time=" + time +
                '}';
    }
}
