package dao;

import entities.Category;
import entities.Product;
import entities.enums.Categories;

import java.util.List;

public interface ProductDao {
    void insert(Product product, Categories category);

    void updateName(Integer id, String name);
    void updatePrice(Integer id, Double price);
    void deleteById(Integer id);
    Product findById(Integer id);
    List<Product> findAll();
    void printProductsByCategory();
    void updateQuantity(Integer id, Integer quantity);
}
