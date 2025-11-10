package com.ecomsuite.dto;

import java.time.LocalDateTime;

public record ShipmentDto(Long id, String carrier, String trackingNumber, String status, LocalDateTime shippedDate) {}
