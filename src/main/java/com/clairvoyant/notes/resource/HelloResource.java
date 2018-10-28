package com.clairvoyant.notes.resource;


import com.clairvoyant.notes.repo.NotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/rest/hello")
@RestController
public class HelloResource {

    @Autowired
    private NotesRepository notesRepository;

    @GetMapping("/all")
    public String hello() {
        return "All Notes";
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/secured/all")
    public String securedHello() {
        return "Secured Hello";
    }


    //Copy of method in HomeController
//    @RequestMapping("/secured/note/{noteid}/user/{userid}")
//    public Note getNotesInfo(@PathVariable Integer noteid, @PathVariable Integer userid) throws Exception {
//        Note allNotes = notesRepository.findByNoteidAndUserid(noteid,userid);
//        return allNotes;
//    }

    @GetMapping("/secured/alternate")
    public String alternate() {
        return "alternate";
    }
}
