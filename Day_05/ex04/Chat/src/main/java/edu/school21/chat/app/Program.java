package edu.school21.chat.app;

import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.repositories.*;
import edu.school21.chat.models.*;

import java.sql.SQLException;
import java.util.List;

public class Program {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASS = "";

    public static void main(String[] args) throws SQLException {
        HikariDataSource ds = new HikariDataSource();
        ds.setJdbcUrl(DB_URL);
        ds.setUsername(USER);
        ds.setPassword(PASS);

        UsersRepository usersRepository = new UsersRepositoryJdbcImpl(ds);

        List<User> users = usersRepository.findAll(1, 3);
        for (User user : users) {
            System.out.printf("User{id = %d, login = %s, password = %s, \n", user.getId(), user.getLogin(), user.getPassword());
            System.out.printf("\tcreatedRooms[");
            for (Chatroom room : user.getCreatedRooms()) {
                System.out.printf(" Chatroom{id = %d, name = %s} ", room.getId(), room.getName());
            }
            System.out.println("],");
            System.out.printf("\tuserRooms[");
            for (Chatroom room : user.getUserRooms()) {
                System.out.printf(" Chatroom{id = %d} ", room.getId());
            }
            System.out.println("]};\n");
        }
    }
}
