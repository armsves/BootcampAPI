package com.example.BootcampAPI.services;

import com.example.BootcampAPI.repositories.UsersRepository;
import com.example.BootcampAPI.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (!usersRepository.findByUsername(username).isPresent()) {
            throw new UsernameNotFoundException("User does not exist");
        }
        //return new CustomUserDetails(userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User does not exist")));
        return new CustomUserDetails(usersRepository.findByUsername(username).get());
    }
}