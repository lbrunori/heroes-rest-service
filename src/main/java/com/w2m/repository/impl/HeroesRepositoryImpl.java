package com.w2m.repository.impl;

import com.w2m.model.Heroe;
import com.w2m.repository.IHeroesRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class HeroesRepositoryImpl implements IHeroesRepository {

    @PersistenceContext
    EntityManager entityManager;

    public List<Heroe> findAll() {
        TypedQuery<Heroe> namedQuery = entityManager.createNamedQuery("find_all_heroes", Heroe.class);
        return namedQuery.getResultList();
    }

    public Heroe findById(long id) {
        return entityManager.find(Heroe.class, id);// JPA
    }

    public Heroe update(Heroe person) {
        return entityManager.merge(person);
    }

    public Heroe insert(Heroe person) {
        return entityManager.merge(person);
    }

    public void deleteById(long id) {
        Heroe person = findById(id);
        entityManager.remove(person);
    }
}
