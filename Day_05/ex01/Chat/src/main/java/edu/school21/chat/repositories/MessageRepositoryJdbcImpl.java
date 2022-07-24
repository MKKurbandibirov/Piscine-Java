package edu.school21.chat.repositories;

import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class MessageRepositoryJdbcImpl implements MessageRepository{
    private DataSource ds;

    public MessageRepositoryJdbcImpl(DataSource ds) {
        this.ds = ds;
    }

    @Override
    public Optional<Message> findById(Long id) throws SQLException {
        Optional<Message> optionalMessage;

        Connection connection = ds.getConnection();

        Statement statement1 = connection.createStatement();
        String query = "SELECT * FROM message WHERE id = " + id;;
        ResultSet resultSet = statement1.executeQuery(query);
        resultSet.next();

        Statement statement2 = connection.createStatement();
        String userQuery = "SELECT * FROM users WHERE id = " + resultSet.getInt("author");;
        ResultSet userResultSet = statement2.executeQuery(userQuery);
        userResultSet.next();
        User user = new User(userResultSet.getInt("id"), userResultSet.getString("login"),
                userResultSet.getString("password"), null, null);

        Statement statement3 = connection.createStatement();
        String roomQuery = "SELECT * FROM chatroom WHERE id = " + resultSet.getInt("room");
        ResultSet roomResultSet = statement3.executeQuery(roomQuery);
        roomResultSet.next();
        Chatroom chatroom = new Chatroom(roomResultSet.getInt("id"),
                roomResultSet.getString("name"), null, null);

        optionalMessage = Optional.of(new Message(
                resultSet.getInt("id"), user, chatroom,
                resultSet.getString("text"),
                LocalDateTime.parse(resultSet.getString("date"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));
        return optionalMessage;
    }
}
