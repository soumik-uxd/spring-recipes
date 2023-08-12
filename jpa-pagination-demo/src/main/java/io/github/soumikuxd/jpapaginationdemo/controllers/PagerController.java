package io.github.soumikuxd.jpapaginationdemo.controllers;

import io.github.soumikuxd.jpapaginationdemo.models.APIResponse;
import io.github.soumikuxd.jpapaginationdemo.models.Product;
import io.github.soumikuxd.jpapaginationdemo.services.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/data/products")
public class PagerController {
    private static final Logger logger = LoggerFactory.getLogger(PagerController.class);

    @Autowired
    private ProductService productService;

    @GetMapping
    private APIResponse<List<Product>> getAllProducts() {
        final List<Product> products = productService.findAllProducts();
        return new APIResponse<>(products.size(), products);
    }

    @GetMapping("/{field}")
    private APIResponse<List<Product>> getProductsWithSort(@PathVariable String field) {
        List<Product> products = productService.findProductsWithSorting(field);
        return new APIResponse<>(products.size(), products);
    }

    @GetMapping("/pagination/{pageOffset}/{pageSize}")
    private APIResponse<Page<Product>> getProductsWithPagination(@PathVariable int pageOffset, @PathVariable int pageSize) {
        Page<Product> products = productService.findProductsWithPagination(pageOffset, pageSize);
        return new APIResponse<>(products.getSize(), products);
    }

    @GetMapping("/pagination/{pageOffset}/{pageSize}/{field}")
    private APIResponse<Page<Product>> getProductsWithPaginationAndSort(@PathVariable int pageOffset, @PathVariable int pageSize,@PathVariable String field) {
        Page<Product> products = productService.findProductsWithPaginationAndSorting(pageOffset, pageSize, field);
        return new APIResponse<>(products.getSize(), products);
    }
}
