package com.example.ECommerce.Repositories.JPA.RoleBasedRepositories;

import com.example.ECommerce.Entities.Product;
import com.example.ECommerce.Enums.Categories;
import jakarta.persistence.NamedQuery;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Override
    @EntityGraph(value = "ProductOwner",type =  EntityGraph.EntityGraphType.FETCH)
    Optional<Product> findById(Long id);
    @Query("SELECT p FROM Product p WHERE p.category = :category")
    @EntityGraph(value = "ProductOwner",type =  EntityGraph.EntityGraphType.FETCH)
    List<Product> findAllProductsByCategory(Categories category);
    @Query("SELECT p FROM Product p")
    @EntityGraph(value = "ProductOwner",type =  EntityGraph.EntityGraphType.FETCH)
    List<Product> getAllProduct();

    @Query("SELECT p FROM Product p WHERE p.owner.id = :sellerId")
    @EntityGraph(value = "ProductOwner",type =  EntityGraph.EntityGraphType.FETCH)
    List<Product> findBySellerId(Long sellerId);

    @EntityGraph(value = "ProductOwner",type =  EntityGraph.EntityGraphType.FETCH)
    List<Product> findByPriceGreaterThanEqual(Double price);

    @Query("SELECT p FROM Product p WHERE p.price BETWEEN :minBalance AND :maxBalance AND p.category = :category")
    @EntityGraph(value = "ProductOwner",type =  EntityGraph.EntityGraphType.FETCH)
    List<Product> findByPriceBetweenWithCategory(Categories category,Double minBalance, Double maxBalance);

    @Query("SELECT p FROM Product p WHERE p.category = :categoryName AND p.offer IS NOT NULL")
    @EntityGraph(value = "ProductOwner",type =  EntityGraph.EntityGraphType.FETCH)
    List<Product> findByCategoryNameANDHasOffer(Categories categoryName);

    @Query("SELECT p FROM Product p WHERE p.id = :id")
    @EntityGraph(value = "ProductOfferAndOwner",type =  EntityGraph.EntityGraphType.FETCH)
    //Also for purchase products
    Product findProductByIdProfile(Long id);

}