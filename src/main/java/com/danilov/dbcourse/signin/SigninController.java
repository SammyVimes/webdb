package com.danilov.dbcourse.signin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SigninController {

	@RequestMapping(value = "signin")
	public String signin() {
        return "signin/signin";
    }

    @RequestMapping(value = "signinSubscriber")
    public String signinSubs() {
        return "signin/subsSignin";
    }

}
