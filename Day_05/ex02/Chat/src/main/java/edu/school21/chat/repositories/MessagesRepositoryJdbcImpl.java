package edu.school21.chat.repositories;

import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class MessagesRepositoryJdbcImpl implements MessagesRepository {
    private DataSource ds;

    public MessagesRepositoryJdbcImpl(DataSource ds) {
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

    private boolean checkAuthor(User author, Connection connection) {
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM users WHERE id = " + author.getId();
            ResultSet resultSet = statement.executeQuery(query);
            return resultSet.next();
        } catch (SQLException e) {
            System.err.println("Couldn't create statement!");
        }
        return false;
    }

    private boolean checkRoom(Chatroom chatroom, Connection connection) {
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM chatroom WHERE id = " + chatroom.getId();
            ResultSet resultSet = statement.executeQuery(query);
            return resultSet.next();
        } catch (SQLException e) {
            System.err.println("Couldn't create statement!");
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void save(Message message) throws NotSavedSubEntityException, SQLException {
        Connection connection = ds.getConnection();

        if (!checkAuthor(message.getAuthor(), connection)) {
            throw new NotSavedSubEntityException("Could not find author for message!");
        }
        if (!checkRoom(message.getRoom(), connection)) {
            throw new NotSavedSubEntityException("Could not find chatroom for message!");
        }

        String query = "INSERT INTO message (author, room, text, date) VALUES ("
                + message.getAuthor().getId() + ", " + message.getRoom().getId() + ", '" + message.getText() + "', "
                + "TO_TIMESTAMP('"
                + message.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
                + "', 'YYYY-MM-DD HH:MI:SS'))";

        PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        statement.executeUpdate();
        ResultSet generatedKeys = statement.getGeneratedKeys();
        if (generatedKeys.next()) {
            message.setId(generatedKeys.getInt("id"));
        }
    }
}
