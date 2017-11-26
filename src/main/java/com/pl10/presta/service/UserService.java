package com.pl10.presta.service;

import com.pl10.presta.entity.UserRole;
import com.pl10.presta.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("userService")
public class UserService implements UserDetailsService{

    @Autowired
    @Qualifier("userRepository")
    private UserRepository userRepository;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.pl10.presta.entity.User user = userRepository.findByUsername(username);
        List<GrantedAuthority> authorities = buildAuthority(user.getUserRole());
        return buildUser(user, authorities);
    }

    private User buildUser(com.pl10.presta.entity.User user, List<GrantedAuthority> authorities) {
        return new User(user.getUsername(), user.getPassword(), user.isEnabled(), true, true, true, authorities);
    }

    private List<GrantedAuthority> buildAuthority(Set<UserRole> userRoles) {
        Set<GrantedAuthority> auths = new HashSet<GrantedAuthority>();

        for (UserRole userRole : userRoles) {
            auths.add(new SimpleGrantedAuthority(userRole.getRole()));
        }
        return new ArrayList<GrantedAuthority>(auths);
    }

    public com.pl10.presta.entity.User getUserByUsername(String username){
        return userRepository.findByUsername(username);
    }


}
