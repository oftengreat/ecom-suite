package com.ecomsuite.dto;

import java.util.List;

public record CreateOrderRequest(
        Long customerId,
        List<CreateOrderItemRequest> items,
        String paymentMethod
) {}

