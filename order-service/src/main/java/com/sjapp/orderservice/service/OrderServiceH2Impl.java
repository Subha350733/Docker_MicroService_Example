package com.sjapp.orderservice.service;

import com.sjapp.orderservice.entity.Order;
import com.sjapp.orderservice.external.client.ProductService;
import com.sjapp.orderservice.model.OrderRequest;
import com.sjapp.orderservice.repository.OrderRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@Log4j2
public class OrderServiceH2Impl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductService productService;

    @Override
    public long placeOrder(OrderRequest orderRequest) {

        // Rest API call to Product-Service using Feign Client
        productService.reduceQuantity(orderRequest.getProductId() , orderRequest.getQuantity());

        log.info("Creating order with status CREATED ...");

        Order orderEntity = Order.builder()
                            .productId(orderRequest.getProductId())
                            .quantity(orderRequest.getQuantity())
                            .orderDate(Instant.now())
                            .orderStatus("CREATED")
                            .totalAmount(orderRequest.getTotalAmount())
                            .build();
        orderEntity = orderRepository.save(orderEntity);
        long orderId = orderEntity.getId();
        log.info("Order Placed with Id : "+orderId);
        return orderId;
    }
}
