package com.komsoft.shopmvc.repository;

import com.komsoft.shopmvc.exception.DataBaseException;
import com.komsoft.shopmvc.model.Category;
import com.komsoft.shopmvc.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ProductRepository {
    private static final String GET_ALL_PRODUCT = "SELECT products.id AS p_id, products.name AS p_name, products.description, products.price, category.id AS c_id, category.name AS c_name FROM products JOIN category ON products.category_id=category.id";
    private static final String GET_ALL_PRODUCT_BY_Category = "SELECT products.id AS p_id, products.name AS p_name, products.description, products.price, category.id AS c_id, category.name AS c_name FROM products JOIN category ON products.category_id=category.id WHERE category.id=?";
    private PostgreSQLJDBC postgreSQLJDBC;
    private Connection connection;
    Logger logger = Logger.getLogger(UserRepository.class.getName());

    public List<Product> getAllProduct(String category) throws DataBaseException {
        postgreSQLJDBC.establishConnection();
        connection = postgreSQLJDBC.getConnection();
        List<Product> result = new ArrayList<>();
        String req = GET_ALL_PRODUCT;
        try {
            PreparedStatement statement = connection.prepareStatement( category != null ? GET_ALL_PRODUCT_BY_Category : GET_ALL_PRODUCT);
            if (category != null) {
//                req = GET_ALL_PRODUCT_BY_Category;
                statement.setInt(1, Integer.parseInt(category));
            }
            ResultSet products = statement.executeQuery();
            while (products.next()) {
                result.add(new Product().setId(products.getLong("p_id"))
                        .setName(products.getString("p_name"))
                        .setDescription(products.getString("description"))
                        .setPrice(products.getDouble("price"))
                        .setCategory(new Category().setId(products.getLong("c_id"))
                                                .setName(products.getString("c_name"))));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataBaseException(e.getMessage());
        } finally {
            if (connection != null) {
                postgreSQLJDBC.closeConnection();
/*                try {
                    postgreSQLJDBC.closeConnection();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
 */
            }
        }
        return result;
    }

    public ProductRepository(PostgreSQLJDBC postgreSQLJDBC) {
        this.postgreSQLJDBC = postgreSQLJDBC;
    }
}
