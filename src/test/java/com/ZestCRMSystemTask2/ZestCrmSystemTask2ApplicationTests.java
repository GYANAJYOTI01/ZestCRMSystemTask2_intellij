package com.ZestCRMSystemTask2;

import static org.mockito.Mockito.*;
import com.ZestCRMSystemTask2.entity.Customer;
import com.ZestCRMSystemTask2.entity.Product;
import com.ZestCRMSystemTask2.payload.CustomerDTO;
import com.ZestCRMSystemTask2.repository.CustomerRepository;
import com.ZestCRMSystemTask2.repository.ProductRepository;
import com.ZestCRMSystemTask2.service.impl.CustomerServiceImpl;
import com.ZestCRMSystemTask2.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ZestCrmSystemTask2ApplicationTests {

		@Mock
		private ProductRepository productRepository;
	    @Mock
	    private CustomerRepository customerRepository;
	    @InjectMocks
	    private CustomerServiceImpl customerServiceimpl;
	    @InjectMocks
		private ProductServiceImpl productServiceimpl;
	    @Mock
	    private ModelMapper mapper;
	private CustomerDTO customerDTO;
	private Customer customer;
		@BeforeEach
		public void setUp() {
			MockitoAnnotations.openMocks(this);
		}

		@Test
		public void testCalculateTotalAmount() {
			List<Product> products = new ArrayList<>();
			products.add(new Product(1L, "Product A", 10.0, 2));
			products.add(new Product(2L, "Product B", 15.0, 3));
			products.add(new Product(3L, "Product C", 50.0, 5));

			double totalAmount = productServiceimpl.calculateTotalAmount(products);
			assertEquals(315.0, totalAmount);// 10 * 2 + 15 * 3 + 50 * 3 = 315
		}

		@Test
		public void testApplyDiscount() {
			double totalAmount = 100.0;
			double discountPercentage = 20.0;

			double discountedTotal = productServiceimpl.applyDiscount(totalAmount, discountPercentage);
			assertEquals(80.0, discountedTotal); // 100 - (20% of 100) = 80
		}


	@BeforeEach
	public void setup() {
		// Create a sample CustomerDTO
		customerDTO = new CustomerDTO();
		customerDTO.setName("Gyanajyoti panda");
		customerDTO.setEmail("gyanajyotipanda4@gmail.com");
		customerDTO.setPhone("8073762448");

		// Create a sample Customer entity
		customer = new Customer();
		customer.setId(6L);
		customer.setName("bapii");
		customer.setEmail("bapiii011@gmail.com");
		customer.setPhone("1215521190");
	}
	@Test
	public void testCreateCustomer() {
		when(mapper.map(any(), any())).thenReturn(customer);
		when(customerRepository.save(any())).thenReturn(customer);
		CustomerDTO result = customerServiceimpl.createCustomer(customerDTO);
	}
@Test
public void testGetCustomerById() {
	when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customer));
	when(mapper.map(any(), any())).thenReturn(customerDTO);
	CustomerDTO result = customerServiceimpl.getCustomerById(1L);
}
	@Test
	public void testGetAllCustomers() {
		List<Customer> customers = new ArrayList<>();
		customers.add(customer);
		when(customerRepository.findAll()).thenReturn(customers);
		when(mapper.map(any(), any())).thenReturn(customerDTO);
		List<CustomerDTO> result = customerServiceimpl.getAllCustomer();
	}

	@Test
	public void testUpdateCustomer() {
		when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customer));
		when(customerRepository.save(any())).thenReturn(customer);
		when(mapper.map(any(), any())).thenReturn(customer);

		CustomerDTO updatedCustomerDTO = new CustomerDTO();
		updatedCustomerDTO.setName("Gyana07");
		updatedCustomerDTO.setEmail("gyana1596@gmail.com");
		updatedCustomerDTO.setPhone("9876543210");
		CustomerDTO result = customerServiceimpl.updateCustomer(updatedCustomerDTO, 2L);
	}
@Test
public void testDeleteCustomerById() {
	when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customer));
	customerServiceimpl.deleteCustomerById(1L);
}
}