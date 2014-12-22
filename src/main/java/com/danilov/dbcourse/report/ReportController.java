package com.danilov.dbcourse.report;

import com.danilov.dbcourse.Pair;
import com.danilov.dbcourse.address.Address;
import com.danilov.dbcourse.address.AddressRepository;
import com.danilov.dbcourse.address.Region;
import com.danilov.dbcourse.magazine.Magazine;
import com.danilov.dbcourse.magazine.MagazineRepository;
import com.danilov.dbcourse.postman.PostmanRepository;
import com.danilov.dbcourse.subscribe.Subscribe;
import com.danilov.dbcourse.subscribe.SubscribeRepository;
import com.danilov.dbcourse.subscriber.Subscriber;
import com.danilov.dbcourse.subscriber.SubscriberRepository;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Semyon on 21.12.2014.
 */
@Controller
@RequestMapping("/secured/reports")
public class ReportController {

    @Autowired
    private SubscribeRepository subscribeRepository;

    @Autowired
    private SubscriberRepository subscriberRepository;

    @Autowired
    private MagazineRepository magazineRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private PostmanRepository postmanRepository;

    @RequestMapping("")
    public String all(final HttpServletRequest request) {
        return "reports/reports";
    }

    @RequestMapping("/first")
    public String first(final Model model, final HttpServletRequest request) {
        List<Magazine> magazineList = magazineRepository.findAll();
        Date d = new Date();
        List<Pair> pairs = new LinkedList<>();
        for (Magazine magazine : magazineList) {
            List<Subscribe> subscribes = subscribeRepository.subscribesByMagazineAndTillDate(magazine, d);
            int size = subscribes.size();
            Pair p = new Pair();
            p.first = magazine;
            p.second = size;
            pairs.add(p);
        }
        model.addAttribute("pairs", pairs);
        return "reports/first";
    }

    @RequestMapping("/second")
    public String secondView() {
        return "reports/second";
    }

    @RequestMapping("/third")
    public String third(final Model model, final @RequestParam(required = false) String name, final @RequestParam(required = false) String surname, final @RequestParam(required = false) String patronymic) {
        if (name != null) {
            model.addAttribute("name", name);
            model.addAttribute("surname", surname);
            model.addAttribute("patronymic", patronymic);
            Subscriber subscriber = subscriberRepository.findByAll(name, surname, patronymic);
            if (subscriber != null) {
                model.addAttribute("subscribes", subscriber.getSubscribes());
            }
        }
        return "reports/third";
    }


    @RequestMapping(value = "/second", produces = "application/json")
    @ResponseBody
    public JSONObject secondJSON(final @RequestParam() String street, final @RequestParam() String house) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", addressRepository.getByStreet(street, house));
        return jsonObject;
    }

    @RequestMapping(value = "/fourth", produces = "application/json")
    @ResponseBody
    public JSONObject fourth() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", postmanRepository.getAll().size());
        return jsonObject;
    }


    @RequestMapping(value = "/fifth", produces = "application/json")
    @ResponseBody
    public JSONObject fifth() {
        JSONObject jsonObject = new JSONObject();
        List<Region> regions = addressRepository.getAllRegions();

        long regionNumber = -1;
        int quantity = -1;

        for (Region region : regions) {
            List<Address> addresses = addressRepository.getByRegion(region);
            int quantityByRegion = 0;
            for (Address address : addresses) {
                List<Subscriber> subscribers = subscriberRepository.findByAddress(address);
                for (Subscriber subscriber : subscribers) {
                    List<Subscribe> subscribes = subscriber.getActualSubscribes();
                    quantityByRegion += subscribes.size();
                }
            }
            if (quantity == -1 || quantity < quantityByRegion) {
                quantity = quantityByRegion;
                regionNumber = region.getRegionNumber();
            }
        }
        if (regionNumber != -1) {
            jsonObject.put("result", "Максимальное количество на участке номер: " + regionNumber);
        } else {
            jsonObject.put("result", "Никто ничего не выписывает");
        }
        return jsonObject;
    }

}
