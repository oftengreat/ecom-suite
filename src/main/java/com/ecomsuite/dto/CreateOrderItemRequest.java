package com.ecomsuite.dto;

public record CreateOrderItemRequest(
        Long productId,
        int quantity
) {}
