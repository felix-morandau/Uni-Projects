package model;

import java.util.Objects;

/**
 * Represents a product entity.
 */
public class Product {
    private int id;
    private String name;
    private int price;
    private int stock;

    /**
     * Constructs a product with specified attributes.
     * @param name The name of the product.
     * @param price The price of the product.
     * @param stock The stock quantity of the product.
     */
    public Product(String name, int price, int stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    /**
     * Constructs an empty product object.
     */
    public Product(){}

    /**
     * Returns a string representation of the product.
     * @return A string containing the product's ID, name, and stock.
     */
    public String toString(){
        return this.id + ". " + this.name + ", lot: " + this.stock;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * @param o The reference object with which to compare.
     * @return true if this object is the same as the obj argument; false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id &&
                price == product.price &&
                stock == product.stock &&
                Objects.equals(name, product.name);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
