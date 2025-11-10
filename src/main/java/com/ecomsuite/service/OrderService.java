package com.ecomsuite.service;

import com.ecomsuite.dto.*;
import com.ecomsuite.common.model.*;
import com.ecomsuite.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepo;
    private final CustomerRepository customerRepo;
    private final ProductRepository productRepo;
    private final PaymentRepository paymentRepo;

    public OrderService(OrderRepository orderRepo,
                        CustomerRepository customerRepo,
                        ProductRepository productRepo,
                        PaymentRepository paymentRepo) {
        this.orderRepo = orderRepo;
        this.customerRepo = customerRepo;
        this.productRepo = productRepo;
        this.paymentRepo = paymentRepo;
    }

    @Transactional
    public OrderDto createOrder(CreateOrderRequest request) {
        Customer customer = customerRepo.findById(request.customerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        Order order = new Order();
        order.setCustomer(customer);
        order.setOrderDate(LocalDateTime.now());
        order.setStatus("NEW");

        List<OrderItem> items = request.items().stream().map(i -> {
            Product product = productRepo.findById(i.productId())
                    .orElseThrow(() -> new RuntimeException("Product not found: " + i.productId()));

            OrderItem item = new OrderItem();
            item.setProduct(product);
            item.setQuantity(i.quantity());
            item.setUnitPrice(product.getPrice());
            item.setSubtotal(product.getPrice() * i.quantity());
            item.setOrder(order);
            return item;
        }).toList();

        double total = items.stream().mapToDouble(OrderItem::getSubtotal).sum();
        order.setItems(items);
        //order.setTotalAmount(total);

        Payment payment = new Payment();
        payment.setMethod(request.paymentMethod());
        payment.setAmount(total);
        payment.setStatus("PENDING");
        payment.setPaymentDate(LocalDateTime.now());
        payment.setOrder(order);

        order.setPayment(payment);
        orderRepo.save(order);
        paymentRepo.save(payment);

        return toDto(order);
    }

    public List<OrderDto> findAll() {
        return orderRepo.findAll().stream()
                .map(this::toDto)
                .toList();
    }

    public OrderDto findById(Long id) {
        return orderRepo.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    private OrderDto toDto(Order order) {
        var customer = order.getCustomer();
        var items = order.getItems().stream()
                .map(item -> new OrderItemDto(
                        item.getProduct().getId(),
                        item.getProduct().getName(),
                        item.getQuantity(),
                        item.getUnitPrice(),
                        item.getSubtotal()
                ))
                .toList();

        return new OrderDto(
                order.getId(),
                customer.getId(),
                customer.getFirstName() + " " + customer.getLastName(),
                order.getOrderDate(),
                order.getStatus(),
                order.getTotalAmount(),
                items
        );
    }
}
