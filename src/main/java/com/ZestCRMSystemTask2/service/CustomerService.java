package com.ZestCRMSystemTask2.service;

import com.ZestCRMSystemTask2.payload.CustomerDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerService {


   CustomerDTO createCustomer(CustomerDTO customerDTO) ;
   List<CustomerDTO> getAllCustomer();
   CustomerDTO getCustomerById(long id);
   CustomerDTO updateCustomer(CustomerDTO customerDTO, long id);
   void deleteCustomerById(long id);
}

