package com.example.ECommerce.Services.UserServices;

import com.example.ECommerce.DTOs.RoleBasedDTO.CustomerDTO;
import com.example.ECommerce.DTOs.RoleBasedDTO.UserProfileDTO;
import com.example.ECommerce.DTOs.UserRegisterationDTO;
import com.example.ECommerce.Entities.Address;
import com.example.ECommerce.Entities.SubEntities.Customer;
import com.example.ECommerce.Enums.Roles;
import com.example.ECommerce.Mappers.CustomerMapper;
import com.example.ECommerce.Mappers.ProfilesMapper;
import com.example.ECommerce.Mappers.UserMapper;
import com.example.ECommerce.Repositories.RoleBasedRepositories.CustomerRepository;
import com.example.ECommerce.Services.AddressService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final UserMapper userMapper;
    private final CustomerMapper customerMapper;
    private final PasswordEncoder passwordEncoder;
    private final ProfilesMapper profilesMapper ;
    private final AddressService addressService ;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, UserMapper userMapper, CustomerMapper customerMapper, PasswordEncoder passwordEncoder,
                            ProfilesMapper profilesMapper, AddressService addressService) {
        this.customerRepository = customerRepository;
        this.userMapper = userMapper;
        this.customerMapper = customerMapper;
        this.passwordEncoder = passwordEncoder;
        this.profilesMapper = profilesMapper;
        this.addressService = addressService;
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

    public Customer getRawCustomer(Long id) throws Exception {
        boolean exists = customerRepository.existsById(id);
        if (!exists) {
            throw new Exception("Customer not found");
        }
        return getCustomerToPromote(id);
    }

    public CustomerDTO getCustomer(Long id) {
        return customerMapper.CustomertoCustomerDTO(customerRepository.findById(id).orElseThrow());
    }

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    public CustomerDTO updateCustomer(Long id, JsonNode jsonNode) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper() .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);;
        Customer existingCustomer = customerRepository.findById(id).orElseThrow();
        JsonNode newJsonNode = handlingJsonPersonalAddress(jsonNode,objectMapper,existingCustomer);//Convert Json to Dto OBJECT WITH NESTED ADDRESS dto
        existingCustomer = objectMapper.readerForUpdating(existingCustomer).readValue(newJsonNode);
        customerRepository.save(existingCustomer);
        //To make sure the updated Address will be returned within the entity
        Customer theupdatedCustomer = customerRepository.findById(id).orElseThrow();
        return customerMapper.CustomertoCustomerDTO(theupdatedCustomer);
    }

    public void updateCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    private JsonNode handlingJsonPersonalAddress(JsonNode jsonNode,ObjectMapper objectMapper,Customer existingCustomer) throws IOException
    {
            JsonNode addressNode = jsonNode.get("personalAddress");
            Address updatedAddress;
            if (existingCustomer.getPersonalAddress() != null) {
                // Merge into existing Address
                updatedAddress = objectMapper.readerForUpdating(existingCustomer.getPersonalAddress())
                        .readValue(addressNode);
            } else {
                // Create new Address
                updatedAddress = objectMapper.treeToValue(addressNode, Address.class);
            }
            addressService.updateAddress(updatedAddress);
            existingCustomer.setPersonalAddress(updatedAddress);
            ((ObjectNode) jsonNode).remove("personalAddress");
            return jsonNode;
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

    public UserProfileDTO getCustomerProfile(Long id) {
        Customer customer = customerRepository.getCustomerForProfile(id);
        return profilesMapper.customerToCustomerProfileDTO(customer);
    }
}
