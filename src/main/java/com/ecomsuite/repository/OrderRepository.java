package com.ecomsuite.repository;

import com.ecomsuite.common.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {}
