package com.danilov.dbcourse.address;

import javax.persistence.*;

/**
 * Created by Semyon on 04.11.2014.
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue
    private long id;

    @Column(unique = true)
    private String address;

    @Column
    private long region;

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(final String address) {
        this.address = address;
    }

    public long getRegion() {
        return region;
    }

    public void setRegion(final long region) {
        this.region = region;
    }

}
