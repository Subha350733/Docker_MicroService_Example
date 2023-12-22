package com.sjapp.orderservice.service;

import com.sjapp.orderservice.model.OrderRequest;

public interface OrderService {
    long placeOrder(OrderRequest orderRequest);
}
