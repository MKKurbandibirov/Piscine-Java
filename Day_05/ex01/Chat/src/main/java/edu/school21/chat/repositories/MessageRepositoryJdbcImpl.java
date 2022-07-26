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
        String query = "SELECT message.id, text, date, c.id, name, u.id, login, password FROM message " +
                "JOIN chatroom c ON c.id = message.room " +
                "JOIN users u ON u.id = message.author " +
                "WHERE message.id = " + id;
        ResultSet resultSet = statement1.executeQuery(query);
        resultSet.next();

        User user = new User(resultSet.getInt(6), resultSet.getString(7),
                resultSet.getString(8), null, null);

        Chatroom chatroom = new Chatroom(resultSet.getInt(4),
                resultSet.getString(5), null, null);

        optionalMessage = Optional.of(new Message(
                resultSet.getInt(1), user, chatroom,
                resultSet.getString(2),
                LocalDateTime.parse(resultSet.getString(3), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));
        return optionalMessage;
    }
}
