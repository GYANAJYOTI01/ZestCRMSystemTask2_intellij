package com.ZestCRMSystemTask2.controller;

import com.ZestCRMSystemTask2.entity.Product;
import com.ZestCRMSystemTask2.service.ProductService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

//http://localhost:8080/api/products/calculateTotal?discountPercentage=10
    @PostMapping("/calculateTotal")
    public double calculateTotalWithDiscount(@RequestBody List<Product> products,
                                             @RequestParam double discountPercentage) {
        double totalAmount = productService.calculateTotalAmount(products);
        double discountedTotal = productService.applyDiscount(totalAmount, discountPercentage);
        return discountedTotal;
    }
}