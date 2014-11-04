package com.danilov.dbcourse.subscriber;

import com.danilov.dbcourse.subscribe.Subscribe;
import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

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
    @GeneratedValue
    private Long id;

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

    private String role = "USER_SUBSCRIBER";

    @OneToMany
    private Set<Subscribe> subscribes;

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

    public Long getId() {
        return id;
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


}
