package com.example.ECommerce.Services.UserServices;

import com.example.ECommerce.DTOs.RoleBasedDTO.CustomerDTO;
import com.example.ECommerce.DTOs.UserRegisterationDTO;
import com.example.ECommerce.Entities.SubEntities.Customer;
import com.example.ECommerce.Entities.User;
import com.example.ECommerce.Enums.Roles;
import com.example.ECommerce.Mappers.CustomerMapper;
import com.example.ECommerce.Mappers.UserMapper;
import com.example.ECommerce.Repositories.RoleBasedRepositories.CustomerRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final UserMapper userMapper;
    private final CustomerMapper customerMapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, UserMapper userMapper, CustomerMapper customerMapper, PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.userMapper = userMapper;
        this.customerMapper = customerMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public CustomerDTO registerCustomer(UserRegisterationDTO userRegisterationDTO) {
        Customer customer = userMapper.userRegisterationDTOToCustomer(userRegisterationDTO);
        customer.setRole(Roles.CUSTOMER);
        customer.setPassword(passwordEncoder.encode(userRegisterationDTO.password()));
        customerRepository.save(customer);
        return customerMapper.CustomertoCustomerDTO(customer);
    }

    public Customer getCustomerToPromote(Long id) {
        return customerRepository.findById(id).orElseThrow();
    }

    public CustomerDTO getCustomer(Long id) {
        return customerMapper.CustomertoCustomerDTO(customerRepository.findById(id).orElseThrow());
    }

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    public CustomerDTO updateCustomer(Long id, JsonNode jsonNode){
        ObjectMapper objectMapper = new ObjectMapper();
        CustomerDTO customerDTO = objectMapper.convertValue(jsonNode,CustomerDTO.class); //Convert Json to Dto OBJECT WITH NESTED ADDRESS dto
        //-----------------------------------------------------------------------------------------
        Customer customer = customerMapper.CustomerDTOtoCustomer(customerDTO);
        customerRepository.save(customer);
        return customerDTO;
    }

    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream().map(customerMapper::CustomertoCustomerDTO).collect(Collectors.toList());
    }

    public List<CustomerDTO> getCustomerWithBalanceGreaterThanOrEqual(double balance) {
        List <Customer> customers = customerRepository.findByBalanceGreaterThanEqual(balance);
        return customers.stream().map(customerMapper::CustomertoCustomerDTO).collect(Collectors.toList());
    }

    public List<CustomerDTO> getCustomerWithBalanceLessThanOrEqual(double balance) {
        List <Customer> customers = customerRepository.findByBalanceLessThanEqual(balance);
        return customers.stream().map(customerMapper::CustomertoCustomerDTO).collect(Collectors.toList());
    }

    public List<CustomerDTO> getCustomerWithBalanceBetween(double minBalance, double maxBalance) {
        List <Customer> customers = customerRepository.findByBalanceBetween(minBalance, maxBalance);
        return customers.stream().map(customerMapper::CustomertoCustomerDTO).collect(Collectors.toList());
    }

    public List<CustomerDTO> getBuyers(Long productId) {
        List <Customer> customers = customerRepository.getCustomersByPurchasedProduct(productId);
        return customers.stream().map(customerMapper::CustomertoCustomerDTO).collect(Collectors.toList());
    }
}
