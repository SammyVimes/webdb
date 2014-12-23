package com.danilov.dbcourse.address;

import javax.persistence.*;

/**
 * Created by Semyon on 04.11.2014.
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "street",
        uniqueConstraints=
        @UniqueConstraint(columnNames = {"street", "house"}))
@NamedQueries({
        @NamedQuery(name = Address.BY_REGION, query = "select a from Address a where region=:region"),
        @NamedQuery(name = Address.UNSET, query = "select a from Address a where region is null"),
        @NamedQuery(name = Address.BY_STREET, query = "select a from Address a where street=:street and house=:house")
})
public class Address {

    public static final String BY_REGION = "Address.getByRegion";

    public static final String BY_STREET = "Address.getByStreet";

    public static final String UNSET = "Address.unset";

    @Id
    @GeneratedValue
    private long id;

    private String street;

    private String house;

    @ManyToOne
    private Region region;

    public String getHouse() {
        return house;
    }

    public void setHouse(final String house) {
        this.house = house;
    }

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(final String street) {
        this.street = street;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(final Region region) {
        this.region = region;
    }

    @Override
    public String toString() {
        return house + " " + street;
    }

}
