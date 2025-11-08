package com.payment.epurse.controller;

import com.payment.epurse.entity.Product;
import com.payment.epurse.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/*author - aniket das*/
@RestController("/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/getProductById/{productId}")
    public Optional<Product> findById(@PathVariable Integer productId) {
        return productService.getProduct(productId);
    }

    @GetMapping("/allProduct")
    public Iterable<Product> getAllProducts() {
        return  productService.getAllProduct();
    }

    @PutMapping("/updateProduct/{productId}")
    public Product updateProduct(@RequestBody Product product, @RequestParam Integer productId) {
        return productService.updateProductById(productId, product);
    }
}
