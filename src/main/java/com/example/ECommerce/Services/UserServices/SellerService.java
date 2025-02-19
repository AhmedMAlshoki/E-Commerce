package com.example.ECommerce.Services.UserServices;

import com.example.ECommerce.DTOs.RoleBasedDTO.CustomerDTO;
import com.example.ECommerce.DTOs.RoleBasedDTO.SellerDTO;
import com.example.ECommerce.DTOs.RoleBasedDTO.UserDTO;
import com.example.ECommerce.Entities.SubEntities.Customer;
import com.example.ECommerce.Entities.SubEntities.Seller;
import com.example.ECommerce.Entities.User;
import com.example.ECommerce.Enums.Roles;
import com.example.ECommerce.Mappers.AddressMapper;
import com.example.ECommerce.Mappers.SellerMapper;
import com.example.ECommerce.Repositories.RoleBasedRepositories.SellerRepository;
import com.example.ECommerce.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerService  {

    private final SellerRepository sellerRepository;
    private final SellerMapper sellerMapper;
    private final CustomerService customerService;
    private final UserService userService;
    @Autowired
    public SellerService(SellerRepository sellerRepository, SellerMapper sellerMapper,
                          CustomerService customerService,
                            UserService userService)
    {
        this.sellerRepository = sellerRepository;
        this.sellerMapper = sellerMapper;
        this.customerService = customerService;
        this.userService = userService;
    }
    public SellerDTO getSeller(Long id) {
        return sellerMapper.sellerToSellerDTO(sellerRepository.findById(id).orElseThrow());
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
        seller.setCarts(customer.getCarts());
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
    /*
    * Update seller
    * public void updateSeller(SellerDTO sellerDTO)
    * */
}
