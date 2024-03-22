package com.virtual.soft.axew.service;

import com.virtual.soft.axew.entity.Client;
import com.virtual.soft.axew.repository.ClientRepository;
import com.virtual.soft.axew.security.user.UserAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserAuthService implements UserDetailsService {

    private final ClientRepository repository;

    @Autowired
    public UserAuthService(ClientRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername (String email) throws UsernameNotFoundException {
        Client c = repository.findByEmail(email);
        
        if (c == null) {
            throw new UsernameNotFoundException("Invalid user: " + email);
        }

        return new UserAuth(c.getId(), c.getEmail(), c.getPassword(), c.getRoles());
    }
}
