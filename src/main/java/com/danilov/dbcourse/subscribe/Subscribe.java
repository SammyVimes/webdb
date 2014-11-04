package com.danilov.dbcourse.subscribe;

import com.danilov.dbcourse.magazine.Magazine;
import com.danilov.dbcourse.subscriber.Subscriber;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Semyon on 04.11.2014.
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "subscribe")
public class Subscribe {

    @Id
    @GeneratedValue
    private long id;

    @Column
    private Date startDate;

    @Column
    private Date endDate;

    @OneToOne
    private Magazine magazine;

    @ManyToOne
    private Subscriber subscriber;

    public Magazine getMagazine() {
        return magazine;
    }

    public void setMagazine(final Magazine magazine) {
        this.magazine = magazine;
    }

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(final Date endDate) {
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(final Date startDate) {
        this.startDate = startDate;
    }

}
