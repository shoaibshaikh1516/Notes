package com.clairvoyant.notes.repo;

import com.clairvoyant.notes.model.Note;
import com.clairvoyant.notes.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByemailaddress(String username);
}
