package com.danilov.dbcourse.signup;

import org.hibernate.validator.constraints.*;

public class SignupFormSubscriber {

	private static final String NOT_BLANK_MESSAGE = "{notBlank.message}";
	private static final String LOGIN_MESSAGE = "{login.message}";

    @NotBlank(message = SignupFormSubscriber.LOGIN_MESSAGE)
	private String login;

    @NotBlank(message = SignupFormSubscriber.NOT_BLANK_MESSAGE)
	private String password;

    @NotBlank(message = SignupFormSubscriber.NOT_BLANK_MESSAGE)
    private String street;

    @NotBlank(message = SignupFormSubscriber.NOT_BLANK_MESSAGE)
    private String house;

    private Long appartment;

    private Long passport;

    public String getHouse() {
        return house;
    }

    public void setHouse(final String house) {
        this.house = house;
    }

    public Long getPassport() {
        return passport;
    }

    public void setPassport(final Long passport) {
        this.passport = passport;
    }

    public String getLogin() {
        return login;
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

    public Long getAppartment() {
        return appartment;
    }

    public void setAppartment(final Long appartment) {
        this.appartment = appartment;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(final String street) {
        this.street = street;
    }
}
