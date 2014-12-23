package com.danilov.dbcourse.subscribe;

import com.danilov.dbcourse.magazine.Magazine;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Semyon on 04.11.2014.
 */
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

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Subscribe subscribe = (Subscribe) o;

        if (endDate != null ? !endDate.equals(subscribe.endDate) : subscribe.endDate != null) return false;
        if (magazine != null ? !magazine.equals(subscribe.magazine) : subscribe.magazine != null) return false;
        if (startDate != null ? !startDate.equals(subscribe.startDate) : subscribe.startDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = startDate != null ? startDate.hashCode() : 0;
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (magazine != null ? magazine.hashCode() : 0);
        return result;
    }

}
