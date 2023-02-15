package com.springproject.StudentManagementApi.Security;

import com.springproject.StudentManagementApi.Entity.User;
import com.springproject.StudentManagementApi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    //this method is overriden from UserDetailsService
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        //optional return type for findByEmail
        User existingUser = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found for the email :" + email));

        //email, password, authorities(here i have not specified any authorities hence it is set as an empty arraylist)
        //the line below is already provided by spring also the User class is provided by the UserDetails class
        return new org.springframework.security.core.userdetails.User(existingUser.getEmail(), existingUser.getPassword(),new ArrayList<>());

    }
}
