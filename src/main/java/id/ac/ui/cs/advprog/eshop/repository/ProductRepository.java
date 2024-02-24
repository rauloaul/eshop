package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class ProductRepository implements ProductRepositoryInterface {
    private List<Product> productData = new ArrayList<>();

    public void create(Product product) {
        productData.add(product);
    }

    public Product edit(String id, Product product) {
        Iterator<Product> products = findAll();
        int i = 0;
        if (products.hasNext()) {
            for (; products.hasNext(); i++) {
                Product current = products.next();
                if (current.getProductId().equals(id)) {
                    product.setProductId(current.getProductId());
                    break;
                }
            }
            return productData.set(i, product);
        }
        return null;
    }

    public boolean delete(Product product) {
        return productData.remove(product);
    }

    public Iterator<Product> findAll() {
        return productData.iterator();
    }

    public Product get(String id) {
        Iterator<Product> products = findAll();

        while (products.hasNext()) {
            Product currentProduct = products.next();
            if (currentProduct.getProductId().equals(id)) {
                return currentProduct;
            }
        }
        return null;
    }
}
