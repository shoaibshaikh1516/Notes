package com.clairvoyant.notes.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

public class CustomNotesUserDetails extends Users implements UserDetails {

    public CustomNotesUserDetails(final Users users) {
        super(users);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getRole()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public String getUsername() {
        return super.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        CustomNotesUserDetails that = (CustomNotesUserDetails) obj;
        return Objects.equals(getId(), that.getId());
    }
}
