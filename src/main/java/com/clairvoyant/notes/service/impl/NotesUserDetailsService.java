package com.clairvoyant.notes.service.impl;

import com.clairvoyant.notes.model.NotesUserDetails;
import com.clairvoyant.notes.model.User;
import com.clairvoyant.notes.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class NotesUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser=userRepository.findByemailaddress(username);
        optionalUser.orElseThrow(()->new UsernameNotFoundException("User Not found!!"));

        NotesUserDetails notesUserDetails=   optionalUser.map(user->{
            return new NotesUserDetails(user);}).get();

        return notesUserDetails;
    }
}
