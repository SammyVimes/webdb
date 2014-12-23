package com.danilov.dbcourse.subscribe;

import com.danilov.dbcourse.address.Region;
import com.danilov.dbcourse.magazine.Magazine;
import com.danilov.dbcourse.subscriber.Subscriber;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

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

    public List<Subscribe> subscribesByMagazine(final Magazine magazine) {
        List<Subscriber> subscribers = entityManager.createQuery("select a from Subscriber a join a.subscribes m where m.magazine=:magazine", Subscriber.class).setParameter("magazine", magazine).getResultList();
        List<Subscribe> subscribes = new ArrayList<>(subscribers.size());

        for (Subscriber s : subscribers) {
            Set<Subscribe> subscribeList = s.getSubscribes();
            for (Subscribe subscribe : subscribeList) {
                if (subscribe.getMagazine().equals(magazine)) {
                    subscribes.add(subscribe);
                    break;
                }
            }
        }
        return subscribes;
    }


    public List<Subscriber> subscribersByMagazineAndRegion(final Magazine magazine, final Region region) {
        List<Subscriber> subscribers = entityManager.createQuery("select a from Subscriber a join a.subscribes m where m.magazine=:magazine and a.address.region=:region", Subscriber.class)
                .setParameter("magazine", magazine)
                .setParameter("region", region)
                .getResultList();
        return subscribers;
    }

    public List<Subscribe> subscribesByMagazineAndTillDate(final Magazine magazine, final Date date) {
        List<Subscriber> subscribers = entityManager.createQuery("select a from Subscriber a join a.subscribes m where m.magazine=:magazine", Subscriber.class).setParameter("magazine", magazine).getResultList();
        List<Subscribe> subscribes = new ArrayList<>(subscribers.size());


        for (Subscriber s : subscribers) {
            Set<Subscribe> subscribeList = s.getSubscribes();
            for (Subscribe subscribe : subscribeList) {
                if (subscribe.getMagazine().equals(magazine)) {
                    if (subscribe.getEndDate().after(date)) {
                        subscribes.add(subscribe);
                    }
                    break;
                }
            }
        }
        return subscribes;
    }

}
