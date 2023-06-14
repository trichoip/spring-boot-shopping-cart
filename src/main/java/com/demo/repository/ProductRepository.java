package com.demo.repository;

import com.demo.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    boolean existsBySku(String sku);
    Optional<Product> findBySku(String sku);
}
