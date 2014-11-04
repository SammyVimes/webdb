package com.danilov.dbcourse.subscribe;

import com.danilov.dbcourse.subscriber.Subscriber;
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
public class SubscribeRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Subscribe save(Subscribe subscribe) {
        entityManager.persist(subscribe);
        return subscribe;
    }

    @Transactional
    public Subscribe merge(Subscribe subscribe) {
        return entityManager.merge(subscribe);
    }

}
