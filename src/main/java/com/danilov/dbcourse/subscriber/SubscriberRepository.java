package com.danilov.dbcourse.subscriber;

import com.danilov.dbcourse.address.Address;
import com.danilov.dbcourse.subscribe.Subscribe;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import java.util.List;

/**
 * Created by Semyon on 04.11.2014.
 */
@Repository
@Transactional(readOnly = true)
public class SubscriberRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Subscriber save(Subscriber subscriber) {
        entityManager.persist(subscriber);
        return subscriber;
    }

    @Transactional
    public Subscriber merge(Subscriber subscriber) {
        if (subscriber.getSubscribes() != null) {
            entityManager.createNativeQuery("delete from subscriber_subscribes where subscriber_id=" + subscriber.getPassport()).executeUpdate();
            entityManager.flush();
        }
        return entityManager.merge(subscriber);
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

    public List<Subscriber> findByAddress(final Address address) {
        try {
            return entityManager.createQuery("select a from Subscriber a where address=:address", Subscriber.class)
                    .setParameter("address", address)
                    .getResultList();
        } catch (PersistenceException e) {
            return null;
        }
    }


    public Subscriber findByAll(final String name, final String surname, final String patronymic) {
        try {
            return entityManager.createQuery("select a from Subscriber a where name=:name and surname=:surname and patronymic=:patronymic", Subscriber.class)
                    .setParameter("name", name)
                    .setParameter("surname", surname)
                    .setParameter("patronymic", patronymic)
                    .getSingleResult();
        } catch (PersistenceException e) {
            return null;
        }
    }

}
