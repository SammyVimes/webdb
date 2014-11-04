package com.danilov.dbcourse.postman;

import com.danilov.dbcourse.subscribe.Subscribe;
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
public class PostmanRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Postman save(Postman postman) {
        entityManager.persist(postman);
        return postman;
    }

    @Transactional
    public Postman merge(Postman postman) {
        return entityManager.merge(postman);
    }

    public Postman getByName(final String name) {
        try {
            return entityManager.createNamedQuery(Postman.BY_NAME, Postman.class)
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (PersistenceException e) {
            return null;
        }
    }

    public List<Postman> getAll() {
        try {
            return entityManager.createQuery("select a from Postman a", Postman.class)
                    .getResultList();
        } catch (PersistenceException e) {
            return null;
        }
    }

}