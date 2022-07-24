package edu.school21.chat.app;

import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.repositories.MessageRepository;
import edu.school21.chat.repositories.MessageRepositoryJdbcImpl;

import java.sql.SQLException;
import java.util.Scanner;

public class Program {
    private static String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static String USER = "postgres";
    private static String PASS = "";
    public static void main(String[] args) throws SQLException{
        HikariDataSource ds = new HikariDataSource();
        ds.setJdbcUrl(DB_URL);
        ds.setUsername(USER);
        ds.setPassword(PASS);

        MessageRepository messageRepository = new MessageRepositoryJdbcImpl(ds);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a message ID");
        System.out.print("-> ");
        long id = scanner.nextLong();
        try {
            System.out.println(messageRepository.findById(id).get());
        } catch (SQLException e) {
            System.err.println("No message with id = " + id);
        }
    }
}
