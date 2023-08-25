package io.github.soumikuxd.jpapaginationdemo.models;

import io.github.soumikuxd.jpapaginationdemo.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductRepositoryTests {
    @Autowired
    private ProductRepository repository;
    private Product product;

    @BeforeEach()
    void setUp() {
        Product product = new Product("TestProduct", 10, 100);
        repository.save(product);
    }

    @Test
    void addProduct() {
        final List<Product> products = repository.findAll();
        assertEquals(products.toArray().length, 1);
        final Product savedProduct = products.get(0);
        assertEquals(product.getName(), savedProduct.getName());
        assertEquals(product.getQuantity(), savedProduct.getQuantity());
        assertEquals(product.getPrice(), savedProduct.getPrice());
    }

    @Test
    void findById() {
        final Product result = repository.findById(product.getId()).orElse(new Product());
        assertEquals(product.getName(), result.getName());
        assertEquals(product.getQuantity(), result.getQuantity());
        assertEquals(product.getPrice(), result.getPrice());
    }
}
