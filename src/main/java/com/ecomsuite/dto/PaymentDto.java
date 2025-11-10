package com.ecomsuite.dto;

import java.time.LocalDateTime;

public record PaymentDto(Long id, String method, String status, Double amount, LocalDateTime paymentDate) {}
