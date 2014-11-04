package com.danilov.dbcourse.auth;

import com.danilov.dbcourse.subscriber.Subscriber;
import com.danilov.dbcourse.subscriber.SubscriberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Semyon on 04.11.2014.
 */
@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private SubscriberRepository subscriberRepository;

    @RequestMapping("/subscriber")
    public String auth(final HttpServletRequest request, final Model model, final String login, final String password) {
        Subscriber subscriber = subscriberRepository.findByLogin(login);
        if (subscriber == null) {
            model.addAttribute("error", "Пользователь не найден");
            return "signin/subsSignin";
        }
        if (subscriber.getPassword().equals(password)) {
            request.getSession().setAttribute("subscriber", subscriber);
            request.getSession().setAttribute("isAuth", true);
        } else {
            model.addAttribute("error", "Неверный пароль");
            return "signin/subsSignin";
        }
        return "home/home";
    }

}
