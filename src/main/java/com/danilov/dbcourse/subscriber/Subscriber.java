package com.danilov.dbcourse.subscriber;

import com.danilov.dbcourse.address.Address;
import com.danilov.dbcourse.subscribe.Subscribe;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.*;

/**
 * Created by Semyon on 04.11.2014.
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "subscriber")
@NamedQuery(name = Subscriber.FIND_BY_LOGIN, query = "select a from Subscriber a where a.login = :login")
public class Subscriber {

    public static final String FIND_BY_LOGIN = "Subscriber.findByLogin";

    @Id
    @Column(name = "passport_number")
    private Long passport;

    @Column(unique = true)
    private String login;

    @JsonIgnore
    private String password;

    @Column
    private String name;

    @Column
    private String surname;

    @Column
    private String patronymic;

    @Column
    private long apartment;

    @ElementCollection(fetch = FetchType.EAGER)
    @Cascade(value=org.hibernate.annotations.CascadeType.ALL)
    @CollectionTable(name = "subscriber_subscribes", joinColumns = @JoinColumn(name = "subscriber_id"))
    private Set<Subscribe> subscribes;

    @ManyToOne
    private Address address;

    private String role = "USER_SUBSCRIBER";

    protected Subscriber() {

    }

    public Subscriber(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(final String patronymic) {
        this.patronymic = patronymic;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(final Address address) {
        this.address = address;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(final String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Set<Subscribe> getSubscribes() {
        return subscribes;
    }

    public void setSubscribes(final Set<Subscribe> subscribes) {
        this.subscribes = subscribes;
    }

    public Long getPassport() {
        return passport;
    }

    public void setPassport(Long passport) {
        this.passport = passport;
    }

    public String getLogin() {
        return login;
    }

    public String getRole() {
        return role;
    }

    public void setRole(final String role) {
        this.role = role;
    }

    public void setLogin(final String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getApartment() {
        return apartment;
    }

    public void setApartment(final long apartment) {
        this.apartment = apartment;
    }

    public List<Subscribe> getActualSubscribes() {
        List<Subscribe> subscribes1 = new ArrayList<>();
        Date d = new Date();
        for (Subscribe subscribe : subscribes) {
            if (subscribe.getEndDate().after(d)) {
                subscribes1.add(subscribe);
            }
        }
        return subscribes1;
    }

    public void addSubscribe(final Subscribe subscribe) {
        if (subscribes == null) {
            subscribes = new HashSet<>();
        }
        subscribes.add(subscribe);
    }

}
