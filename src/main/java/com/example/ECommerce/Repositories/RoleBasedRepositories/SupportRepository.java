package com.example.ECommerce.Repositories.RoleBasedRepositories;

import com.example.ECommerce.Entities.SubEntities.Support;
import org.hibernate.annotations.NamedQuery;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@NamedQuery(name = "Support.findByIdForProfile", query = "SELECT s FROM Support s where s.id = :id")
@Repository
public interface SupportRepository extends JpaRepository<Support, Long> {
    @Override
    @NonNull
    Optional<Support> findById(Long id);

    boolean existsById(Long id);

    void deleteById(Long id);

    @Override
    @NonNull
    List<Support> findAll();

    @EntityGraph(value = "support-entity-graph",type =  EntityGraph.EntityGraphType.FETCH)
    Support findByIdForProfile(Long id);
}