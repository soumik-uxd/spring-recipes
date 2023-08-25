package io.github.soumikuxd.jpapaginationdemo.controllers;

import io.github.soumikuxd.jpapaginationdemo.models.Product;
import io.github.soumikuxd.jpapaginationdemo.repositories.ProductRepository;
import io.github.soumikuxd.jpapaginationdemo.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = PagerController.class)
public class PagerControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductRepository repository;

    @MockBean
    private ProductService productService;

    private List<Product> products;

    @BeforeEach
    void setUp() {
        final Product product1 = new Product("product1", 10, 100);
        final Product product2 = new Product("product2", 5, 200);
        products = Arrays.asList(product1, product2);
    }

    /*@Test
    void getAllProducts() throws Exception {
        when(repository.findAll()).thenReturn(products);
        final ResultActions result = mockMvc.perform(get("/data/products"));
        result.andExpect(status().isOk()).andDo(print());
        verify(repository, times(1)).findAll();
    }*/
}
