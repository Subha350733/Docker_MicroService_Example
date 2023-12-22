package com.sjapp.ProductService.service;

import com.sjapp.ProductService.entity.Product;
import com.sjapp.ProductService.error.ProductServiceCustomException;
import com.sjapp.ProductService.model.ProductRequest;
import com.sjapp.ProductService.model.ProductResponse;
import com.sjapp.ProductService.repository.ProductRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class ProductServiceH2Impl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public long addProduct(ProductRequest productRequest) {

        log.info("Adding Product ..");

        Product product = Product.builder()
                            .productName(productRequest.getName())
                            .price(productRequest.getPrice())
                            .quantity(productRequest.getQuantity())
                            .build();

        productRepository.save(product);

        log.info("Product Created !!");

        return product.getProductId();
    }

    @Override
    public ProductResponse getProductById(long productId) {

        Product product = productRepository.findById(productId)
                .orElseThrow( () -> new ProductServiceCustomException("Product Not found with id : "+productId , "PRODUCT_NOT_FOUND"));

        log.info("Fetched Product with id : "+productId);

        ProductResponse productResponse = ProductResponse.builder()
                                            .productId(product.getProductId())
                                            .productName(product.getProductName())
                                            .price(product.getPrice())
                                            .quantity(product.getQuantity())
                                            .build();
        return productResponse;
    }

    @Override
    public void reduceQuantity(long productId, long quantity) {

        Product product = productRepository.findById(productId)
                .orElseThrow( () -> new ProductServiceCustomException("Product Not found with id : "+productId , "PRODUCT_NOT_FOUND"));

        if(product.getQuantity() < quantity){
            throw new ProductServiceCustomException("Product does not have sufficient quantity" , "INSUFFICIENT_QUANTITY");
        }

        product.setQuantity(product.getQuantity() - quantity);
        productRepository.save(product);

        log.info("Product Quantity Updated for : "+productId);
    }
}
