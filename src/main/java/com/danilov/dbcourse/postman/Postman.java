package com.danilov.dbcourse.postman;

import javax.persistence.*;

/**
 * Created by Semyon on 04.11.2014.
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "postmans")
@NamedQueries({
        @NamedQuery(name = Postman.BY_NAME, query = "select a from Postman a where name=:name"),
        @NamedQuery(name = Postman.BY_PASSPORT, query = "select a from Postman a where passport=:passport")
})
public class Postman {

    public static final String BY_NAME = "Postman.getByName";
    public static final String BY_PASSPORT = "Postman.getByPasspoer";

    @Id
    @Column(name = "passport_number")
    private Long passport;

    @Column
    private String name;

    public Long getPassport() {
        return passport;
    }

    public void setPassport(Long passport) {
        this.passport = passport;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

}
