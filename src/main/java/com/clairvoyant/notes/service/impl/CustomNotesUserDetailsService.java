package com.clairvoyant.notes.service.impl;

import com.clairvoyant.notes.model.Role;
import com.clairvoyant.notes.model.Users;
import com.clairvoyant.notes.repo.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomNotesUserDetailsService implements UserDetailsService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> optionalUsers = usersRepository.findByEmail(username);
        optionalUsers.orElseThrow(() -> new UsernameNotFoundException("Username not found"));
        Users users = optionalUsers.get();

        List<SimpleGrantedAuthority> roles = new ArrayList<>();
        if (users == null) {
            throw new UsernameNotFoundException(username);
        }
        if (users.getRoles() != null) {

            for (Role role : users.getRoles()) {

                roles.add(new SimpleGrantedAuthority(role.getRole()));
            }


        }

        return new User(username, users.getPassword(), roles);
    }
}
