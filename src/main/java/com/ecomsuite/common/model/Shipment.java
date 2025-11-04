package com.ecomsuite.common.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor; // Optional: creates a default constructor

@Entity
@Getter 
@Setter
@NoArgsConstructor
@Table(name = "shipments")
public class Shipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String carrier;       // e.g., DHL, UPS, FedEx
    private String trackingNumber;
    private String status;        // e.g., PENDING, SHIPPED, DELIVERED
    private LocalDateTime shippedDate;
    private LocalDateTime deliveredDate;

    @Embedded
    private Address deliveryAddress;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    public Shipment(Order order, String carrier, Address deliveryAddress) {
        this.order = order;
        this.carrier = carrier;
        this.deliveryAddress = deliveryAddress;
        this.status = "PENDING";
    }

    public void markAsShipped(String trackingNumber) {
        this.trackingNumber = trackingNumber;
        this.status = "SHIPPED";
        this.shippedDate = LocalDateTime.now();
    }

    public void markAsDelivered() {
        this.status = "DELIVERED";
        this.deliveredDate = LocalDateTime.now();
    }

    // Getters/Setters omitted
}

