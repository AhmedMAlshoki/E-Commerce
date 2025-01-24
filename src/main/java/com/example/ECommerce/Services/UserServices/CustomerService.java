package com.example.ECommerce.Services.UserServices;

import com.example.ECommerce.DTOs.RoleBasedDTO.CustomerDTO;
import com.example.ECommerce.DTOs.UserRegisterationDTO;
import com.example.ECommerce.Entities.SubEntities.Customer;
import com.example.ECommerce.Mappers.CustomerMapper;
import com.example.ECommerce.Mappers.UserMapper;
import com.example.ECommerce.Repositories.RoleBasedRepositories.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final UserMapper userMapper;
    private final CustomerMapper customerMapper;

    public CustomerService(CustomerRepository customerRepository, UserMapper userMapper, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.userMapper = userMapper;
        this.customerMapper = customerMapper;
    }

    public CustomerDTO registerCustomer(UserRegisterationDTO userRegisterationDTO) {
        Customer customer = userMapper.userRegisterationDTOToCustomer(userRegisterationDTO);
        customerRepository.save(customer);
        CustomerDTO customerDTO = customerMapper.CustomertoCustomerDTO(customer);
        return null;
    }
}
