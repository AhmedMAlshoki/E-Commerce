package com.example.ECommerce.Services;


import com.example.ECommerce.DTOs.OrderDTO;
import com.example.ECommerce.Documents.Order;
import com.example.ECommerce.Entities.Payment;
import com.example.ECommerce.Entities.Product;
import com.example.ECommerce.Entities.SubEntities.Customer;
import com.example.ECommerce.Enums.Roles;
import com.example.ECommerce.Enums.STATUS;
import com.example.ECommerce.Mappers.OrderMapper;
import com.example.ECommerce.Repositories.OrderRepository;
import com.example.ECommerce.Repositories.ProductRepository;
import com.example.ECommerce.SecurityConfig.SecurityServices.UserDetailsImp;
import com.example.ECommerce.Services.UserServices.CustomerService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final PaymentsService paymentsService;
    private final ProductService productService;
    private final CustomerService customerService;
    private final OrderMapper orderMapper;

    @Autowired
    public OrderService(OrderRepository orderRepository, PaymentsService paymentsService,
                        CustomerService customerService, OrderMapper orderMapper,
                        ProductService productService) {
        this.orderRepository = orderRepository;
        this.paymentsService = paymentsService;
        this.customerService = customerService;
        this.orderMapper = orderMapper;
        this.productService = productService;
    }
    private Long getUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImp userDetails = (UserDetailsImp) authentication.getPrincipal();
        return userDetails.getId();
    }

    private Roles getRole() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImp userDetails = (UserDetailsImp) authentication.getPrincipal();
        return userDetails.getRole();
    }

    @Transactional
    public ResponseEntity<?> addOrder(Long productId, int quantity) throws RuntimeException {
        Product product = productService.getRawProduct(productId);
        Long userId = getUserId();
        Roles role = getRole();
        Customer customer = customerService.getRawCustomer(userId);
        if (role.equals(Roles.SUPPORT) || !role.equals(Roles.ADMIN)) {
            throw new RuntimeException("You are not authorized to place an order");
        }
        if (product.getQuantity() < quantity) {
            throw new RuntimeException("Product is out of stock");
        }
        Order order = new Order();
        try {
            //Error of Purchasing "short of balance" occurred
            String orderId = new ObjectId().toString();
            Payment payment = paymentsService.createPayment(product , customer, quantity, orderId);
            order.setId(orderId);
            order.setQuantity(quantity);
            order.setStatus(STATUS.ONGOING);
            order.setPaymentId(payment.getId());
            order.setProductId(product.getId());;
            if (product.getOwner().getShippingAddress() == null || customer.getPersonalAddress() == null) {
                throw new RuntimeException("An error within the addresses occurred");
            }
            order.setShippingAddress(product.getOwner().getShippingAddress().getId());
            order.setDestinationAddress(customer.getPersonalAddress().getId());
            order.setUserId(customer.getId());
            orderRepository.save(order);
            product.setQuantity(product.getQuantity() - quantity);
            productService.updateProduct(product);
        }
        catch (Exception e) {
            throw new RuntimeException("Failed to place order", e);
        }
        return ResponseEntity.ok(orderMapper.orderToOrderDTO(order));
    }

    public List<OrderDTO> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream().map(orderMapper::orderToOrderDTO).collect(Collectors.toList());
    }

    public List<OrderDTO> getAllOrdersByProduct(Long id) {
        List<Order> orders = orderRepository.findByProductId(id);
        return orders.stream().map(orderMapper::orderToOrderDTO).collect(Collectors.toList());
    }

    public OrderDTO getOrder(String id) {
        Order order = orderRepository.findById(id).orElse(null);
        return orderMapper.orderToOrderDTO(order);
    }


    public void deleteOrder(String id) {
        orderRepository.deleteById(id);
    }

    public List<OrderDTO> getOrdersByUser(Long id) {
        List<Order> orders = orderRepository.findByUserId(id);
        return orders.stream().map(orderMapper::orderToOrderDTO).collect(Collectors.toList());
    }


    @Transactional
    public OrderDTO changeStatus(String id, STATUS status) {
        if (status.equals(STATUS.CANCELLED)) {
            paymentsService.cancelPayment(id);
            int quantity = orderRepository.findById(id).orElseThrow().getQuantity();
            Product product = productService.getRawProduct(orderRepository.findById(id).orElseThrow().getProductId());
            product.setQuantity(product.getQuantity() + quantity);
            productService.updateProduct(product);
        }
        Order order = orderRepository.changeStatus(id, status);
        return orderMapper.orderToOrderDTO(order);
    }


}
