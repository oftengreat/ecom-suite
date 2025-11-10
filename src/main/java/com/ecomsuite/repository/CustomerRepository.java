package com.ecomsuite.repository;

import com.ecomsuite.common.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    
    // Example custom query methods:
    Optional<Customer> findByEmail(String email);

    boolean existsByEmail(String email);
}
