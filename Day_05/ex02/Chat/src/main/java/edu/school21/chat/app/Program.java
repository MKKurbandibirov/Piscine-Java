package edu.school21.chat.app;

import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.repositories.MessagesRepository;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;
import edu.school21.chat.models.*;
import edu.school21.chat.repositories.NotSavedSubEntityException;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Program {
    private static String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static String USER = "postgres";
    private static String PASS = "";

    public static void main(String[] args){
        HikariDataSource ds = new HikariDataSource();
        ds.setJdbcUrl(DB_URL);
        ds.setUsername(USER);
        ds.setPassword(PASS);

        User creator = new User(5, "user", "user", new ArrayList(), new ArrayList());
        User author = creator;
        Chatroom room = new Chatroom(4, "room", creator, new ArrayList());
        Message message = new Message(null, author, room, "Welcome to the club buddy!", LocalDateTime.now());
        MessagesRepository messagesRepository = new MessagesRepositoryJdbcImpl(ds);

        try {
            messagesRepository.save(message);
        } catch (NotSavedSubEntityException | SQLException e) {
            System.err.println(e.getMessage());
            System.exit(-1);
        }
        System.out.println(message.getId());
    }
}
