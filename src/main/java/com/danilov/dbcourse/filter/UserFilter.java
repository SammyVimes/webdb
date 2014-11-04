package com.danilov.dbcourse.filter;

import com.danilov.dbcourse.account.UserService;
import com.danilov.dbcourse.subscriber.SubscriberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.Collection;

/**
 * Created by Semyon on 04.11.2014.
 */
public class UserFilter implements Filter {

    @Autowired
    private UserService userService;

    @Autowired
    private SubscriberService subscriberService;

    @Override
    public void init(final FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(final ServletRequest servletRequest, final ServletResponse servletResponse, final FilterChain filterChain) throws IOException, ServletException {
        doFilter((HttpServletRequest) servletRequest, (HttpServletResponse) servletResponse, filterChain);
    }

    public void doFilter(final HttpServletRequest servletRequest, final HttpServletResponse servletResponse, final FilterChain filterChain) throws IOException, ServletException {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        Principal principal = servletRequest.getUserPrincipal();
//        if (principal != null) {
//            String name = principal.getName();
//            Collection<SimpleGrantedAuthority> authorities = (Collection<SimpleGrantedAuthority>) authentication.getAuthorities();
//            subscriberService.loadUserByUsername(name);
//        }
//        filterChain.doFilter(servletRequest, servletResponse);
    }
    @Override
    public void destroy() {

    }

}
