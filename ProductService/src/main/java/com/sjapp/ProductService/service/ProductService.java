package com.sjapp.ProductService.service;

import com.sjapp.ProductService.model.ProductRequest;
import com.sjapp.ProductService.model.ProductResponse;

public interface ProductService {
    long addProduct(ProductRequest productRequest);

    ProductResponse getProductById(long productId);

    void reduceQuantity(long productId, long quantity);

}
