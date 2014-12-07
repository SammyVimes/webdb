package com.danilov.dbcourse.address;

import javax.persistence.*;

/**
 * Created by Semyon on 04.11.2014.
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "address")
@NamedQueries({
        @NamedQuery(name = Address.BY_REGION, query = "select a from Address a where region=:region"),
        @NamedQuery(name = Address.UNSET, query = "select a from Address a where region is null"),
        @NamedQuery(name = Address.BY_STREET, query = "select a from Address a where address=:address")
})
public class Address {

    public static final String BY_REGION = "Address.getByRegion";

    public static final String BY_STREET = "Address.getByStreet";

    public static final String UNSET = "Address.unset";

    @Id
    @GeneratedValue
    private long id;

    @Column(unique = true)
    private String address;

    @ManyToOne
    private Region region;

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

    public Region getRegion() {
        return region;
    }

    public void setRegion(final Region region) {
        this.region = region;
    }

}
