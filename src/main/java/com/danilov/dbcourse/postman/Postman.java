package com.danilov.dbcourse.postman;

import javax.persistence.*;

/**
 * Created by Semyon on 04.11.2014.
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "postmans")
@NamedQueries({
        @NamedQuery(name = Postman.BY_NAME, query = "select a from Postman a where name=:name")
})
public class Postman {

    public static final String BY_NAME = "Postman.getByName";

    @Id
    @GeneratedValue
    private long id;

    @Column
    private String name;

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

}
