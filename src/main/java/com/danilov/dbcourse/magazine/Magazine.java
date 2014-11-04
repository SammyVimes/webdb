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
}
