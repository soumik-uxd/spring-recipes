package io.github.soumikuxd.jpapaginationdemo.repositories;

import io.github.soumikuxd.jpapaginationdemo.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
