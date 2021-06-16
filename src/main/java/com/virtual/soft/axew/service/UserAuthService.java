package com.virtual.soft.axew.service;

import com.virtual.soft.axew.entity.Client;
import com.virtual.soft.axew.security.user.UserAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserAuthService implements UserDetailsService {

    @Autowired
    private ClientService clientService;

    @Override
    public UserDetails loadUserByUsername (String email) throws UsernameNotFoundException {
        Client c = clientService.findByEmail(email);
        
        if (c == null) {
            throw new UsernameNotFoundException("Invalid user: " + email);
        }

        return new UserAuth(c.getId(), c.getEmail(), c.getPassword(), c.getRoles());
    }
}
