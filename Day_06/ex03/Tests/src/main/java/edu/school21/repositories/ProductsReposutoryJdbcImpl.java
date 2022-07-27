package edu.school21.repositories;

import edu.school21.models.Product;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductsReposutoryJdbcImpl implements ProductsRepository {

    private EmbeddedDatabase db;

    public ProductsReposutoryJdbcImpl(EmbeddedDatabase db) {
        this.db = db;
    }

    @Override
    public List<Product> findAll() {
        try {
            Connection connection = db.getConnection();
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM product";
            ResultSet resultSet = statement.executeQuery(query);
            List<Product> result = new ArrayList<>();
            while (resultSet.next()) {
                result.add(new Product(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getInt(3)));
            }
            try {
                connection.close();
                statement.close();
                resultSet.close();
            } catch (SQLException e) {}
            return result;
        } catch (SQLException e) {
            System.err.println("Something wrong with DB connection!");
        }
        return null;
    }

    @Override
    public Optional<Product> findById(Long id) {
        try {
            Connection connection = db.getConnection();
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM product WHERE id = " + id;
            ResultSet resultSet = statement.executeQuery(query);
            if (!resultSet.next()) {
                throw new RuntimeException("Not such product in DB!");
            }
            Optional<Product> optionalProduct = Optional.of(new Product(resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getInt(3)));
            try {
                connection.close();
                statement.close();
                resultSet.close();
            } catch (SQLException e) {}
            return optionalProduct;
        } catch (SQLException e) {
            System.err.println("Something wrong with DB connection!");
        }
        return Optional.empty();
    }

    @Override
    public void update(Product product) {
        try {
            Connection connection = db.getConnection();
            Statement statement = connection.createStatement();
            String query = "UPDATE product SET  name = '" + product.getName()
                    + "', price = " + product.getPrice() + " WHERE id = " + product.getId();
            statement.executeUpdate(query);
            try {
                connection.close();
                statement.close();
            } catch (SQLException e) {}
        } catch (SQLException e) {
            System.err.println("Something wrong with DB connection!");
        }
    }

    @Override
    public void save(Product product) {
        try {
            Connection connection = db.getConnection();
            Statement statement = connection.createStatement();
            String query = "INSERT INTO product (id, name, price) VALUES ("
                    + product.getId() + ", '"
                    + product.getName() + "', "
                    + product.getPrice() + ")";
            statement.executeUpdate(query);
            try {
                connection.close();
                statement.close();
            } catch (SQLException e) {}
        } catch (SQLException e) {
            System.err.println("Something wrong with DB connection!");
        }
    }

    @Override
    public void delete(Long id) {
        try {
            Connection connection = db.getConnection();
            Statement statement = connection.createStatement();
            String query = "DELETE FROM product WHERE id = " + id;
            statement.executeUpdate(query);
            try {
                connection.close();
                statement.close();
            } catch (SQLException e) {}
        } catch (SQLException e) {
            System.err.println("Something wrong with DB connection!");
        }
    }
}