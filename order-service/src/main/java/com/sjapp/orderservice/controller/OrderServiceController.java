package com.sjapp.orderservice.controller;

import com.sjapp.orderservice.model.OrderRequest;
import com.sjapp.orderservice.model.OrderResponse;
import com.sjapp.orderservice.service.OrderService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/order")
@RestController
@Log4j2
public class OrderServiceController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<Long> placeOrder(@RequestBody OrderRequest orderRequest){
        log.info("Order received as: "+orderRequest);
        long orderId = orderService.placeOrder(orderRequest);
        log.info("Order Placed with id : "+orderId);
        return new ResponseEntity<>(orderId,HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable("id") long orderId){
        return null ;
    }

}
