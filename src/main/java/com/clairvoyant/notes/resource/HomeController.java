package com.clairvoyant.notes.resource;


import com.clairvoyant.notes.Exception.NotesEmptyResultDataAccessException;
import com.clairvoyant.notes.model.Note;
import com.clairvoyant.notes.repo.NotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class HomeController {

    @Autowired
    private NotesRepository notesRepository;

    @RequestMapping("/note")
    public List<Note> getNoteInfo() throws Exception {
        List<Note> allNotes = notesRepository.findAll();
        return allNotes;
    }

    @RequestMapping("/note/{noteid}")
    public Note getNotesInfo(@PathVariable int noteid) throws Exception {
        Note allNotes = notesRepository.findOne(noteid);
        return allNotes;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, value = "/note/update/{noteid}")
    public void updateNote(@RequestBody Note notes, String noteid, UriComponentsBuilder ucBuilder) throws Exception {

        Note save = notesRepository.save(notes);
        System.out.println(ucBuilder.toString());
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(method = RequestMethod.DELETE, value = "/note/delete/{noteid}")
    public void deleteStudent(@PathVariable int noteid) {
        notesRepository.delete(noteid);
        //TODO :org.springframework.dao.EmptyResultDataAccessException: No class com.clairvoyant.notes.model.Note entity with id 6 exists!
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, value = "/note/add")
    public Note addNote(@RequestBody Note notes, UriComponentsBuilder ucBuilder) throws Exception {
        Note save = notesRepository.save(notes);
        System.out.println(ucBuilder.toString());
        if (save == null) {
            throw new NotesEmptyResultDataAccessException("Not Found");
        }
        return save;
    }

    @RequestMapping("/secured/note/{noteid}/user/{userid}")
    public Note getNotesInfo(@PathVariable Integer noteid, @PathVariable Integer userid) throws Exception {
        Note allNotes = notesRepository.findByNoteidAndUserid(noteid, userid);
        return allNotes;
    }

}
