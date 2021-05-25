package com.ellachihwa.lapa.controller.mobile;

import com.ellachihwa.lapa.dto.UserDto;
import com.ellachihwa.lapa.model.Client;
import com.ellachihwa.lapa.model.User;
import com.ellachihwa.lapa.service.ClientService;
import com.ellachihwa.lapa.service.UserService;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserMobileController {

    @Autowired
    private UserService userService;

    @Autowired
    private ClientService clientService;

    @GetMapping
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @PostMapping
    public ResponseEntity<User> signUpUser(@RequestBody UserDto userDto) {

        User user = userService.save(userDto);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        }
        else
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);

    }
    @PutMapping
    public User updateUser(@RequestBody User user){

        return userService.updateUSer(user);
    }


    @GetMapping("/{id}")
    public User getOneUser(@PathVariable("id")Long id){
        return userService.getUser(id);

    }

    @PostMapping("/login")
    public ResponseEntity<User> loginUser(@RequestParam("username") String userName, @RequestParam("password") String password, @RequestParam("token") String token) {


        User user = userService.loginUser(userName,password);
        if (user != null) {
            Client client = clientService.getClientByUserId(user.getId());
            client.setDeviceToken(token);
            clientService.updateClient(client);
                return new ResponseEntity<>(user, HttpStatus.OK);
        }
        else {
            userService.updateUSer(user);
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
    }






}
