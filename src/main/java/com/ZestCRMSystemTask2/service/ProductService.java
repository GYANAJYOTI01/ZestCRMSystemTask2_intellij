package com.ZestCRMSystemTask2.service;

import com.ZestCRMSystemTask2.entity.Product;

import java.util.List;

public interface ProductService {
    double calculateTotalAmount(List<Product> products);
    double applyDiscount(double totalAmount, double discountPercentage);
}

