package com.danilov.dbcourse.magazine;

import javax.persistence.*;

/**
 * Created by Semyon on 04.11.2014.
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "magazine")
@NamedQueries({
        @NamedQuery(name = Magazine.FIND_BY_NAME, query = "select a from Magazine a where a.name = :name"),
        @NamedQuery(name = Magazine.FIND_BY_ID, query = "select a from Magazine a where a.id = :id"),
        @NamedQuery(name = Magazine.FIND_ALL, query = "select a from Magazine a")
})
public class Magazine {

    public static final String FIND_BY_NAME = "Subscriber.findByName";
    public static final String FIND_BY_ID = "Subscriber.findById";
    public static final String FIND_ALL = "Subscriber.findAll";

    @Id
    @GeneratedValue
    private long id;

    @Column
    private String name;

    @Column
    private int price;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private MagazineType type;

    public int getPrice() {
        return price;
    }

    public void setPrice(final int price) {
        this.price = price;
    }

    public MagazineType getType() {
        return type;
    }

    public void setType(MagazineType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Magazine magazine = (Magazine) o;

        if (id != magazine.id) return false;
        if (price != magazine.price) return false;
        if (name != null ? !name.equals(magazine.name) : magazine.name != null) return false;
        if (type != magazine.type) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + price;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }

}
