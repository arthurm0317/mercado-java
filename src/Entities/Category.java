package Entities;

import Entities.enums.Categories;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Category {
    private Categories categories;
    private List<Product> products;

    public Category(Categories categories) {
        this.categories = categories;
        this.products = new ArrayList<>();
    }
    public void addProducts(Set<Product> product){
        products.addAll(product);
    }
    public List<Product> getProducts() {
        return products;
    }

    @Override
    public String toString() {
        return "Categoria: " + categories +
                ", Produtos:" + products;
    }
}
