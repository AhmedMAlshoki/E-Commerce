package com.example.ECommerce.Repositories;

import com.example.ECommerce.Entities.Product;
import com.example.ECommerce.Entities.SubEntities.Customer;
import com.example.ECommerce.Enums.Categories;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.NamedQuery;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@NamedQuery(name = "Product.getAllProductsByCategory",
        query = "SELECT p FROM Product p WHERE p.category = :category")
@NamedQuery(name = "Product.getAllProduct",
            query = "SELECT p FROM Product p")
@NamedQuery(name = "Product.findProductByIdProfile",
            query = "SELECT p FROM Product p WHERE p.id = :id")
@NamedQuery(name = "Product.findAllProductsBySellerId",
            query = "SELECT p FROM Product p WHERE p.owner.id = :sellerId")
@NamedQuery(name = "Product.findByBalanceBetweenWithCategory",
            query = "SELECT p FROM Product p WHERE p.price BETWEEN :minBalance AND :maxBalance AND p.category = :category")
@NamedQuery(name = "Product.findByCategoryNameANDHasOffer",
            query = "SELECT p FROM Product p WHERE p.category = :categoryName AND p.offer IS NOT NULL")
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Override
    @EntityGraph(value = "ProductOwner",type =  EntityGraph.EntityGraphType.FETCH)
    Optional<Product> findById(Long id);
    @EntityGraph(value = "ProductOwner",type =  EntityGraph.EntityGraphType.FETCH)
    List<Product> findAllProductsByCategory(Categories category);

    @EntityGraph(value = "ProductOwner",type =  EntityGraph.EntityGraphType.FETCH)
    List<Product> getAllProduct();

    @EntityGraph(value = "ProductOwner",type =  EntityGraph.EntityGraphType.FETCH)
    List<Product> findBySellerId(Long sellerId);

    @EntityGraph(value = "ProductOwner",type =  EntityGraph.EntityGraphType.FETCH)
    List<Product> findByPriceGreaterThanEqual(Double price);

    @EntityGraph(value = "ProductOwner",type =  EntityGraph.EntityGraphType.FETCH)
    List<Product> findByPriceBetweenWithCategory(Categories category,Double minBalance, Double maxBalance);

    @EntityGraph(value = "ProductOwner",type =  EntityGraph.EntityGraphType.FETCH)
    List<Product> findByCategoryNameANDHasOffer(Categories categoryName);

    @EntityGraph(value = "ProductOfferAndOwner",type =  EntityGraph.EntityGraphType.FETCH)
    Product findProductByIdProfile(Long id);
}