package com.ecomsuite.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor; // Optional: creates a default constructor

@Entity
@Getter 
@Setter
@NoArgsConstructor
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime orderDate;
    private String status; // e.g. NEW, PROCESSING, SHIPPED, DELIVERED, CANCELLED

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> items = new ArrayList<>();

    @Embedded
    private Address shippingAddress;

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private Payment payment;

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private Shipment shipment;


    public Order(Customer customer, Address shippingAddress) {
        this.customer = customer;
        this.shippingAddress = shippingAddress;
        this.orderDate = LocalDateTime.now();
        this.status = "NEW";
    }

    public void addItem(Product product, int quantity) {
        OrderItem item = new OrderItem(this, product, quantity, product.getPrice());
        items.add(item);
    }

    public Double getTotalAmount() {
        return items.stream()
                    .mapToDouble(OrderItem::getSubtotal)
                    .sum();
    }
}
