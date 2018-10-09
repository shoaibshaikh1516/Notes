package com.clairvoyant.notes.resource;


import com.clairvoyant.notes.model.Users;
import com.clairvoyant.notes.repo.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserDetails {

    @Autowired
    private UsersRepository userRepository;

    @RequestMapping("/user/{userid}")
    public List<Users> getNotesInfo(@PathVariable Integer userid) throws Exception {

        List<Users> all = userRepository.findAll();
        return all;

    }


//    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, value = "/scheduleevents")
//    public void addUser(@RequestBody List<Event> events) throws Exception {
//
//    }
//@PathVariable Integer userid, @RequestParam Integer subid
}
