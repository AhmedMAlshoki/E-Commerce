package com.example.ECommerce.Services;

import com.example.ECommerce.DTOs.CartDTO;
import com.example.ECommerce.Mappers.CartMapper;
import com.example.ECommerce.RedisHash.Cart;
import com.example.ECommerce.Repositories.CartRepository;
import com.example.ECommerce.SecurityConfig.SecurityServices.UserDetailsImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@CacheConfig(cacheNames = "cart")
@Service
public class CartService {
    private final CartRepository cartRepository;
    private final CartMapper cartMapper;
    private final OrderService orderService;

    @Autowired
    public CartService(CartRepository cartRepository,CartMapper cartMapper,OrderService orderService)
    {
        this.cartMapper= cartMapper;
        this.cartRepository= cartRepository;
        this.orderService=orderService;
    }

    @Cacheable(key = "#id")
    public CartDTO getCart() {
        Cart cart = getCart(getUserId());
        return cartMapper.cartToCartDTO(cart);
    }

    public Cart getCart(Long userId) {
        return cartRepository.findByUserId(userId);
    }

    // Create or update a cart and update the cache.
    @CachePut(key = "#cart.id")
    public CartDTO saveOrUpdateCart(Cart cart) {
        return cartMapper.cartToCartDTO(cartRepository.save(cart));
    }

    // Delete a cart and evict it from the cache.
    @CacheEvict(key = "#id")
    public String deleteCart() {
        String get = getCart(getUserId()).getId();
        cartRepository.deleteById(get);
        return "Cart had been deleted successfully";
    }

    private Long getUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImp userDetails = (UserDetailsImp) authentication.getPrincipal();
        return userDetails.getId();
    }


    public String orderCart() throws Exception {
        try
        {
            Cart cart = getCart(getUserId());
            if (cart != null){
                for (Long productId : cart.getItems().keySet()) {
                    orderService.addOrder(productId,cart.getItems().get(productId).intValue());
                    cart.getItems().remove(productId);
                }
            }
        }
        catch (Exception e) {
            throw new Exception("An error occurred while ordering the cart", e);
        }
        cartRepository.delete(getCart(getUserId()));
        return "Cart had been  ordered successfully";
    }
    // Add a product to the cart.
    public CartDTO addProduct(Long product_id,Long quantity) {
        Cart cart = getCart(getUserId());
        if (cart == null) {
            cart = new Cart();
            cart.setUserId(getUserId());
        }
        cart.getItems().put(product_id, quantity);
        return saveOrUpdateCart(cart);
    }

    public CartDTO addProducts(Long[][] product_ids) {
        Cart cart = getCart(getUserId());
        if (cart == null) {
            cart = new Cart();
            cart.setUserId(getUserId());
        }
        for (Long[] productId : product_ids) {
            cart.getItems().put(productId[0], productId[1]);
        }
        return saveOrUpdateCart(cart);
    }

    // Remove a product from the cart.
    public String removeProduct(Long productId) {
        Cart cart = getCart(getUserId());
        if (cart != null) {
            if (cart.getItems().containsKey(productId)) {
                cart.getItems().remove(productId);
                return "Product removed from cart";
            }
            else {
                return "Product not found in your  cart";
            }
        }
        return "Create a cart first that contains at least one product";
    }
}
