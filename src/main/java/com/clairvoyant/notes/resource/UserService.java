package com.clairvoyant.notes.resource;


import com.clairvoyant.notes.VO.UserVO;
import com.clairvoyant.notes.model.Users;
import com.clairvoyant.notes.repo.RoleRepository;
import com.clairvoyant.notes.repo.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserService {

    @Autowired
    private UsersRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @RequestMapping("/users")
    public List<Users> getNotesInfo(@PathVariable Integer userid) throws Exception {
        List<Users> all = userRepository.findAll();
        return all;
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, value = "/user/add")
    public Users addUser(@RequestBody UserVO userVO) throws Exception {


         Optional<Users> byEmail = userRepository.findByEmail(userVO.getEmail());




        Users user = new Users();
        user.setEmail(userVO.getEmail());
        user.setName(userVO.getLastName());
        user.setLastName(userVO.getLastName());

        user.setActive(1);
        Users savedUser = null;
        if (userVO.getPassword().equals(userVO.getPasswordConfirmation())) {
            user.setPassword(userVO.getPassword());
            savedUser = userRepository.save(user);
        }

        return savedUser;
    }

}