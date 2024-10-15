package dao.impl;

import dao.ProductDao;
import db.DB;
import db.DbException;
import entities.Category;
import entities.Product;
import entities.enums.Categories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ProdDaoJDBC implements ProductDao {

    private final Connection connection;

    public ProdDaoJDBC(Connection connection){
        this.connection = connection;
    }



    private Category instantiateCategory(ResultSet resultSet) throws SQLException {
        Category category = new Category();
        category.setId(resultSet.getInt("id"));
        category.setCategories(Categories.valueOf(resultSet.getString("category")));
        return category;
    }

    private Product instantiateProduct(ResultSet resultSet) throws SQLException {
        Product product = new Product();
        product.setId(resultSet.getInt("id"));
        product.setName(resultSet.getString("nome"));
        product.setPrice(resultSet.getDouble("preco"));
        product.setQuantity(resultSet.getInt("quantidade"));
        return product;
    }




    @Override
    public void insert(Product product, Categories category) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(
                    "INSERT INTO produtos (name, price, quantity, category_name) VALUES (?, ?, ?, ?)"
            );
            statement.setString(1, product.getName());
            statement.setDouble(2, product.getPrice());
            statement.setInt(3, product.getQuantity());
            statement.setString(4, category.name());

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("adicionado com sucesso");
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(statement);
        }
    }

    @Override
    public void update(Product prod) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Product findById(Integer id) {
        return null;
    }

    @Override
    public List<Product> findAll() {
        return List.of();
    }

    @Override
    public List<Product> findByCategory(Category category) {
        return List.of();
    }
}
