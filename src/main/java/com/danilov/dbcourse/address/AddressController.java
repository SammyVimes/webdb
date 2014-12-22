package com.danilov.dbcourse.address;

import com.danilov.dbcourse.postman.Postman;
import com.danilov.dbcourse.postman.PostmanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by Semyon on 04.11.2014.
 */
@Controller
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private PostmanRepository postmanRepository;

    @RequestMapping("/all")
    public String showAll(final Model model) {
        model.addAttribute("regions", addressRepository.getAllRegions());
        return "regions/regions";
    }

    @RequestMapping("/unset")
    public String showUnset(final Model model) {
        model.addAttribute("addresses", addressRepository.getUnset());
        model.addAttribute("regions", addressRepository.getAllRegions());
        return "regions/unset";
    }

    @RequestMapping(value = "/apply", method = RequestMethod.POST)
    public String apply(final Model model, @RequestParam(required = true, value = "addressId")final Long addressId, @RequestParam(required = true, value = "region")final Long regionId) {
        Region region = addressRepository.getRegionById(regionId);
        Address address = addressRepository.getAddressById(addressId);
        address.setRegion(region);
        addressRepository.updateAddress(address);
        return "redirect:/address/unset";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String editGet(final Model model, final @RequestParam(required = true, value="id") Long id) {
        Region region = addressRepository.getRegionById(id);
        List<Address> addresses = addressRepository.getByRegion(region);
        model.addAttribute("addresses", addresses);
        model.addAttribute("region", region);
        List<Postman> postmans = postmanRepository.getAll();
        model.addAttribute("postmans", postmans);
        return "regions/regionEdit";
    }

}
