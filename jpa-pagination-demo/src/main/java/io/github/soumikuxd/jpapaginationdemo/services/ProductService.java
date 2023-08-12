package io.github.soumikuxd.jpapaginationdemo.services;

import io.github.soumikuxd.jpapaginationdemo.models.Product;
import io.github.soumikuxd.jpapaginationdemo.repositories.ProductRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @PostConstruct
    private void initDB() {
        List<Product> products = IntStream.rangeClosed(1, 200)
            .mapToObj(i -> new Product("product" + i, new Random().nextInt(100), new Random().nextInt(50000)))
            .collect(Collectors.toList());
        productRepository.saveAll(products);
    }

    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> findProductsWithSorting(String field){
        return  productRepository.findAll(Sort.by(Sort.Direction.ASC,field));
    }

    public Page<Product> findProductsWithPagination(int pageOffset,int pageSize){
        return productRepository.findAll(PageRequest.of(pageOffset, pageSize));
    }

    public Page<Product> findProductsWithPaginationAndSorting(int pageOffset, int pageSize, String field){
        return productRepository.findAll(PageRequest.of(pageOffset, pageSize).withSort(Sort.by(field)));
    }
}
