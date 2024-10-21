package dao;

import entities.Category;
import entities.enums.Categories;

import java.util.List;

public interface CategoryDao {
    void insertCategoryIfNotExists(Categories categoryName);
    Categories findCategoryByProductId(int productId);

}
