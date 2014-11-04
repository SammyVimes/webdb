package com.danilov.dbcourse.address;

import com.danilov.dbcourse.postman.Postman;
import com.danilov.dbcourse.subscriber.Subscriber;
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
public class AddressRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Address saveAddress(Address address) {
        entityManager.persist(address);
        return address;
    }

    @Transactional
    public Address updateAddress(Address address) {
        entityManager.merge(address);
        return address;
    }

    @Transactional
    public Region saveRegion(Region region) {
        entityManager.persist(region);
        return region;
    }

    @Transactional
    public Region updateRegion(Region region) {
        entityManager.merge(region);
        return region;
    }

    public List<Region> getByPostman(final Postman postman) {
        try {
            return entityManager.createNamedQuery(Region.BY_POSTMAN, Region.class)
                    .setParameter("postman", postman)
                    .getResultList();
        } catch (PersistenceException e) {
            return null;
        }
    }

    public List<Address> getByRegion(final Region region) {
        try {
            return entityManager.createNamedQuery(Address.BY_REGION, Address.class)
                    .setParameter("region", region)
                    .getResultList();
        } catch (PersistenceException e) {
            return null;
        }
    }

    public Address getByStreet(final String street) {
        try {
            return entityManager.createNamedQuery(Address.BY_STREET, Address.class)
                    .setParameter("address", street)
                    .getSingleResult();
        } catch (PersistenceException e) {
            return null;
        }
    }

    public List<Region> getAllRegions() {
        try {
            return entityManager.createQuery("select a from Region a", Region.class)
                    .getResultList();
        } catch (PersistenceException e) {
            return null;
        }
    }

    public Region getById(final long id) {
        try {
            return entityManager.createQuery("select a from Region a where id=:id", Region.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (PersistenceException e) {
            return null;
        }
    }

}
