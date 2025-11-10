package com.ecomsuite.dto;

public record OrderItemDto(Long productId, String productName, int quantity, double unitPrice, double subtotal) {}
