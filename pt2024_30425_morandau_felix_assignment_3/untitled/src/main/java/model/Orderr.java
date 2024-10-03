package model;

/**
 * Represents an order entity.
 * Has the name like that because in Postgres "order" is a keyword
 * so by using reflection this class can not be named "order"
 */
public class Orderr {
    private int id;
    private int client_id;
    private int product_id;
    private int quantity;

    /**
     * Constructs an order with specified attributes.
     * @param client_id The ID of the client associated with the order.
     * @param product_id The ID of the product associated with the order.
     * @param quantity The quantity of the product in the order.
     */
    public Orderr(int client_id, int product_id, int quantity) {
        this.client_id = client_id;
        this.product_id = product_id;
        this.quantity = quantity;
    }

    /**
     * Constructs an empty order object.
     */
    public Orderr(){}

    // Getters and setters are not documented here for brevity

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClient_id() {
        return client_id;
    }

    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
