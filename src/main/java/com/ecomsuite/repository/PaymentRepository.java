package com.ecomsuite.repository;

import com.ecomsuite.common.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    
    List<Payment> findByStatus(String status);

    List<Payment> findByOrderId(Long orderId);
}
