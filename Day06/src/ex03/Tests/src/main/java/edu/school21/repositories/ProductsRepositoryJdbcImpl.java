package edu.school21.repositories;

import edu.school21.models.Product;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductsRepositoryJdbcImpl implements ProductsRepository {

    private final DataSource ds;
    public ProductsRepositoryJdbcImpl(DataSource ds) {
        this.ds = ds;
    }

    @Override
    public List<Product> findAll() throws SQLException {
        List<Product> list = new ArrayList<>();
        try (Connection connection = ds.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM product;");
            while (resultSet.next()) {
                list.add(new Product(resultSet.getLong(1),
                        resultSet.getString(2),
                        resultSet.getInt(3)));
            }
        }
        return list;
    }

    @Override
    public Optional<Product> findById(Long id) throws SQLException {
        Connection connection = ds.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM product WHERE identifier = ? ;");
        statement.setLong(1, id);
        ResultSet resultSet = statement.executeQuery();
        if (!resultSet.next()) { return Optional.empty(); }
        Product product = new Product(resultSet.getLong(1),
                resultSet.getString(2),
                resultSet.getInt(3));
        statement.close();
        connection.close();
        return Optional.of(product);
    }

    @Override
    public void update(Product product) throws SQLException {
        String query = "UPDATE product SET name = ?, price = ? WHERE identifier = ?;";
        try (Connection connection = ds.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, product.getName());
            statement.setInt(2, product.getPrice());
            statement.setLong(3, product.getId());
            statement.execute();
        }
    }

    @Override
    public void save(Product product) throws SQLException {
        String query = "INSERT INTO product VALUES (?, ?, ?);";
        try (Connection connection = ds.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, product.getId());
            statement.setString(2, product.getName());
            statement.setInt(3, product.getPrice());
            statement.execute();
        }
    }

    @Override
    public void delete(Long id) throws SQLException {
        String query = "DELETE FROM product WHERE identifier = ?;";
        try (Connection connection = ds.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            statement.execute();
        }
    }
}
