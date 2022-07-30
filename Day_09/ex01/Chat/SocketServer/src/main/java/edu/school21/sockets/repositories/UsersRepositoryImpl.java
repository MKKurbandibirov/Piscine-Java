package edu.school21.sockets.repositories;

import edu.school21.sockets.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Optional;

@Component("usersRepository")
public class UsersRepositoryImpl implements UsersRepository{
    private final JdbcTemplate template;

    private final RowMapper<User> ROW_MAPPER = (ResultSet resultSet, int rowNum) ->
            new User(resultSet.getLong(1), resultSet.getString(2), resultSet.getString(3));

    @Autowired
    public UsersRepositoryImpl(DataSource ds) {
        this.template = new JdbcTemplate(ds);
    }

    @Override
    public User findById(Long id) {
        User user = null;
        try {
            user = this.template.queryForObject(String
                    .format("SELECT * FROM users WHERE id = %d", id), ROW_MAPPER);
        } catch (DataAccessException e) {
            System.err.println("Couldn't find user with id = " + id);
        }
        return user;
    }

    @Override
    public List<User> findAll() {
        return this.template.query("SELECT * FROM users", ROW_MAPPER);
    }

    @Override
    public void save(User entity) {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodePass = encoder.encode(entity.getPassword());
        this.template.update(String
                .format("INSERT INTO users (username, password) VALUES ('%s', '%s')",
                        entity.getUsername(), encodePass));
    }

    @Override
    public void update(User entity) {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodePass = encoder.encode(entity.getPassword());
        this.template.update(String
                .format("UPDATE users SET username = '%s', password = '%s' WHERE id = %d",
                        entity.getUsername(), encodePass, entity.getId()));
    }

    @Override
    public void delete(Long id) {
        this.template.update(String
                .format("DELETE FROM users WHERE id = %d", id));
    }

    @Override
    public Optional<User> findByLogin(String username) {
        Optional<User> optionalUser = Optional.empty();
        try {
            optionalUser = Optional.ofNullable(this.template.queryForObject(String
                    .format("SELECT * FROM users WHERE username = '%s'", username), ROW_MAPPER));
        } catch (DataAccessException ignored) {}
        return optionalUser;
    }
}
