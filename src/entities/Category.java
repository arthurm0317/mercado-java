package entities;

import entities.enums.Categories;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Category {
    private Integer id;
    private Categories categories;
    private List<Product> products;

    public Category(){

    }

    public Category(Categories categories) {
        this.categories = categories;
    }

    public Category(Integer id, Categories categories) {
        this.id = id;
        this.categories = categories;
    }

    public void addProducts(Set<Product> product){
        products.addAll(product);
    }

    public List<Product> getProducts() {
        return products;
    }

    public Categories getCategories() {
        return categories;
    }

    public void setCategories(Categories categories) {
        this.categories = categories;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Categoria: " + categories +
                ", Produtos:" + products;
    }
}
