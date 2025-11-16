package com.ecomsuite.entity;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor; // Optional: creates a default constructor

@Entity
@Getter 
@Setter
@NoArgsConstructor
@Table(name = "order_items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;
    private Double unitPrice;
    private int quantity;

    public OrderItem(Order order, Product product, int quantity, Double price) {
    }


    public Double getSubtotal() {
        return quantity * getProduct().getPrice();
    }

    public void setSubtotal(double v) {
    }
}

