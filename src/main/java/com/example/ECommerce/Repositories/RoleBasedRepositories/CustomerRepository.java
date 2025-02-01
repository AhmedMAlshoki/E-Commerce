package com.example.ECommerce.Repositories.RoleBasedRepositories;

import com.example.ECommerce.Entities.SubEntities.Customer;
import org.hibernate.annotations.NamedQuery;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
@NamedQuery(name = "Customer.getCustomersByPurchasedProduct",
        query = "select c from Customer c join c.purchasedProducts p where p.id = ?1")//select u from User u where u.emailAddress = ?1")
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Override
    @EntityGraph(value = "customerGraphAddress")
    Optional<Customer> findById(Long id);

    List<Customer> findByBalanceGreaterThanEqual(Double balance);

    List<Customer>  findByBalanceLessThanEqual(Double balance);

    List<Customer> findByBalanceBetween(Double minBalance, Double maxBalance);


    List<Customer> getCustomersByPurchasedProduct(Long productId);
}