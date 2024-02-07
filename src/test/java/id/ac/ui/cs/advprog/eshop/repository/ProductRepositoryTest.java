package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ProductRepositoryTest {

    @InjectMocks
    ProductRepository productRepository;
    @BeforeEach
    void setUp() {
    }
    @Test
    void testCreateAndFind() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testFindAllIfEmpty() {
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }
    @Test
    void testFindAllIfMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product2.setProductName("Sampo Cap Bambang");
        product2.setProductQuantity(100);
        productRepository.create(product2);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product1.getProductId(), savedProduct.getProductId());
        savedProduct = productIterator.next();
        assertEquals(product2.getProductId(), savedProduct.getProductId());
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testDeleteProduct() {
        // Set
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Gibson Guitars");
        product1.setProductQuantity(20);
        productRepository.create(product1);

        // Perform Delete
        productRepository.delete(product1);

        // test
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testDeleteIfMoreThanOneProduct() {
        // Set
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Gibson Guitars");
        product1.setProductQuantity(20);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("cba5ef02-081c-44c3-a3c8-a49bb37ef505");
        product2.setProductName("Efnote Drum");
        product2.setProductQuantity(15);
        productRepository.create(product2);

        // Perform Delete
        productRepository.delete(product1);

        // Test
        Iterator<Product> productIterator = productRepository.findAll();
        Product current = productIterator.next();
        assertEquals(current.getProductId(), product2.getProductId());
        assertNotEquals(current.getProductId(), product1.getProductId());
    }

    @Test
    void testEditProduct() {
        // Set
        Product product = new Product();
        product.setProductId("cba5ef02-081c-44c3-a3c8-a49bb37ef505");
        product.setProductName("Ferrari");
        product.setProductQuantity(15);
        productRepository.create(product);

        // Perform Edit
        product.setProductName("Mercedes");
        product.setProductQuantity(10);
        productRepository.edit("cba5ef02-081c-44c3-a3c8-a49bb37ef505", product);

        // Test
        Iterator<Product> productIterator = productRepository.findAll();
        Product edited = null;
        while (productIterator.hasNext()) {
            Product current = productIterator.next();
            if (current.getProductId().equals(product.getProductId())) {
                edited = current;
                break;
            }
        }
        assertNotNull(edited);
        assertEquals(edited.getProductName(), "Mercedes");
        assertEquals(edited.getProductQuantity(), 10);
    }
}
