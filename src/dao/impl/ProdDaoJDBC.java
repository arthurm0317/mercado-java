package dao.impl;

import dao.CategoryDao;
import dao.DaoFactory;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProdDaoJDBC implements ProductDao {

    private final Connection connection;

    public ProdDaoJDBC(Connection connection){
        this.connection = connection;
    }



    private Category instantiateCategory(ResultSet resultSet) throws SQLException {
        Category category = new Category();
        category.setId(resultSet.getInt("id"));
        category.setCategories(Categories.valueOf(resultSet.getString("category_name")));
        return category;
    }

    private Product instantiateProduct(ResultSet resultSet) throws SQLException {
        Product product = new Product();
        product.setId(resultSet.getInt("id"));
        product.setName(resultSet.getString("name"));
        product.setPrice(resultSet.getDouble("price"));
        product.setQuantity(resultSet.getInt("quantity"));
        return product;
    }

    @Override
    public void insert(Product product, Categories category) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(
                    "INSERT INTO produtos (id, name, price, quantity, category_name) VALUES (?, ?, ?, ?, ?)"
            );
            statement.setInt(1, product.getId());
            statement.setString(2, product.getName());
            statement.setDouble(3, product.getPrice());
            statement.setInt(4, product.getQuantity());
            statement.setString(5, category.name());

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Adicionado com sucesso!");
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
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("DELETE FROM produtos WHERE id = ?");
            statement.setInt(1, id);
            statement.executeUpdate();
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }finally {
            DB.closeStatement(statement);
        }

    }

    @Override
    public Product findById(Integer id) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement("SELECT FROM produtos WHERE id=?");
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if(resultSet.next()){
                return instantiateProduct(resultSet);
            }
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }finally {
            DB.closeStatement(statement);
            DB.closeResultSet(resultSet);
        }
        return null;
    }

    @Override
    public List<Product> findAll() {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement("SELECT produtos.*, " +
                    "categories.name as catgName FROM produtos INNER JOIN " +
                    "categories ON produtos.category_name = categories.name " +
                    "ORDER BY produtos.name");

            resultSet = statement.executeQuery();

            List<Product> products = new ArrayList<>();
            Map<String, Category> map = new HashMap<>();
            while(resultSet.next()){
                String categoryName = resultSet.getString("category_name");

                Category category = map.get(categoryName);
                if (category == null) {
                    category = instantiateCategory(resultSet);
                    map.put(categoryName, category);
                }

                Product product = instantiateProduct(resultSet);
                products.add(product);
            }
            return products;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(statement);
            DB.closeResultSet(resultSet);
        }
    }
    public void printProductsByCategory() {
        List<Product> products = findAll();

        Map<Categories, List<Product>> categoryMap = new HashMap<>();
        CategoryDao categoryDaoJDBC = DaoFactory.createCatDao();


        for (Product product : products) {
            Categories category = categoryDaoJDBC.findCategoryByProductId(product.getId());


            categoryMap.putIfAbsent(category, new ArrayList<>());
            categoryMap.get(category).add(product);
        }


        for (Map.Entry<Categories, List<Product>> entry : categoryMap.entrySet()) {
            System.out.println("Categoria: " + entry.getKey());
            for (Product product : entry.getValue()) {
                System.out.println(product);
            }
            System.out.println();
        }
    }



    @Override
    public List<Product> findByCategory(Category category) {
        return List.of();
    }
}
