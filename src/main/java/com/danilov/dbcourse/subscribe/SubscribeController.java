package com.danilov.dbcourse.subscribe;

import com.danilov.dbcourse.subscriber.Subscriber;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Semyon on 04.11.2014.
 */
@Controller
@RequestMapping("/subscribes")
public class SubscribeController {

    @RequestMapping("/all")
    public String getAll(final HttpServletRequest request, final Model model) {
        Subscriber subscriber = (Subscriber) request.getSession().getAttribute("subscriber");
        model.addAttribute("subscribes", subscriber.getSubscribes());
        return "subscribes/subscribes";
    }

}
