package com.ZestCRMSystemTask2.repository;

import com.ZestCRMSystemTask2.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}

