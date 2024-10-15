package dao;

import entities.Category;
import entities.enums.Categories;

import java.util.List;

public interface CategoryDao {
    void insertCategoryIfNotExists(Categories categoryName);
    Category findById(Integer id);
    List<Category> findAll();
}
