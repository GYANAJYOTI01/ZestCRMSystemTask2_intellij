package com.ZestCRMSystemTask2.service.impl;

import com.ZestCRMSystemTask2.entity.Product;
import com.ZestCRMSystemTask2.repository.ProductRepository;
import com.ZestCRMSystemTask2.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;


    public ProductServiceImpl(ProductRepository productRepository ) {
        this.productRepository = productRepository;
    }
    @Override
    public double calculateTotalAmount(List<Product> products) {
        double totalAmount = 0;
        for (Product product : products) {
            totalAmount += product.getPrice() * product.getQuantity();
        }
        return totalAmount;
    }
    @Override
    public double applyDiscount(double totalAmount, double discountPercentage) {
        double discountAmount=0;
        double actualAmount=0;
        discountAmount=totalAmount * (discountPercentage / 100);
        actualAmount= totalAmount - discountAmount;
        return actualAmount;
    }
}