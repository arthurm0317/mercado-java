package entities;

import java.util.Objects;

public class Product {
    private Integer id;
    private String name;
    private Double price;
    private Integer quantity;

    public Product(){

    }

    public Product(Integer id, String name, Double price, Integer quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void addProduct(int quantity){
        this.quantity=getQuantity()+quantity;
    }
    public void removeProduct(int quantity){
        this.quantity=getQuantity()-quantity;
    }

    public boolean getProductById(Product product, int id){
        return product.getId()==id;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return  "ID: " + id +
                ", nome: " + name +
                ", preço: R$" + String.format("%.2f", price)+
                ", quantidade: " + quantity;
    }
}
