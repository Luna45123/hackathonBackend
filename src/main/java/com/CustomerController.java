package com;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.domain.Customer;
import com.dto.CustomerDTO;
import com.mapper.CustomerMapper;
import com.repository.CustomerRepository;

@RestController
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerMapper customerMapper;

    @PostMapping("/addCustomer")
    public ResponseEntity<String> createCustomer(@RequestBody CustomerDTO customerDTO) {
        Customer newCustomer = new Customer();
        customerMapper.updateCustomerFromDto(customerDTO, newCustomer);
        customerRepository.save(newCustomer);
        return new ResponseEntity<>("Customer created", HttpStatus.OK);
    }

    @GetMapping("/allCustomers")
    public ResponseEntity<Collection<CustomerDTO>> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        List<CustomerDTO> customerDTOs = new ArrayList<>();
        for (Customer customer : customers) {
            CustomerDTO customerDTO = customerMapper.toCustomerDTO(customer);
            customerDTOs.add(customerDTO);
        }
        return new ResponseEntity<>(customerDTOs, HttpStatus.OK);
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Long id) {
        return customerRepository.findById(id)
                .map(customer -> new ResponseEntity<>(customerMapper.toCustomerDTO(customer), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    

}
