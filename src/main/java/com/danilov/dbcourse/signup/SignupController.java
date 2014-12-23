package com.danilov.dbcourse.signup;

import javax.validation.Valid;

import com.danilov.dbcourse.address.Address;
import com.danilov.dbcourse.address.AddressRepository;
import com.danilov.dbcourse.subscriber.Subscriber;
import com.danilov.dbcourse.subscriber.SubscriberRepository;
import com.danilov.dbcourse.subscriber.SubscriberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.danilov.dbcourse.account.*;
import com.danilov.dbcourse.support.web.*;

@Controller
public class SignupController {

    private static final String SIGNUP_VIEW_NAME = "signup/signup";

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private SubscriberRepository subscriberRepository;
	
	@Autowired
	private UserService userService;

    @Autowired
    private SubscriberService subscriberService;

    @Autowired
    private AddressRepository addressRepository;
	
	@RequestMapping(value = "signup")
	public String signup(Model model) {
		model.addAttribute("signupForm", new SignupFormSubscriber());
        return SIGNUP_VIEW_NAME;
	}
	
	@RequestMapping(value = "signup", method = RequestMethod.POST)
	public String signup(@Valid @ModelAttribute SignupFormSubscriber form, Errors errors, RedirectAttributes ra) {
        if (errors.hasErrors()) {
            return SIGNUP_VIEW_NAME;
        }
        Subscriber subscriber = new Subscriber(form.getLogin(), form.getPassword());
        Address address = addressRepository.getByStreet(form.getStreet(), form.getHouse());
        if (address == null) {
            address = new Address();
            address.setStreet(form.getStreet());
            address.setHouse(form.getHouse());
            addressRepository.saveAddress(address);
        }
        subscriber.setAddress(address);
        subscriber.setName(form.getName());
        subscriber.setSurname(form.getSurname());
        subscriber.setPatronymic(form.getPatronymic());
        subscriber.setPassport(form.getPassport());
        subscriber.setApartment(form.getAppartment());
        subscriber = subscriberRepository.save(subscriber);
        subscriberService.signin(subscriber);
        // see /WEB-INF/i18n/messages.properties and /WEB-INF/views/homeSignedIn.html
        MessageHelper.addSuccessAttribute(ra, "signup.success");
        return "redirect:/";
	}

    @RequestMapping(value = "signupSubscriber", method = RequestMethod.POST)
    public String signupSubscriber(@Valid @ModelAttribute SignupFormSubscriber form, Errors errors, RedirectAttributes ra) {
        if (errors.hasErrors()) {
            return SIGNUP_VIEW_NAME;
        }
        Subscriber subscriber = new Subscriber(form.getLogin(), form.getPassword());
        Address address = addressRepository.getByStreet(form.getStreet(), form.getHouse());
        if (address == null) {
            address = new Address();
            address.setStreet(form.getStreet());
            address.setHouse(form.getHouse());
            addressRepository.saveAddress(address);
        }
        subscriber.setAddress(address);
        subscriber.setName(form.getName());
        subscriber.setSurname(form.getSurname());
        subscriber.setPatronymic(form.getPatronymic());
        subscriber.setApartment(form.getAppartment());
        subscriber = subscriberRepository.save(subscriber);
        subscriberService.signin(subscriber);
        // see /WEB-INF/i18n/messages.properties and /WEB-INF/views/homeSignedIn.html
        MessageHelper.addSuccessAttribute(ra, "signup.success");
        return "redirect:/";
    }
}