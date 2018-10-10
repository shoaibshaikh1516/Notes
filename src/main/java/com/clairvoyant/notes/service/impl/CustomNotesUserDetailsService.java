package com.clairvoyant.notes.service.impl;

import com.clairvoyant.notes.model.CustomNotesUserDetails;
import com.clairvoyant.notes.model.Users;
import com.clairvoyant.notes.repo.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomNotesUserDetailsService implements UserDetailsService {

    @Autowired
    private UsersRepository usersRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> optionalUsers = usersRepository.findByEmail(username);
        optionalUsers.orElseThrow(() -> new UsernameNotFoundException("Username not found"));
        return optionalUsers
                .map(CustomNotesUserDetails::new).orElseThrow(() -> new UsernameNotFoundException("Username/Email not found in" +
                        "database for " + username));
    }
}
