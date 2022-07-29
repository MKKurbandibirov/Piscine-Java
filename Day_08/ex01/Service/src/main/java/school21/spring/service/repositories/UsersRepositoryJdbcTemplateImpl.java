package school21.spring.service.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;
import school21.spring.service.models.User;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

public class UsersRepositoryJdbcTemplateImpl implements UsersRepository {
    private final JdbcTemplate template;

    private final RowMapper<User> ROW_MAPPER = (ResultSet resultSet, int rowNum) ->
            new User(resultSet.getLong(1), resultSet.getString(2));

    public UsersRepositoryJdbcTemplateImpl(DataSource ds) {
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
        this.template.update(String
                .format("INSERT INTO users (email) VALUES ('%s')", entity.getEmail()),
                ROW_MAPPER);
    }

    @Override
    public void update(User entity) {
        this.template.update(String
                .format("UPDATE users SET email = '%s' WHERE id = %d",
                entity.getEmail(), entity.getId()), ROW_MAPPER);
    }

    @Override
    public void delete(Long id) {
        this.template.update(String
                .format("DELETE FROM users WHERE id = %d", id), ROW_MAPPER);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        Optional<User> optionalUser = Optional.empty();
        try {
            optionalUser = Optional.ofNullable(this.template.queryForObject(String
                    .format("SELECT * FROM users WHERE email = '%s'", email), ROW_MAPPER));
        } catch (DataAccessException e) {
            System.err.println("Couldn't find user with email = " + email);
        }
        return optionalUser;
    }
}