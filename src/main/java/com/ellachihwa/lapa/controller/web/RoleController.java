package com.ellachihwa.lapa.controller.web;

import com.ellachihwa.lapa.model.Client;
import com.ellachihwa.lapa.model.Role;
import com.ellachihwa.lapa.service.ClientService;
import com.ellachihwa.lapa.service.RoleService;
import com.ellachihwa.lapa.utils.Gender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("config/roles")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @GetMapping("list")
    public String listRoles(Model model) {
        model.addAttribute("roles", roleService.getRoles());
        return "admin/role/list";
    }

    @GetMapping("add")
    public String addPage(Model model) {


        Role role = new Role();
        model.addAttribute("role", role);
        return "admin/role/add";
    }

    @GetMapping("view/{id}")
    public String viewPage(@PathVariable Long id, Model model) {

        Role role = roleService.getRole(id);
        model.addAttribute("role",role);

        return "admin/role/view";
    }

    @PostMapping("save")
    public String saveRole(@ModelAttribute("role") Role role) {
        // save role to database
        roleService.saveRole(role);
        return "redirect:/roles/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteEm(@PathVariable(value = "id") long id) {

        // call delete employee payment-type
        roleService.deleteRole(id);
        return "redirect:/roles/list";
    }
}
