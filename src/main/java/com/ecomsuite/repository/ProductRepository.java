package com.ecomsuite.repository;

import com.ecomsuite.common.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    
    // Custom queries (optional)
    List<Product> findByNameContainingIgnoreCase(String name);

    List<Product> findBySkuIn(List<String> skus);
}
