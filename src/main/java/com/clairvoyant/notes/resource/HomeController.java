package com.clairvoyant.notes.resource;


import com.clairvoyant.notes.model.Note;
import com.clairvoyant.notes.repo.NotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalTime;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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

    @RequestMapping("/secured/note/{noteid}/user/{userid}")
    public Note getNotesInfo(@PathVariable Integer noteid,@PathVariable Integer userid) throws Exception {
        Note allNotes = notesRepository.findByNoteidAndUserid(noteid,userid);
        return allNotes;
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, value = "/notes")
    public void addNote(@RequestBody Note notes, UriComponentsBuilder ucBuilder) throws Exception {
        Note save = notesRepository.save(notes);
        System.out.println(ucBuilder.toString());
    }

    @RequestMapping(method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE, value = "/note/{noteid}")
    public void deleteStudent(@PathVariable int noteid) {
        notesRepository.delete(noteid);
    }


        @RequestMapping("/test/sseTest")
        public SseEmitter handleRequest () {

            final SseEmitter emitter = new SseEmitter();
            ExecutorService service = Executors.newSingleThreadExecutor();
            service.execute(() -> {
                for (int i = 0; i < 500; i++) {
                    try {
                        emitter.send(LocalTime.now().toString() , MediaType.TEXT_PLAIN);

                        Thread.sleep(200);
                    } catch (Exception e) {
                        e.printStackTrace();
                        emitter.completeWithError(e);
                        return;
                    }
                }
                emitter.complete();
            });

            return emitter;
        }
}
