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
import java.util.*;

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

    @RequestMapping(value = "/six", produces = "application/json")
    @ResponseBody
    public JSONObject six() {
        JSONObject jsonObject = new JSONObject();

        List<Magazine> magazines = magazineRepository.findAll();

        String result = "";

        for (Magazine magazine : magazines) {
            List<Subscribe> subscribes = subscribeRepository.subscribesByMagazine(magazine);
            int days = 0;
            for (Subscribe subscribe : subscribes) {
                Date start = subscribe.getStartDate();
                Date end = subscribe.getEndDate();
                long millis = end.getTime() - start.getTime();
                int _days = (int) (millis / (1000 * 60 * 60 * 24));
                days += _days;
            }
            int average = days / subscribes.size();
            result += "Издание: " + magazine.getName();
            result += ", среднее время подписки: " + average + "\n";
        }
        jsonObject.put("result", result);
        return jsonObject;
    }

    @RequestMapping(value = "/global")
    public String global(final Model model) {
        List<Region> regions = addressRepository.getAllRegions();

        List<Magazine> magazines = magazineRepository.findAll();
        for (Region region : regions) {
            Tuple tuple = new Tuple();

            tuple.third = region;

            tuple.first = region.getPostman();

            List<Triple> magazineToSubscribesList = new ArrayList<>();
            tuple.second = magazineToSubscribesList;

            for (Magazine magazine : magazines) {
                List<Subscriber> subscribers = subscribeRepository.subscribersByMagazineAndRegion(magazine, region);
                if (subscribers.isEmpty()) {
                    continue;
                }
                Triple magazineToSubscribes = new Triple();
                magazineToSubscribesList.add(magazineToSubscribes);
                List<Pair> pairs = new LinkedList<>();

                //данные по изданию, фёст = жернал, секонд - подписчик и его подписка
                magazineToSubscribes.first = magazine;
                magazineToSubscribes.second = pairs;

                int days = 0;
                int subscribesQuantity = 0;
                for (Subscriber subscriber : subscribers) {
                    Pair pair = new Pair();
                    pair.first = subscriber;
                    for (Subscribe subscribe : subscriber.getActualSubscribes()) {
                        if (subscribe.getMagazine().equals(magazine)) {
                            pair.second = subscribe;
                            pairs.add(pair);
                            subscribesQuantity++;
                            days += (subscribe.getEndDate().getTime() - subscribe.getStartDate().getTime()) / (1000 * 60 * 60 * 24);
                            break;
                        }
                    }
                }
                magazineToSubscribes.third = days / subscribesQuantity;
            }

        }
        return "reports/global";
    }

    private class Triple {

        private Object first;

        private Object second;

        private Object third;

        public Object getFirst() {
            return first;
        }

        public void setFirst(final Object first) {
            this.first = first;
        }

        public Object getSecond() {
            return second;
        }

        public void setSecond(final Object second) {
            this.second = second;
        }

        public Object getThird() {
            return third;
        }

        public void setThird(final Object third) {
            this.third = third;
        }

    }

    private class Tuple {

        private Object first;

        private Object second;

        private Object third;

        private Object fourth;

        public Object getFirst() {
            return first;
        }

        public void setFirst(final Object first) {
            this.first = first;
        }

        public Object getSecond() {
            return second;
        }

        public void setSecond(final Object second) {
            this.second = second;
        }

        public Object getThird() {
            return third;
        }

        public void setThird(final Object third) {
            this.third = third;
        }

        public Object getFourth() {
            return fourth;
        }

        public void setFourth(final Object fourth) {
            this.fourth = fourth;
        }
    }

}
