package com.danilov.dbcourse.subscribe;

import com.danilov.dbcourse.magazine.Magazine;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Semyon on 04.11.2014.
 */
@SuppressWarnings("serial")
@Embeddable
public class Subscribe {

    @Column
    private Date startDate;

    @Column
    private Date endDate;

    @ManyToOne(fetch = FetchType.EAGER)
    private Magazine magazine;

    public Magazine getMagazine() {
        return magazine;
    }

    public void setMagazine(final Magazine magazine) {
        this.magazine = magazine;
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
