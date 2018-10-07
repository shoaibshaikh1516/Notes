package com.clairvoyant.notes.repo;

import com.clairvoyant.notes.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotesRepository extends JpaRepository<Note, Integer> {

    Note findByNoteidAndUserid(Integer noteid,Integer userid);
}
