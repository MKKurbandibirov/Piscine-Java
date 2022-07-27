package edu.school21.repositories;

import edu.school21.models.Product;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class ProductsReposutoryJdbcImplTest {
    private EmbeddedDatabase db;
    private ProductsRepository productsRepository;
    final List<Product> EXPECTED_FIND_ALL_PRODUCTS = Arrays.asList(
            new Product(1, "Cake", 540),
            new Product(2, "Cookies", 120),
            new Product(3, "Juice", 140),
            new Product(4, "Coffee", 70),
            new Product(5, "Tea", 40)
    );
    final Product EXPECTED_FIND_BY_ID_PRODUCT = new Product(3, "Juice", 140);
    final Product EXPECTED_UPDATED_PRODUCT = new Product(3, "Cocktail", 170);
    final Product EXPECTED_SAVED_PRODUCT = new Product(6, "Bred", 40);

    @BeforeEach
    public void init() {
        db = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.HSQL)
                .addScript("schema.sql")
                .addScript("data.sql")
                .build();
        productsRepository = new ProductsReposutoryJdbcImpl(db);
    }

    @AfterEach
    public void close() {
        if (db != null)
            db.shutdown();
    }

    @Test
    public void findAllTest() {
        assertEquals(EXPECTED_FIND_ALL_PRODUCTS, productsRepository.findAll());
    }

    @Test
    public void findByIdTest() {
        assertEquals(EXPECTED_FIND_BY_ID_PRODUCT, productsRepository.findById(3L).get());
    }

    @Test
    public void updateTest() {
        productsRepository.update(EXPECTED_UPDATED_PRODUCT);
        assertEquals(EXPECTED_UPDATED_PRODUCT, productsRepository.findById(3L).get());
    }

    @Test
    public void saveTest() {
        productsRepository.save(EXPECTED_SAVED_PRODUCT);
        assertEquals(EXPECTED_SAVED_PRODUCT, productsRepository.findById(6L).get());
    }

    @Test
    public void deleteTest() {
        productsRepository.delete(2L);
        assertThrows(RuntimeException.class, () -> productsRepository.findById(2L));
    }
}