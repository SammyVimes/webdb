package com.danilov.dbcourse.magazine;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import java.util.List;

/**
 * Created by Semyon on 04.11.2014.
 */
@Repository
@Transactional(readOnly = true)
public class MagazineRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Magazine save(Magazine magazine) {
        entityManager.persist(magazine);
        return magazine;
    }

    public Magazine findByName(String name) {
        try {
            return entityManager.createNamedQuery(Magazine.FIND_BY_NAME, Magazine.class)
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (PersistenceException e) {
            return null;
        }
    }

    public Magazine findById(long id) {
        try {
            return entityManager.createNamedQuery(Magazine.FIND_BY_ID, Magazine.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (PersistenceException e) {
            return null;
        }
    }

    public List<Magazine> findAll() {
        try {
            return entityManager.createNamedQuery(Magazine.FIND_ALL, Magazine.class)
                    .getResultList();
        } catch (PersistenceException e) {
            return null;
        }
    }

}