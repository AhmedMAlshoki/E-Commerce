package com.example.ECommerce.Repositories.JPA.RoleBasedRepositories;

import com.example.ECommerce.Entities.Offer;
import org.hibernate.annotations.NamedQuery;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {
    @Override
    @NonNull
    @EntityGraph(value = "offerWithProductAndSeller",type =  EntityGraph.EntityGraphType.FETCH)
    List<Offer> findAll();

    @Override
    @NonNull
    @EntityGraph(value = "offerWithProductAndSeller",type =  EntityGraph.EntityGraphType.FETCH)
    Optional<Offer> findById(Long id);


    @Override
    boolean existsById(Long id);



    @Query("select o from Offer o where o.seller.id=:id")
    @EntityGraph(value = "offerWithProductAndSeller",type =  EntityGraph.EntityGraphType.FETCH)
    List<Offer> findBySellerId(Long id);


    @Query("select o from Offer o where o.product.id=:id")
    @EntityGraph(value = "offerWithProductAndSeller",type =  EntityGraph.EntityGraphType.FETCH)
    List<Offer> findByProductId(Long id);

    @Query("select o from Offer o where o.product.id=:productId and o.endDate > :startDate and o.startDate < :endDate")
    Boolean isAnotherOfferActiveOnTheProduct(Long productId, LocalDate startDate, LocalDate endDate);

    @Query("select o from Offer o where o.endDate > :currentDate and o.startDate < :currentDate")
    List<Offer> findAllActiveOffers(LocalDate currentDate);
}