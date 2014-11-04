package com.danilov.dbcourse.magazine;

import com.danilov.dbcourse.subscribe.Subscribe;
import com.danilov.dbcourse.subscribe.SubscribeRepository;
import com.danilov.dbcourse.subscriber.Subscriber;
import com.danilov.dbcourse.subscriber.SubscriberRepository;
import com.danilov.dbcourse.subscriber.SubscriberService;
import com.danilov.dbcourse.support.web.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Semyon on 04.11.2014.
 */
@Controller
@RequestMapping("/magazines")
public class MagazineController {

    @Autowired
    private MagazineRepository magazineRepository;

    @Autowired
    private SubscribeRepository subscribeRepository;

    @Autowired
    private SubscriberRepository subscriberRepository;

    @RequestMapping("/all")
    public String showAllMagazines(final Model model) {
        model.addAttribute("magazines", magazineRepository.findAll());
        return "magazine/magazines";
    }


    @RequestMapping(value = "/subscribe", method = RequestMethod.GET)
    public String subscribe(final Model model, final @RequestParam(required = true, value = "id") Long id) {
        Magazine magazine = magazineRepository.findById(id);
        model.addAttribute("magazine", magazine);
        return "magazine/subscribe";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addMagazine(final Model model) {
        return "magazine/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addMagazinePost(final Model model, final @RequestParam(required = true, value = "magazineName") String magazineName) {
        Magazine magazine = new Magazine();
        magazine.setName(magazineName);
        try {
            magazineRepository.save(magazine);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/magazines/all";
    }

    @RequestMapping(value = "/subscribe", method = RequestMethod.POST)
    public String subscribePost(final HttpServletRequest request, final Model model, final @RequestParam(required = true, value = "id") Long id, final @RequestParam(required = true, value = "endDate") String endDate) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Magazine magazine = magazineRepository.findById(id);
        Subscriber subscriber = (Subscriber) request.getSession().getAttribute("subscriber");
        Subscribe subscribe = new Subscribe();
        subscribe.setStartDate(new Date());
        subscribe.setSubscriber(subscriber);
        Date _endDate = null;
        try {
            _endDate = simpleDateFormat.parse(endDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        subscribe.setEndDate(_endDate);
        subscribe.setMagazine(magazine);
        try {
            subscribeRepository.save(subscribe);
            subscriber.addSubscribe(subscribe);
            subscriberRepository.merge(subscriber);
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("magazines", magazineRepository.findAll());
        model.addAttribute("message", new Message("Подписка оформлена", Message.Type.SUCCESS));
        return "magazine/magazines";
    }

}
