package com.example.ECommerce.Repositories.JPA.RoleBasedRepositories;

import com.example.ECommerce.Entities.SubEntities.Customer;
import org.hibernate.annotations.NamedQuery;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Override
    @EntityGraph(value = "customerGraphAddress",type =  EntityGraph.EntityGraphType.FETCH)
    Optional<Customer> findById(Long id);

    @EntityGraph(value = "customerGraphAddress",type =  EntityGraph.EntityGraphType.FETCH)
    List<Customer> findByBalanceGreaterThanEqual(Double balance);

    @EntityGraph(value = "customerGraphAddress",type =  EntityGraph.EntityGraphType.FETCH)
    List<Customer>  findByBalanceLessThanEqual(Double balance);

    @EntityGraph(value = "customerGraphAddress",type =  EntityGraph.EntityGraphType.FETCH)
    List<Customer> findByBalanceBetween(Double minBalance, Double maxBalance);

    @Query("select c from Customer c join c.purchasedProducts p where p.id = ?1")
    @EntityGraph(value = "customerGraphAddress", type = EntityGraph.EntityGraphType.FETCH)
    List<Customer> getCustomersByPurchasedProduct(Long productId);

    @Query("select c from Customer c where c.id = ?1")
    @EntityGraph(value = "CustomerForProfile",type =  EntityGraph.EntityGraphType.FETCH)
    Customer getCustomerForProfile(Long id);


}