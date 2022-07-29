package school21.spring.service.repositories;

import com.zaxxer.hikari.HikariDataSource;
import school21.spring.service.models.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcImpl implements UsersRepository {
    private final HikariDataSource ds;

    public UsersRepositoryJdbcImpl(HikariDataSource ds) {
        this.ds = ds;
    }

    @Override
    public User findById(Long id) {
        User user = null;
        try {
            Connection connection = this.ds.getConnection();
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM users WHERE id = " + id;
            ResultSet resultSet = statement.executeQuery(query);
            if (!resultSet.next()) {
                throw new RuntimeException("Not such user in DB!");
            }
            user = new User(resultSet.getLong(1),
                    resultSet.getString(2));
            try {
                connection.close();
                statement.close();
                resultSet.close();
            } catch (SQLException ignored) {}
        } catch (SQLException e) {
            System.err.println("Something wrong with DB connection!");
        }
        return user;
    }

    @Override
    public List<User> findAll() {
        List<User> result = null;
        try {
            Connection connection = this.ds.getConnection();
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM users";
            ResultSet resultSet = statement.executeQuery(query);
            result = new ArrayList<>();
            while (resultSet.next()) {
                result.add(new User(resultSet.getLong(1),
                        resultSet.getString(2)));
            }
            try {
                connection.close();
                statement.close();
                resultSet.close();
            } catch (SQLException ignored) {}
        } catch (SQLException e) {
            System.err.println("Something wrong with DB connection!");
        }
        return result;
    }

    @Override
    public void save(User entity) {
        try {
            Connection connection = this.ds.getConnection();
            Statement statement = connection.createStatement();
            String query = "INSERT INTO users (email) VALUES ('" + entity.getEmail() + "');";
            statement.executeUpdate(query);
            try {
                connection.close();
                statement.close();
            } catch (SQLException ignored) {}
        } catch (SQLException e) {
            System.err.println("Something wrong with DB connection!");
        }
    }

    @Override
    public void update(User entity) {
        try {
            Connection connection = this.ds.getConnection();
            Statement statement = connection.createStatement();
            String query = "UPDATE users SET  email = '" + entity.getEmail()
                    + "' WHERE id = " + entity.getId();
            statement.executeUpdate(query);
            try {
                connection.close();
                statement.close();
            } catch (SQLException ignored) {}
        } catch (SQLException e) {
            System.err.println("Something wrong with DB connection!");
        }
    }

    @Override
    public void delete(Long id) {
        try {
            Connection connection = this.ds.getConnection();
            Statement statement = connection.createStatement();
            String query = "DELETE FROM users WHERE id = " + id;
            statement.executeUpdate(query);
            try {
                connection.close();
                statement.close();
            } catch (SQLException ignored) {}
        } catch (SQLException e) {
            System.err.println("Something wrong with DB connection!");
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        Optional<User> optionalProduct = Optional.empty();
        try {
            Connection connection = this.ds.getConnection();
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM users WHERE email = '" + email + "';";
            ResultSet resultSet = statement.executeQuery(query);
            if (!resultSet.next()) {
                throw new RuntimeException("Not such product in DB!");
            }
            optionalProduct = Optional.of(new User(resultSet.getLong(1),
                    resultSet.getString(2)));
            try {
                connection.close();
                statement.close();
                resultSet.close();
            } catch (SQLException ignored) {}
        } catch (SQLException e) {
            System.err.println("Something wrong with DB connection!");
        }
        return optionalProduct;
    }
}