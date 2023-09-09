package edu.school21.repositories;

import edu.school21.models.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


public class ProductRepositoryJdbcImplTest {
    ProductsRepository productsRepository;
    EmbeddedDatabase dataBase;
    final List<Product> EXPECTED_FIND_ALL_PRODUCTS = Arrays.asList(
            new Product(1L, "MacBook", 100000),
            new Product(2L, "iPhone", 70000),
            new Product(3L, "iMac", 325000),
            new Product(4L, "iPad", 40000),
            new Product(5L, "iPod", 15000),
            new Product(6L, "iMouse", 10000));
    final Product EXPECTED_FIND_BY_ID_PRODUCT = new Product(1L, "MacBook", 100000);
    final Product EXPECTED_UPDATED_PRODUCT = new Product(2L, "iPhone", 50000);
    final Product EXPECTED_SAVED_PRODUCT = new Product(7L, "AirTag", 3000);

    @BeforeEach
    void init() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        builder.setType(EmbeddedDatabaseType.HSQL);
        builder.addScript("schema.sql").addScript("data.sql");
        dataBase = builder.build();
        productsRepository = new ProductsRepositoryJdbcImpl(dataBase);
    }

//    @AfterEach
//    void shutdownBase() {
//        dataBase.shutdown();
//    }

    @Test
    void findByIdTest() throws SQLException {
        Assertions.assertEquals(productsRepository.findById(1L).get(), EXPECTED_FIND_BY_ID_PRODUCT);
    }

    @Test
    void findAllTest() throws SQLException {
        Assertions.assertEquals(EXPECTED_FIND_ALL_PRODUCTS, productsRepository.findAll());
    }

    @Test
    void updateTest() throws SQLException {
        productsRepository.update(EXPECTED_UPDATED_PRODUCT);
        Assertions.assertEquals(EXPECTED_UPDATED_PRODUCT, productsRepository.findById(2L).get());
    }


    @Test
    void saveTest() throws SQLException {
        productsRepository.save(EXPECTED_SAVED_PRODUCT);
        Assertions.assertEquals(productsRepository.findById(7L).get(), EXPECTED_SAVED_PRODUCT);
    }

    @Test
    void deleteTest() throws SQLException {
        productsRepository.delete(6L);
        Assertions.assertEquals(productsRepository.findById(6L), Optional.empty());
    }
}


