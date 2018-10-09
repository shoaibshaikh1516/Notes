package com.clairvoyant.notes.repo;

import com.clairvoyant.notes.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Integer> {
    Optional<Users> findByName(String username);
    Optional<Users> findByEmail(String username);
}
