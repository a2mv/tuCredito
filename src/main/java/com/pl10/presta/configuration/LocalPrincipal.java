package com.pl10.presta.configuration;

import com.pl10.presta.entity.User;
import com.pl10.presta.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@Scope(value="session")
public class LocalPrincipal {

    private User user = null;

    @Autowired
    private UserService userService;

    public User getUser(){
        if(SecurityContextHolder.getContext().getAuthentication().isAuthenticated()){
            if(this.user==null){
                init(SecurityContextHolder.getContext().getAuthentication().getName());
            }
        }
        return this.user;
    }

    public boolean isIsset(){
        return this.user!=null;
    }

    private void init(String username){
        user = userService.getUserByUsername(username);
    }


}
