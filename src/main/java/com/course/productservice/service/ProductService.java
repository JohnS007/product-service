package com.course.productservice.service;

import com.course.productservice.entity.Product;
import com.course.productservice.model.ProductRequest;
import com.course.productservice.model.ProductResponse;

public interface ProductService {
    long addProduct(ProductRequest productRequest);

    ProductResponse getProductById(long productId);
}
