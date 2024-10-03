package bll;

import dao.Dao;
import model.Product;

import java.util.List;

/**
 * Business logic layer (BLL) class for managing products.
 * This class provides methods to update, insert, delete, and retrieve products.
 */
public class ProductBLL {
    private List<Product> products;
    private Dao<Product> productDao;

    /**
     * Constructs a new ProductBLL object and initializes the DAO for Product objects.
     * Retrieves the list of products from the database.
     */
    public ProductBLL() {
        productDao = new Dao<>(Product.class);
        this.products = productDao.retrieveObjects();
    }

    /**
     * Updates the information of a product.
     * @param product The product to be updated.
     */
    public void updateProduct(Product product) {
        productDao.updateObject(product);
        products = productDao.retrieveObjects();
    }

    /**
     * Inserts a new product into the database.
     * @param product The product to be inserted.
     */
    public void insertProduct(Product product) {
        productDao.insertObject(product);
        products = productDao.retrieveObjects();
    }

    /**
     * Deletes a product from the database.
     * @param product The product to be deleted.
     */
    public void deleteProduct(Product product) {
        productDao.deleteObject(product);
        products = productDao.retrieveObjects();
    }

    /**
     * Retrieves the list of products.
     * @return The list of products.
     */
    public List<Product> getProducts() {
        return products;
    }
}
