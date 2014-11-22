package com.danilov.dbcourse.signup;

import org.hibernate.validator.constraints.*;

public class SignupFormSubscriber {

	private static final String NOT_BLANK_MESSAGE = "{notBlank.message}";
	private static final String LOGIN_MESSAGE = "{login.message}";

    @NotBlank(message = SignupFormSubscriber.LOGIN_MESSAGE)
	private String login;

    @NotBlank(message = SignupFormSubscriber.NOT_BLANK_MESSAGE)
	private String password;

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

}
