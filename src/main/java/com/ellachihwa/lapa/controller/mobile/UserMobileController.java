package com.ellachihwa.lapa.controller.mobile;

import com.ellachihwa.lapa.dto.UserDto;
import com.ellachihwa.lapa.model.User;
import com.ellachihwa.lapa.service.UserService;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserMobileController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @PostMapping
    public User signUpUser(@RequestBody UserDto userDto) {

     return userService.save(userDto);
    }
    @PutMapping
    public User updateUser(@RequestBody User user){

        return userService.updateUSer(user);
    }


    @GetMapping("/{id}")
    public User getOneUser(@PathVariable("id")Long id){
        return userService.getUser(id);

    }





}
