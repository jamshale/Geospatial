package com.geospatial.services.impl;

import com.geospatial.entities.Client;
import com.geospatial.repositories.ClientRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService{



    @Autowired
    private ClientRepo clientRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Client client = this.clientRepo.findClientByUsername(username);
        UserBuilder builder = null;
        if(client!=null){
            builder = org.springframework.security.core.userdetails.User.withUsername(username);
            builder.password(client.getPassword());
            builder.roles(client.getRoles());
        } else{
            throw new UsernameNotFoundException("User not found.");
            
        }
        return builder.build();

    }

}