package com.ecomsuite.dto;

import java.time.LocalDateTime;
import java.util.List;

public record OrderDto(
    Long id,
    Long customerId,
    String customerName,
    LocalDateTime orderDate,
    String status,
    Double totalAmount,
    List<OrderItemDto> items
) {}
