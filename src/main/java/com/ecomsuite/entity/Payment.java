package com.ecomsuite.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor; // Optional: creates a default constructor

@Entity
@Getter 
@Setter
@NoArgsConstructor
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String method;      // e.g., CREDIT_CARD, PAYPAL, BANK_TRANSFER
    private String transactionId;
    private Double amount;
    private String currency;    // e.g., "USD", "EUR", "SEK"
    private String status;      // e.g., PENDING, SUCCESS, FAILED
    private LocalDateTime paymentDate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    public Payment(Order order, String method, Double amount, String currency) {
        this.order = order;
        this.method = method;
        this.amount = amount;
        this.currency = currency;
        this.status = "PENDING";
        this.paymentDate = LocalDateTime.now();
    }

    public void markAsSuccessful(String transactionId) {
        this.transactionId = transactionId;
        this.status = "SUCCESS";
        this.paymentDate = LocalDateTime.now();
    }

    public void markAsFailed() {
        this.status = "FAILED";
    }
}
