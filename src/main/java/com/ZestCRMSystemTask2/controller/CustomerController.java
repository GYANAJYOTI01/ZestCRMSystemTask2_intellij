package com.ZestCRMSystemTask2.controller;

import com.ZestCRMSystemTask2.payload.CustomerDTO;
import com.ZestCRMSystemTask2.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
   @Autowired
   private CustomerService customerService;
//http://localhost:8080/api/customers
    @PostMapping()
    public ResponseEntity<Object> createCustomer(@Valid @RequestBody CustomerDTO customerDTO, BindingResult  result) {
        if(result.hasErrors()){
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        CustomerDTO dto = customerService.createCustomer(customerDTO);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
//http://localhost:8080/api/customers
    @GetMapping
    public List<CustomerDTO> getAllCustomer(){

        return customerService.getAllCustomer();
    }
//http://localhost:8080/api/customers/5
    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable("id") long id) {
        return ResponseEntity.ok(customerService.getCustomerById(id));
    }
//http://localhost:8080/api/customers/5
    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(@RequestBody CustomerDTO  customerDTO, @PathVariable("id") long id) {
        CustomerDTO postResponse = customerService.updateCustomer(customerDTO, id);
        return ResponseEntity.ok(postResponse);
    }
//http://localhost:8080/api/customers/5
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomerById(@PathVariable("id") long id) {
        customerService.deleteCustomerById(id);
        return new ResponseEntity<>("Customer id deleted !!", HttpStatus.OK);
    }
}

