package com.example.ECommerce.Repositories.RoleBasedRepositories;

import com.example.ECommerce.Entities.SubEntities.Seller;
import com.example.ECommerce.Enums.Categories;
import org.hibernate.annotations.NamedQuery;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@NamedQuery(name = "Seller.findByCategory", query = "SELECT s FROM Seller s Join Product p ON s.id = p.owner.id AND p.category = :category")
@Repository
public interface SellerRepository extends JpaRepository<Seller, Long> {

    boolean existsById(Long id);

    @EntityGraph(value = "ShippingAddress",type =  EntityGraph.EntityGraphType.FETCH)
    List<Seller> findByCategory(Categories category);


    @Override
    @NonNull
    @EntityGraph(value = "GraphAddress",type =  EntityGraph.EntityGraphType.FETCH)
    Optional<Seller> findById(Long id);

    /*
    * Get seller profile
    * Seller findByIdForProfile(Long id);
    * */
}