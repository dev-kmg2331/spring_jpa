package com.example.advanced.repository.pet;

import com.example.advanced.entity.pet.Owner;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class OwnerDAO {

    @PersistenceContext EntityManager em;

    public Owner save(Owner owner) {
        em.persist(owner);
        return owner;
    }

    public Optional<Owner> findById(Long id) {
        String query = "select o from Owner o inner join fetch o.pets where o.id = :id";
        return Optional.ofNullable(em.createQuery(query, Owner.class)
                .setParameter("id", id)
                .getSingleResult());
    }

    public List<Owner> findAll() {
        String query = "select o from Owner o inner join fetch o.pets";
        return em.createQuery(query, Owner.class)
                .getResultList();
    }

    public void delete(Owner owner) {
        em.remove(owner);
    }
}
