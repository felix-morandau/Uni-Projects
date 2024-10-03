package bll;

import model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductBLLTest {
    private ProductBLL productBLL;

    @BeforeEach
    void setUp() {
        productBLL = new ProductBLL();
    }

    @Test
    void testInsertProduct() {
        List<Product> initialProducts = productBLL.getProducts();
        int initialSize = initialProducts.size();

        Product newProduct = new Product("Laptop", 1000, 10);

        productBLL.insertProduct(newProduct);

        List<Product> updatedProducts = productBLL.getProducts();
        int updatedSize = updatedProducts.size();

        assertEquals(initialSize + 1, updatedSize);
        assertEquals(newProduct.getName(), updatedProducts.get(updatedSize - 1).getName());

        productBLL.deleteProduct(newProduct);
    }

    @Test
    void testUpdateProduct() {
        Product newProduct = new Product("Laptop", 1000, 10);

        productBLL.insertProduct(newProduct);

        List<Product> updatedProducts = productBLL.getProducts();

        Product productToUpdate = updatedProducts.get(updatedProducts.size() - 1);
        productToUpdate.setName("Desktop");
        productToUpdate.setPrice(1200);
        productToUpdate.setStock(8);

        productBLL.updateProduct(productToUpdate);

        List<Product> updatedProductsAfterUpdate = productBLL.getProducts();

        assertEquals("Desktop", updatedProductsAfterUpdate.get(updatedProductsAfterUpdate.size() - 1).getName());
        assertEquals(1200, updatedProductsAfterUpdate.get(updatedProductsAfterUpdate.size() - 1).getPrice());
        assertEquals(8, updatedProductsAfterUpdate.get(updatedProductsAfterUpdate.size() - 1).getStock());

        productBLL.deleteProduct(productToUpdate);
    }

    @Test
    void testDeleteProduct() {
        Product newProduct = new Product("Laptop", 1000, 10);

        productBLL.insertProduct(newProduct);

        List<Product> updatedProducts = productBLL.getProducts();
        int initialSize = updatedProducts.size();

        productBLL.deleteProduct(updatedProducts.get(initialSize - 1));

        List<Product> updatedProductsAfterDeletion = productBLL.getProducts();
        int updatedSize = updatedProductsAfterDeletion.size();

        assertEquals(initialSize - 1, updatedSize);
    }

    @Test
    void testGetProducts() {
        List<Product> products = productBLL.getProducts();

        assertEquals(false, products.isEmpty());
    }
}
