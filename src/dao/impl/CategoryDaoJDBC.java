package dao.impl;

import dao.CategoryDao;
import db.DB;
import db.DbException;
import entities.Category;
import entities.enums.Categories;

import java.sql.*;
import java.util.List;

public class CategoryDaoJDBC implements CategoryDao {

    private final Connection connection;

    public CategoryDaoJDBC(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insertCategoryIfNotExists(Categories categoryName) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            //
            statement = connection.prepareStatement("SELECT * FROM categories WHERE name = ?");
            statement.setString(1, categoryName.name());
            resultSet = statement.executeQuery();

            if (!resultSet.next()) {
                statement = connection.prepareStatement("INSERT INTO categories (name) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, categoryName.name());

                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeResultSet(resultSet);
            DB.closeStatement(statement);
        }
    }


    @Override
    public Category findById(Integer id) {
        return null;
    }

    @Override
    public List<Category> findAll() {
        return List.of();
    }
}
