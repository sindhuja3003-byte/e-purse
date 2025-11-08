package com.payment.epurse.service;

import com.payment.epurse.entity.Merchant;
import com.payment.epurse.entity.Product;
import com.payment.epurse.exceptions.ResourceNotFoundException;
import com.payment.epurse.repository.ProductRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/*author - aniket das*/
@Service
public class ProductService {

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Optional<Product> getProduct(int ProductId) {
        return productRepository.findById(ProductId);
    }

    public Product updateProductById(int productId, Product updateProduct) {
        return productRepository.findById(productId).map(product ->
        {
            product.setProductName(updateProduct.getProductName());
            product.setProductPrice(updateProduct.getProductPrice());
            product.setProductDescription(updateProduct.getProductDescription());
            product.setProductCurrency(updateProduct.getProductCurrency());
            return productRepository.save(product);
        }).orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + productId));
    }

    public Iterable<Product> getAllProduct() {
        return productRepository.findAll();
    }

}
