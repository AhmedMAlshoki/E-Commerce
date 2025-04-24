package com.example.ECommerce.Services.UserServices;
import com.example.ECommerce.DTOs.RoleBasedDTO.SellerDTO;
import com.example.ECommerce.DTOs.RoleBasedDTO.UserProfileDTO;
import com.example.ECommerce.Entities.Address;
import com.example.ECommerce.Entities.SubEntities.Customer;
import com.example.ECommerce.Entities.SubEntities.Seller;
import com.example.ECommerce.Enums.Roles;
import com.example.ECommerce.Mappers.ProfilesMapper;
import com.example.ECommerce.Mappers.SellerMapper;
import com.example.ECommerce.Repositories.JPA.RoleBasedRepositories.SellerRepository;
import com.example.ECommerce.Services.AddressService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class SellerService  {

    private final SellerRepository sellerRepository;
    private final SellerMapper sellerMapper;
    private final CustomerService customerService;
    private final UserService userService;
    private final ProfilesMapper profilesMapper;
    private final AddressService addressService ;

    @Autowired
    public SellerService(SellerRepository sellerRepository, SellerMapper sellerMapper,
                          CustomerService customerService, AddressService addressService,
                            UserService userService,ProfilesMapper profilesMapper)
    {
        this.sellerRepository = sellerRepository;
        this.sellerMapper = sellerMapper;
        this.customerService = customerService;
        this.userService = userService;
        this.profilesMapper = profilesMapper;
        this.addressService = addressService;
    }
    public SellerDTO getSeller(Long id) {
        return sellerMapper.sellerToSellerDTO(sellerRepository.findById(id).orElseThrow());
    }

    public Seller  getSellerEntity(Long id) {
        return sellerRepository.findById(id).orElseThrow();
    }

    public void deleteSeller(Long id) {
        sellerRepository.deleteById(id);
    }

    private SellerDTO promoteCustomerToSeller(Long id) {
        Customer customer = customerService.getCustomerToPromote(id);
        Seller seller = new Seller();
        seller.setId(id);
        seller.setUsername(customer.getUsername());
        seller.setEmail(customer.getEmail());
        seller.setPhoneNumber(customer.getPhoneNumber());
        seller.setPassword(customer.getPassword());
        seller.setBalance(customer.getBalance());
        seller.setPersonalAddress(customer.getPersonalAddress());
        seller.setShippingAddress(customer.getPersonalAddress());
        seller.setRole(Roles.SELLER);
        seller.setBusinessName(customer.getUsername());
        seller.setAmountSaved(customer.getAmountSaved());
        seller.setCreatedAt(customer.getCreatedAt());
        seller.setOrderIds(customer.getOrderIds());
        seller.setPurchasedProducts(customer.getPurchasedProducts());
        seller.setReports(customer.getReports());
        seller.setLoyaltyPoints(customer.getLoyaltyPoints());
        seller.setAmountSpent(customer.getAmountSpent());
        seller.setWishListedProducts(customer.getWishListedProducts());
        seller.setReviewIds(customer.getReviewIds());
        customerService.deleteCustomer(id);
        addSeller(seller);
        return sellerMapper.sellerToSellerDTO(seller);

    }

    public SellerDTO validateSeller(Long id) throws Exception {
        if (sellerRepository.existsById(id))
            return sellerMapper.sellerToSellerDTO(sellerRepository.findById(id).orElseThrow());
        if (userService.getUserRole(id) != Roles.CUSTOMER)
            throw new Exception("User is not a customer");
        SellerDTO sellerDTO = promoteCustomerToSeller(id);
        return sellerMapper.sellerToSellerDTO(sellerRepository.save(sellerMapper.sellerDTOToSeller(sellerDTO)));
    }

    private void addSeller(Seller seller) {
        sellerRepository.save(seller);
    }

    public SellerDTO updateSeller(Long id, JsonNode jsonNode) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper() .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);;
        Seller existingSeller = sellerRepository.findById(id).orElseThrow();
        JsonNode newJsonNode = handlingJsonPersonalAddress(jsonNode,objectMapper,existingSeller);
        JsonNode newJsonNode1 = handlingJsonShippingAddress(newJsonNode,objectMapper,existingSeller);
        existingSeller = objectMapper.readerForUpdating(existingSeller).readValue(newJsonNode1);
        sellerRepository.save(existingSeller);
        //To make sure the updated Address will be returned within the entity
        Seller theUpdatedSeller = sellerRepository.findById(id).orElseThrow();
        return sellerMapper.sellerToSellerDTO(theUpdatedSeller);
    }
    private JsonNode handlingJsonPersonalAddress(JsonNode jsonNode,ObjectMapper objectMapper,Seller existingSeller) throws IOException {

            JsonNode addressNode = jsonNode.get("personalAddress");
            Address updatedAddress;
            if (existingSeller.getPersonalAddress() != null) {
                // Merge into existing Address
                updatedAddress = objectMapper.readerForUpdating(existingSeller.getPersonalAddress())
                        .readValue(addressNode);
            } else {
                // Create new Address
                updatedAddress = objectMapper.treeToValue(addressNode, Address.class);
            }
            addressService.updateAddress(updatedAddress);
            existingSeller.setPersonalAddress(updatedAddress);
            ((ObjectNode) jsonNode).remove("personalAddress");
            return jsonNode;

    }

    private JsonNode handlingJsonShippingAddress(JsonNode jsonNode,ObjectMapper objectMapper,Seller existingSeller) throws IOException {

            JsonNode addressNode = jsonNode.get("shippingAddress");
            Address updatedAddress;
            if (existingSeller.getShippingAddress() != null) {
                // Merge into existing Address
                updatedAddress = objectMapper.readerForUpdating(existingSeller.getShippingAddress())
                        .readValue(addressNode);
            } else {
                // Create new Address
                updatedAddress = objectMapper.treeToValue(addressNode, Address.class);
            }
            addressService.updateAddress(updatedAddress);
            existingSeller.setShippingAddress(updatedAddress);
            ((ObjectNode) jsonNode).remove("shippingAddress");
            return jsonNode;

    }

    public UserProfileDTO getSellerProfile(Long id) {
        Seller seller = sellerRepository.findByIdForProfile(id);
        return profilesMapper.sellerToSellerProfileDTO(seller);
    }
}
