package com.example.ECommerce.Services;

import com.example.ECommerce.DTOs.ProductDTO;
import com.example.ECommerce.DTOs.ProductProfileDTO;
import com.example.ECommerce.DTOs.RoleBasedDTO.SellerDTO;
import com.example.ECommerce.Entities.Product;

import com.example.ECommerce.Entities.SubEntities.Seller;
import com.example.ECommerce.Enums.Categories;
import com.example.ECommerce.Mappers.ProductMapper;
import com.example.ECommerce.Mappers.SellerMapper;
import com.example.ECommerce.Repositories.ProductRepository;
import com.example.ECommerce.SecurityConfig.SecurityServices.UserDetailsImp;
import com.example.ECommerce.Services.UserServices.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final SellerService sellerService;
    private final SellerMapper sellerMapper;
    @Autowired
    public ProductService(ProductRepository productRepository, ProductMapper productMapper,
                           SellerService sellerService, SellerMapper sellerMapper) {
        this.productMapper = productMapper;
        this.productRepository = productRepository;
        this.sellerService = sellerService;
        this.sellerMapper = sellerMapper;
    }

    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepository.getAllProduct();
        return products.stream().map(productMapper::productToProductDTO).collect(Collectors.toList());
    }

    public List<ProductDTO> getProductsByCategory(Categories category) {
        List<Product> products = productRepository.findAllProductsByCategory(category);
        return products.stream().map(productMapper::productToProductDTO).collect(Collectors.toList());
    }

    public ProductDTO getProduct(Long id) {
        return productMapper.productToProductDTO(productRepository.findById(id).orElseThrow());
    }

    public Product getRawProduct(Long id) {
        return productRepository.findById(id).orElseThrow();
    }
    public void deleteProduct(Long id) throws Exception {
        try {
            productRepository.deleteById(id);
        }
        catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public List<ProductDTO> getProductsBySeller(Long sellerId) {
        //sellerService.validateSeller(sellerId); OR sellerService.exists(sellerId);
        List<Product> products = productRepository.findBySellerId(sellerId);
        return products.stream().map(productMapper::productToProductDTO).collect(Collectors.toList());
    }

    public List<ProductDTO> getProductsByPriceOrGreaterThan(Double balance) {
        List<Product> products = productRepository.findByPriceGreaterThanEqual(balance);
        return products.stream().map(productMapper::productToProductDTO).collect(Collectors.toList());
    }

    public List<ProductDTO> getProductsWithCategoryBetween(Categories category,Double minBalance,Double maxBalance) {
        List<Product> products = productRepository.findByPriceBetweenWithCategory(category,minBalance,maxBalance);
        return products.stream().map(productMapper::productToProductDTO).collect(Collectors.toList());
    }

    public List<ProductDTO> getProductsByCategoryNameANDHasOffer(Categories categoryName) {
        List<Product> products = productRepository.findByCategoryNameANDHasOffer(categoryName);
        return products.stream().map(productMapper::productToProductDTO).collect(Collectors.toList());
    }

    public ProductProfileDTO getProductProfile(Long id) {
        Product product = productRepository.findProductByIdProfile(id);
        return productMapper.productToProductProfileDTO(product);
    }

    public boolean isOwner(Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImp userDetails = (UserDetailsImp) authentication.getPrincipal();
        Long userId = productRepository.findById(id).orElseThrow().getOwner().getId();
        return userId.equals(userDetails.getId());
    }

    // After Finishing Seller Service
    public void updateProduct(ProductDTO productDTO) {
        SellerDTO seller = sellerService.getSeller(productDTO.seller());
        Product product = productMapper.productDTOToProduct(productDTO);
        product.setOwner(sellerMapper.sellerDTOToSeller(seller));
        productRepository.save(product);
    }

    public void updateProduct(Product product) {
        productRepository.save(product);
    }



    public void addProduct(ProductDTO productDTO) throws Exception {
        try {
            SellerDTO sellerdto = sellerService.validateSeller(productDTO.seller());
            Product product = productMapper.productDTOToProduct(productDTO);
            Seller seller = sellerMapper.sellerDTOToSeller(sellerdto);
            seller.getOwnedProducts().add(product);
            product.setOwner(seller);
            productRepository.save(product);
        }
        catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }


}
