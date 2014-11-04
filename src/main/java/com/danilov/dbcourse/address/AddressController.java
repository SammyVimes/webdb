package com.danilov.dbcourse.address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Semyon on 04.11.2014.
 */
@Controller
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressRepository addressRepository;

    @RequestMapping("/all")
    public String showAll(final Model model) {
        model.addAttribute("regions", addressRepository.getAllRegions());
        return "regions/regions";
    }

}
