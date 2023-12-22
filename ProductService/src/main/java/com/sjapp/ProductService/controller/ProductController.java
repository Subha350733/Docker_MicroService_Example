package com.sjapp.ProductService.controller;

import com.sjapp.ProductService.model.ProductRequest;
import com.sjapp.ProductService.model.ProductResponse;
import com.sjapp.ProductService.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService ;

    @PostMapping
    public ResponseEntity<Long> addProduct(@RequestBody ProductRequest productRequest){
        long productId = productService.addProduct(productRequest);
        return new ResponseEntity<>(productId, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable("id") long productId){
        ProductResponse productResponse = productService.getProductById(productId);
        return new ResponseEntity<>(productResponse,HttpStatus.OK);
    }

    @PutMapping("/reduce_quantity/{id}")
    public ResponseEntity<String> reduceQuantity(@PathVariable("id") long productId ,@RequestParam long quantity){
        productService.reduceQuantity(productId , quantity);
        return new ResponseEntity<>("Successfully updated product quantity" , HttpStatus.OK);
    }

}
