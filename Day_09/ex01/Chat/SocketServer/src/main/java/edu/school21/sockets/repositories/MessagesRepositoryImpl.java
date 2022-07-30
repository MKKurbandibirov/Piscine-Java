package edu.school21.sockets.repositories;

import edu.school21.sockets.models.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Component("messagesRepository")
public class MessagesRepositoryImpl implements MessagesRepository {
    private final JdbcTemplate template;

    private final RowMapper<Message> ROW_MAPPER = (ResultSet resultSet, int rowNum) ->
            new Message(resultSet.getLong(1), resultSet.getString(2),
            LocalDateTime.parse(resultSet.getString(3), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

    @Autowired
    public MessagesRepositoryImpl(DataSource ds) {
        this.template = new JdbcTemplate(ds);
    }

    @Override
    public Message findById(Long id) {
        Message message = null;
        try {
            message = this.template.queryForObject(String
                    .format("SELECT * FROM messages WHERE id = %d", id), ROW_MAPPER);
        } catch (DataAccessException e) {
            System.err.println("Couldn't find message with id = " + id);
        }
        return message;
    }

    @Override
    public List<Message> findAll() {
        return this.template.query("SELECT * FROM messages", ROW_MAPPER);
    }

    @Override
    public void save(Message entity) {
        this.template.update(String
                .format("INSERT INTO messages (text, time) VALUES ('%s', '%s')", entity.getText(),
                        entity.getTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"))));
    }

    @Override
    public void update(Message entity) {
        this.template.update(String
                .format("UPDATE messages SET text = '%s', time = '%s' WHERE id = %d",
                entity.getText(), entity.getTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")), entity.getId()));
    }

    @Override
    public void delete(Long id) {
        this.template.update(String
                .format("DELETE FROM messages WHERE id = %d", id));
    }
}
