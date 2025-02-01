package com.example.ECommerce.Services.UserServices;

import com.example.ECommerce.DTOs.RoleBasedDTO.CustomerDTO;
import com.example.ECommerce.DTOs.UserRegisterationDTO;
import com.example.ECommerce.Entities.SubEntities.Customer;
import com.example.ECommerce.Entities.User;
import com.example.ECommerce.Enums.Roles;
import com.example.ECommerce.Mappers.CustomerMapper;
import com.example.ECommerce.Mappers.UserMapper;
import com.example.ECommerce.Repositories.RoleBasedRepositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
        CustomerDTO customerDTO = customerMapper.CustomertoCustomerDTO(customer);
        return null;
    }

    public User getCustomer(Long id) {
        return null;
    }
}
