package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {
    @InjectMocks
    private ProductServiceImpl service;

    @Mock
    private ProductRepository productRepository;

    Product product;

    @BeforeEach
    void setUp() {
    }
    @Test
    void testCreate() {
        Product product = new Product();

        doNothing().when(productRepository).create(product);

        service.create(product);
        verify(productRepository).create(product);

        assertDoesNotThrow(() -> UUID.fromString(product.getProductId()));
    }

    @Test
    void testFindAll() {
        Product product1 = Mockito.mock(Product.class);
        Product product2 = Mockito.mock(Product.class);

        List<Product> productList = new ArrayList<>();
        productList.add(product1);
        productList.add(product2);

        when(productRepository.findAll()).thenReturn(productList.iterator());

        List<Product> returnProduct = service.findAll();
        verify(productRepository).findAll();

        assertEquals(productList, returnProduct);
    }

    @Test
    void testEdit() {
        Product product = Mockito.mock(Product.class);
        Product editedProduct = Mockito.mock(Product.class);

        when(productRepository.edit(product.getProductId(), editedProduct)).thenReturn(editedProduct);

        Product returnValue = service.edit(product.getProductId(), editedProduct);
        verify(productRepository).edit(product.getProductId(), editedProduct);

        assertNotNull(returnValue);
    }

    @Test
    void testDelete() {
        product = new Product();
        product.setProductName("dummyName");
        product.setProductQuantity(10);
        product.setProductId("dummyId");

        List<Product> productList = new ArrayList<>();
        productList.add(product);

        when(productRepository.findAll()).thenReturn(productList.iterator());
        when(productRepository.delete(product)).thenAnswer(invocation -> productList.remove(product));

        boolean returnValue = service.delete(product.getProductId());
        verify(productRepository).delete(product);

        assertTrue(returnValue);
    }

    @Test
    void testDeleteNegative() {
        product = new Product();
        product.setProductName("dummyName");
        product.setProductQuantity(10);
        product.setProductId("dummyId");

        List<Product> productList = new ArrayList<>();
        productList.add(product);

        when(productRepository.findAll()).thenReturn(productList.iterator());

        boolean returnValue = service.delete(null);

        assertFalse(returnValue);
    }
}
