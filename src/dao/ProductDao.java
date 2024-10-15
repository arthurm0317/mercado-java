package dao;

import entities.Category;
import entities.Product;
import entities.enums.Categories;

import java.util.List;

public interface ProductDao {
    void insert(Product product, Categories category);

    void update(Product prod);
    void deleteById(Integer id);
    Product findById(Integer id);
    List<Product> findAll();
    List<Product> findByCategory(Category category);
}
