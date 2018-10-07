package com.clairvoyant.notes.repo;

import com.clairvoyant.notes.model.Note;
import com.clairvoyant.notes.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {


}
