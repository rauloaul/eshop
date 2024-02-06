package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class ProductRepository {
    private List<Product> productData = new ArrayList<>();

    public Product create(Product product) {
        productData.add(product);
        return product;
    }

    public Product edit(String id, Product product) {
        Iterator<Product> products = findAll();
        int i = 0;
        for (; products.hasNext(); i++) {
            Product current = products.next();
            if (current.getProductId().equals(id)) {
                product.setProductId(current.getProductId());
                break;
            }
        }
        return productData.set(i, product);
    }

    public boolean delete(Product product) {
        return productData.remove(product);
    }

    public Iterator<Product> findAll() {
        return productData.iterator();
    }
}
