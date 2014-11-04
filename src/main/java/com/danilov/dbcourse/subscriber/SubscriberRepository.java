package com.danilov.dbcourse.subscriber;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

/**
 * Created by Semyon on 04.11.2014.
 */
@Repository
@Transactional(readOnly = true)
public class SubscriberRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    private PasswordEncoder passwordEncoder;

    @Transactional
    public Subscriber save(Subscriber subscriber) {
        subscriber.setPassword(passwordEncoder.encode(subscriber.getPassword()));
        entityManager.persist(subscriber);
        return subscriber;
    }

    public Subscriber findByLogin(final String login) {
        try {
            return entityManager.createNamedQuery(Subscriber.FIND_BY_LOGIN, Subscriber.class)
                    .setParameter("login", login)
                    .getSingleResult();
        } catch (PersistenceException e) {
            return null;
        }
    }

}
