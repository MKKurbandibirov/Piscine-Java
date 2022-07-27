package edu.school21.repositories;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

public class EmbeddedDataSourceTest {

    private static EmbeddedDatabase db;

    @BeforeEach
    private void init() {
        db = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.HSQL)
                .addScript("schema.sql")
                .addScript("data.sql")
                .build();
    }

    @Test
    public void getConnection() throws SQLException {
        assertNotNull(db.getConnection());
    }

    @AfterAll
    public static void after() {
        if (db != null)
            db.shutdown();
    }
}