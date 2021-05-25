package com.ellachihwa.lapa.controller.web;

import com.ellachihwa.lapa.model.Client;
import com.ellachihwa.lapa.repository.ClientRepository;
import com.ellachihwa.lapa.service.UserService;
import com.ellachihwa.lapa.utils.Gender;
import com.ellachihwa.lapa.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/clients/")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private UserService userService;

    @GetMapping("list")
    public String listClients(Model model) {
        clientRepository.updateAllClients();
        model.addAttribute("clients", clientService.getClients());
        return "admin/client/list";
    }

    @GetMapping("add")
    public String addPage(Model model) {

        Client client = new Client();
        model.addAttribute("client", client);
        model.addAttribute("genderList", Gender.values());
        model.addAttribute("users",userService.getUsers());



        return "admin/client/add";
    }

    @GetMapping("view/{id}")
    public String viewPage(@PathVariable Long id, Model model) {

        Client client = clientService.getClient(id);
        model.addAttribute("client",client);

        return "admin/client/view";
    }

    @PostMapping("save")
    public String saveUser(@ModelAttribute("client") Client client) {
        System.out.println("========client");
        // save user to database
        clientService.saveClient(client);
        return "redirect:/clients/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteClient(@PathVariable(value = "id") long id) {

        // call delete client payment-type
        clientService.deleteClient(id);
        return "redirect:/clients/list";
    }
}
