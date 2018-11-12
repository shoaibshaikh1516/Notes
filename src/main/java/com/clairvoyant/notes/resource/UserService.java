package com.clairvoyant.notes.resource;


import com.clairvoyant.notes.Exception.NotesAlreadyPresentException;
import com.clairvoyant.notes.Exception.NotesConflictException;
import com.clairvoyant.notes.VO.UserVO;
import com.clairvoyant.notes.model.UserRole;
import com.clairvoyant.notes.model.Users;
import com.clairvoyant.notes.repo.RoleRepository;
import com.clairvoyant.notes.repo.UserRoleRepository;
import com.clairvoyant.notes.repo.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserService {

    @Autowired
    private UsersRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;

    @RequestMapping("/users")
    public List<Users> getNotesInfo(@PathVariable Integer userid) throws Exception {
        List<Users> all = userRepository.findAll();
        return all;
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, value = "/user/add")
    public Users addUser(@RequestBody  @Valid UserVO userVO) throws Exception {
         Optional<Users> byEmail = userRepository.findByEmail(userVO.getEmail());

         if(byEmail.isPresent())
         {
             throw new NotesAlreadyPresentException("Email Id Already Present");
         }
        Users user = new Users();
        user.setEmail(userVO.getEmail());
        user.setName(userVO.getLastName());
        user.setLastName(userVO.getLastName());
        user.setActive(1);
        Users savedUser = null;
        if (userVO.getPassword().equals(userVO.getPasswordConfirmation())) {
            String encryptedPassword = BCrypt.hashpw(userVO.getPassword(), BCrypt.gensalt());
            user.setPassword(encryptedPassword);
            savedUser = userRepository.save(user);
            UserRole userRole= new UserRole();
            userRole.setRoleId(userVO.getRole());
            userRole.setUserId(savedUser.getId());
            userRoleRepository.save(userRole);
        }else{
            throw new NotesConflictException("Passwoard Must match");
        }
        return savedUser;
    }
}
