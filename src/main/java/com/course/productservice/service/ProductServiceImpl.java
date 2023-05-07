package com.course.productservice.service;

import com.course.productservice.entity.Product;
import com.course.productservice.model.ProductRequest;
import com.course.productservice.model.ProductResponse;
import com.course.productservice.repository.ProductRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public long addProduct(ProductRequest productRequest) {
        Product product = Product.builder()
                .productName(productRequest.getName())
                .quantity(productRequest.getQuantity())
                .price(productRequest.getPrice())
                .build();

        productRepository.save(product);
        log.info("Product created");
        return product.getProductId();
    }

    @Override
    public ProductResponse getProductById(long productId) {
        log.info("Get product from the DB for productId {}: " + productId);
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product with id " + productId + " is not found"));

        ProductResponse productResponse = new ProductResponse();

        BeanUtils.copyProperties(product, productResponse);
        return productResponse;
    }
}
