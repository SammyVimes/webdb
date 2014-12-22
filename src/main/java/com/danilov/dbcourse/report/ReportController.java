package com.danilov.dbcourse.report;

import com.danilov.dbcourse.Pair;
import com.danilov.dbcourse.magazine.Magazine;
import com.danilov.dbcourse.magazine.MagazineRepository;
import com.danilov.dbcourse.subscribe.Subscribe;
import com.danilov.dbcourse.subscribe.SubscribeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
    private MagazineRepository magazineRepository;

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

}
