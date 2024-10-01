package Entities;

import Entities.enums.Categorys;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Category {
    private Categorys categorys;
    private List<Product> products;

    public Category(Categorys categorys) {
        this.categorys = categorys;
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
        return "Categoria: " + categorys +
                ", Produtos:" + products;
    }
}
