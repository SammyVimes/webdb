package com.danilov.dbcourse.postman;

import com.danilov.dbcourse.subscribe.Subscribe;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

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
            return entityManager.createQuery(Postman.BY_NAME, Postman.class)
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (PersistenceException e) {
            return null;
        }
    }

}
