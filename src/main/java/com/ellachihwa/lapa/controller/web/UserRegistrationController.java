package com.ellachihwa.lapa.controller.web;

import com.ellachihwa.lapa.dto.UserDto;
import com.ellachihwa.lapa.model.Client;
import com.ellachihwa.lapa.model.Role;

import com.ellachihwa.lapa.model.User;
import com.ellachihwa.lapa.service.RoleService;
import com.ellachihwa.lapa.service.UserService;
import com.ellachihwa.lapa.utils.Gender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("/users/")
public class UserRegistrationController {

    private UserService userService;

    public UserRegistrationController(UserService userService) {
        super();
        this.userService = userService;
    }

    @Autowired
    private RoleService roleService;



    @GetMapping("registration")
    public String showRegistrationForm(Model model) {

        UserDto userDto = new UserDto();
        List<Role> roles = roleService.getRoles();
        model.addAttribute("roles",roles);
        model.addAttribute("userDto",userDto);

        return "registration";
    }

    @PostMapping("registration")
    public String registerUserAccount(@ModelAttribute("userDto") UserDto userDto){
        System.out.println("here");
        User user = userService.save(userDto);


            if(user == null)
                return "redirect:/users/registration?error";
            else return "redirect:/";


    }

    @GetMapping("list")
    public String listUsers(Model model) {
        model.addAttribute("users", userService.getUsers());
        return "admin/user/list";
    }

    @GetMapping("add")
    public String addPage(Model model) {


        UserDto user = new UserDto();
        model.addAttribute("user", user);
        model.addAttribute("roles",roleService.getRoles());




        return "admin/user/add";
    }

    @PostMapping("save")
    public String saveUser(@ModelAttribute("user") UserDto userDto) {

        if(!userDto.getPassword().equals(userDto.getConfirmPassword()))
            return "redirect:/users/add?error";
        // save user to database
       if(userService.save(userDto) == null)
           return "redirect:/users/add?email";

        return "redirect:/users/list";
    }




}
