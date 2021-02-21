package com.ellachihwa.lapa.controller.web;

import com.ellachihwa.lapa.model.Client;
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

    @GetMapping("list")
    public String listClients(Model model) {
        model.addAttribute("clients", clientService.getClients());
        return "admin/client/list";
    }

    @GetMapping("add")
    public String addPage(Model model) {

        Client client = new Client();
        model.addAttribute("client", client);
        model.addAttribute("gender", Gender.values());



        return "admin/client/add";
    }

    @GetMapping("view/{id}")
    public String viewPage(@PathVariable Long id, Model model) {

        Client client = clientService.getClient(id);
        model.addAttribute("client",client);

        return "admin/client/view";
    }

    @PostMapping("save")
    public String saveEmployee(@ModelAttribute("client") Client client) {
        // save employee to database
        clientService.saveClient(client);
        return "redirect:/clients/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteEm(@PathVariable(value = "id") long id) {

        // call delete employee payment-type
        clientService.deleteClient(id);
        return "redirect:/clients/list";
    }
}
