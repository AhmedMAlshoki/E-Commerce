package com.example.ECommerce.Services;

import com.example.ECommerce.DTOs.OfferDTO;
import com.example.ECommerce.DTOs.ProductDTO;
import com.example.ECommerce.DTOs.RoleBasedDTO.SellerDTO;
import com.example.ECommerce.Entities.Offer;
import com.example.ECommerce.Mappers.OfferMapper;
import com.example.ECommerce.Mappers.ProductMapper;
import com.example.ECommerce.Mappers.SellerMapper;
import com.example.ECommerce.Repositories.JPA.RoleBasedRepositories.OfferRepository;
import com.example.ECommerce.SecurityConfig.SecurityServices.UserDetailsImp;
import com.example.ECommerce.Services.UserServices.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OfferService {
    private final OfferRepository offerRepository;
    private final ProductService productService;
    private final SellerService sellerService;
    private final OfferMapper offerMapper;
    private final ProductMapper productMapper;
    private final SellerMapper  sellerMapper;

    @Autowired
    public OfferService(OfferRepository offerRepository, ProductService productService,
                        SellerService sellerService,@Lazy OfferMapper offerMapper,
                        @Lazy ProductMapper productMapper,@Lazy SellerMapper sellerMapper) {
        this.offerRepository = offerRepository;
        this.productService = productService;
        this.sellerService = sellerService;
        this.offerMapper = offerMapper;
        this.productMapper = productMapper;
        this.sellerMapper = sellerMapper;
    }

    public boolean isOwner(Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImp userDetails = (UserDetailsImp) authentication.getPrincipal();
        Long userId = offerRepository.findById(id).orElseThrow().getSeller().getId();
        return userId.equals(userDetails.getId());
    }


    public OfferDTO findOffer(Long id) {
        return offerMapper.offerToOfferDTO(offerRepository.findById(id).orElseThrow());
    }

    public void deleteOffer(Long id) {
        offerRepository.deleteById(id);
    }

    public void updateOffer(OfferDTO offerDTO) {
        SellerDTO sellerDTO = sellerService.getSeller(offerDTO.sellerId());
        ProductDTO productDTO = productService.getProduct(offerDTO.productId());
        Offer offer = offerMapper.offerDTOToOffer(offerDTO);
        offer.setProduct(productMapper.productDTOToProduct(productDTO));
        offer.setSeller(sellerMapper.sellerDTOToSeller(sellerDTO));
        offerRepository.save(offer);
    }

    public void addOffer(OfferDTO offerDTO) {
        if (offerRepository.isAnotherOfferActiveOnTheProduct(offerDTO.productId(),offerDTO.startDate(),offerDTO.endDate())) {
            throw new RuntimeException("There is another offer active on the product");
        }
        SellerDTO sellerDTO = sellerService.getSeller(offerDTO.sellerId());
        ProductDTO productDTO = productService.getProduct(offerDTO.productId());
        Offer offer = offerMapper.offerDTOToOffer(offerDTO);
        offer.setProduct(productMapper.productDTOToProduct(productDTO));
        offer.setSeller(sellerMapper.sellerDTOToSeller(sellerDTO));
        offerRepository.save(offer);
    }

    public List<OfferDTO> getAllOffers() {
        List<Offer> offers = offerRepository.findAll();
        return offers.stream().map(offerMapper::offerToOfferDTO).collect(Collectors.toList());
    }

    public List<OfferDTO> getActiveOffers() {
        LocalDate date = new Date().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
        List<Offer> offers = offerRepository.findAllActiveOffers(date);
        return offers.stream().map(offerMapper::offerToOfferDTO).collect(Collectors.toList());
    }

    public List<OfferDTO> getOffersByProduct(Long id) {
        List<Offer> offers = offerRepository.findByProductId(id);
        return offers.stream().map(offerMapper::offerToOfferDTO).collect(Collectors.toList());
    }

    public List<OfferDTO> getOffersBySeller(Long id) {
        List<Offer> offers = offerRepository.findBySellerId(id);
        return offers.stream().map(offerMapper::offerToOfferDTO).collect(Collectors.toList());
    }
}
