package edu.school21.repositories;

import org.junit.jupiter.api.*;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import javax.sql.*;
import java.sql.Connection;
import java.sql.SQLException;

public class EmbeddedDataSourceTest {
    private DataSource dataSource;
    @BeforeEach
    void init() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        builder.setType(EmbeddedDatabaseType.HSQL);
        builder.addScript("schema.sql").addScript("data.sql");
        dataSource = builder.build();
    }

    @Test
    void getConnection() throws SQLException {
        Connection connection = dataSource.getConnection();
        Assertions.assertNotNull(connection);
    }
}
