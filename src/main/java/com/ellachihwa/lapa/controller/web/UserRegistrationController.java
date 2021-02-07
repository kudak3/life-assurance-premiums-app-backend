package com.ellachihwa.lapa.controller.web;

import com.ellachihwa.lapa.dto.UserDto;
import com.ellachihwa.lapa.model.Role;

import com.ellachihwa.lapa.model.User;
import com.ellachihwa.lapa.service.RoleService;
import com.ellachihwa.lapa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {

    private UserService userService;

    public UserRegistrationController(UserService userService) {
        super();
        this.userService = userService;
    }

    @Autowired
    private RoleService roleService;



    @GetMapping
    public String showRegistrationForm(Model model) {

        UserDto userDto = new UserDto();
        List<Role> roles = roleService.getRoles();
        model.addAttribute("roles",roles);
        model.addAttribute("userDto",userDto);

        return "registration";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("userDto") UserDto userDto) {
        User user = userService.save(userDto);


            if(user == null)
                return "redirect:/registration?error";
            else return "redirect:/registration?success";


    }
}
