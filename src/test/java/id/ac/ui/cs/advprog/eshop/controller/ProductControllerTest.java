package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.isA;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = ProductController.class)
@AutoConfigureMockMvc(addFilters = false)
public class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductServiceImpl service;

    @Test
    void testCreateProductPage() throws Exception {
        mockMvc.perform(get("/product/create"))
                .andExpect(status().isOk())
                .andExpect(view().name("createProduct"))
                .andExpect(model().attributeExists("product"))
                .andExpect(model().attribute("product", isA(Product.class)));
    }

    @Test
    void testCreateProductPost() throws Exception {

        Product product = Mockito.mock(Product.class);

        mockMvc.perform(post("/product/create").flashAttr("product", product))
                .andExpect(view().name("redirect:list"));

        verify(service, times(1)).create(product);
    }

    @Test
    void testEditProductPage() throws Exception {
        Product product = Mockito.mock(Product.class);
        String productId = String.valueOf(UUID.randomUUID());

        when(service.get(productId)).thenReturn(product);

        mockMvc.perform(get("/product/edit/"+productId))
                .andExpect(view().name("editProduct"))
                .andExpect(model().attributeExists("product"))
                .andExpect(model().attribute("product", isA(Product.class)))
                .andExpect(model().attribute("product", is(product)));
    }

    @Test
    void testEditProductPost() throws Exception {

        Product product = Mockito.mock(Product.class);
        String productId = String.valueOf(UUID.randomUUID());

        mockMvc.perform(post("/product/edit/"+productId).flashAttr("product", product))
                .andExpect(view().name("redirect:/product/list"));

        verify(service).edit(productId, product);
    }

    @Test
    void testDeleteProductPost() throws Exception {

        String productId = String.valueOf(UUID.randomUUID());

        mockMvc.perform(post("/product/delete/"+productId))
                .andExpect(view().name("redirect:/product/list"));

        verify(service).delete(productId);
    }

    @Test
    void testProductListPage() throws Exception {

        Product product1 = Mockito.mock(Product.class);
        Product product2 = Mockito.mock(Product.class);

        List<Product> productList = Arrays.asList(product1, product2);

        when(service.findAll()).thenReturn(productList);

        mockMvc.perform(get("/product/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("productList"))
                .andExpect(model().attribute("products", productList));
    }
}
