package com.danilov.dbcourse.subscriber;

import com.danilov.dbcourse.account.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collections;

/**
 * Created by Semyon on 04.11.2014.
 */
public class SubscriberService implements UserDetailsService {

    @Autowired
    private SubscriberRepository subscriberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Subscriber subscriber = subscriberRepository.findByLogin(username);
        if(subscriber == null) {
            throw new UsernameNotFoundException("user not found");
        }
        return createUser(subscriber);
    }

    public void signin(Subscriber subscriber) {
        SecurityContextHolder.getContext().setAuthentication(authenticate(subscriber));
    }

    private Authentication authenticate(Subscriber subscriber) {
        return new UsernamePasswordAuthenticationToken(createUser(subscriber), null, Collections.singleton(createAuthority(subscriber)));
    }

    private User createUser(Subscriber subscriber) {
        return new User(subscriber.getLogin(), subscriber.getPassword(), Collections.singleton(createAuthority(subscriber)));
    }

    private GrantedAuthority createAuthority(Subscriber subscriber) {
        return new SimpleGrantedAuthority(subscriber.getRole());
    }


}
