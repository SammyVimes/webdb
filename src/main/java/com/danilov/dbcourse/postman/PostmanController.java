package com.danilov.dbcourse.postman;

import com.danilov.dbcourse.magazine.Magazine;
import com.danilov.dbcourse.magazine.MagazineType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Semyon on 14.12.2014.
 */
@Controller
@RequestMapping("/postmans")
public class PostmanController {

    @Autowired
    private PostmanRepository postmanRepository;

    @RequestMapping("/all")
    public String showAllMagazines(final Model model) {
        model.addAttribute("postmans", postmanRepository.getAll());
        return "postman/postmans";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addPostman(final Model model) {
        return "postman/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addPostmanPost(final Model model, final @RequestParam(required = true, value = "postmanName") String postmanName,
                                  @RequestParam(required = true, value = "passportNumber") final Long passportNumber) {
        Postman postman = new Postman();
        postman.setPassport(passportNumber);
        postman.setName(postmanName);
        try {
            postmanRepository.save(postman);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/postmans/all";
    }

}
