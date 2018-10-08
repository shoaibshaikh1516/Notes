package com.clairvoyant.notes.resource;

import com.clairvoyant.notes.model.User;
import com.clairvoyant.notes.repo.UserRepository;
import com.clairvoyant.notes.vo.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class UserWebSocketController {


    @Autowired
    private UserRepository userRepository;


    @MessageMapping("/user")
    @SendTo("/topic/user")
    public UserResponse getUser(User user) {

//        List<User> allUsrs = userRepository.findAll();
        return new UserResponse(user.getEmailaddress());

    }
}