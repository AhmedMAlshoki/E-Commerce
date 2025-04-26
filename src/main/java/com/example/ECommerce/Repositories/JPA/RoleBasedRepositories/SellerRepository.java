package com.example.ECommerce.Repositories.JPA.RoleBasedRepositories;

import com.example.ECommerce.Entities.SubEntities.Seller;
import com.example.ECommerce.Enums.Categories;
import org.hibernate.annotations.NamedQuery;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;



@Repository
public interface SellerRepository extends JpaRepository<Seller, Long> {

    boolean existsById(Long id);

    @Query("SELECT s FROM Seller s Join Product p ON s.id = p.owner.id AND p.category = :category")
    @EntityGraph(value = "ShippingAddress",type =  EntityGraph.EntityGraphType.FETCH)
    List<Seller> findByCategory(Categories category);


    @Override
    @NonNull
    @EntityGraph(value = "GraphAddress",type =  EntityGraph.EntityGraphType.FETCH)
    Optional<Seller> findById(Long id);

    @Query("SELECT s FROM Seller s where s.id = :id")
    @EntityGraph(value = "SellerForProfile",type =  EntityGraph.EntityGraphType.FETCH)
    Seller findByIdForProfile(Long id);

}