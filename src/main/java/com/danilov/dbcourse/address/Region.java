package com.danilov.dbcourse.address;

import com.danilov.dbcourse.postman.Postman;

import javax.persistence.*;

/**
 * Created by Semyon on 04.11.2014.
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "region")
@NamedQueries({
        @NamedQuery(name = Region.BY_POSTMAN, query = "select a from Region a where postman=:postman")
})
public class Region {

    public static final String BY_POSTMAN = "Region.getByPostman";

    @Id
    @GeneratedValue
    private long id;

    @Column
    private long regionNumber;

    @ManyToOne(fetch = FetchType.EAGER)
    private Postman postman;

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public long getRegionNumber() {
        return regionNumber;
    }

    public void setRegionNumber(final long regionNumber) {
        this.regionNumber = regionNumber;
    }

    public Postman getPostman() {
        return postman;
    }

    public void setPostman(final Postman postman) {
        this.postman = postman;
    }

}
