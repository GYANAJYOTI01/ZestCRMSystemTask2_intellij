package com.ZestCRMSystemTask2.service.impl;

import com.ZestCRMSystemTask2.entity.Customer;
import com.ZestCRMSystemTask2.exception.CustomerNotFoundException;
import com.ZestCRMSystemTask2.payload.CustomerDTO;
import com.ZestCRMSystemTask2.repository.CustomerRepository;
import com.ZestCRMSystemTask2.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class CustomerServiceImpl implements CustomerService {

     @Autowired
        private CustomerRepository customerRepository;

    @Override
    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        Customer customer = mapToEntity (customerDTO);
        Customer savedcustomer = customerRepository.save(customer);
        CustomerDTO dto = mapToDto(savedcustomer);
            return dto;
    }
    @Override
    public List<CustomerDTO> getAllCustomer() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream().map(customer -> mapToDto(customer)).collect(Collectors.toList());
    }
    @Override
    public CustomerDTO getCustomerById(long id) {
        Customer  customer = customerRepository.findById(id).orElseThrow(

                    () -> new CustomerNotFoundException("Customer", "id", id)
            );
            return mapToDto(customer);


    }
    @Override
    public CustomerDTO updateCustomer(CustomerDTO customerDTO, long id) {
        Customer  customer = customerRepository.findById(id).orElseThrow(

                    ()->new CustomerNotFoundException("Customer","id",id)
            );
        customer.setPhone(customerDTO.getPhone());
        customer.setName(customerDTO.getName());
        customer.setEmail(customerDTO.getEmail());

        Customer updateCustomer = customerRepository.save(customer);
            return mapToDto(updateCustomer);
    }
    @Override
    public void deleteCustomerById(long id) {
        customerRepository.findById(id).orElseThrow(()->new CustomerNotFoundException("Customer","id",id));
        customerRepository.deleteById(id);
    }
    CustomerDTO mapToDto(Customer  customer){
        CustomerDTO customerDTO=new CustomerDTO();
        customerDTO.setId(customer.getId());
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setName(customer.getName());
        customerDTO.setPhone(customer.getPhone());
        return customerDTO;

    }
    Customer mapToEntity(CustomerDTO customerDTO){
        Customer  customer = new Customer();
        customer.setName(customerDTO.getName());
        customer.setEmail(customerDTO.getEmail());
        customer.setPhone(customerDTO.getPhone());
        return customer;
    }
}

