package com.ellachihwa.lapa.controller.mobile;

import com.ellachihwa.lapa.model.Client;
import com.ellachihwa.lapa.model.User;
import com.ellachihwa.lapa.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientMobileController {

    @Autowired
    private ClientService clientService;

    @GetMapping
    public List<Client> getClients() {
        return clientService.getClients();
    }

    @GetMapping("/{id}")
    public Client getClient(@PathVariable("id") Long id) {
        return clientService.getClient(id);

    }

    @PostMapping
    public Client registerClient(@RequestBody Client client){
        return clientService.saveClient(client);
    }

    @PutMapping
    public Client updateClient(@RequestBody Client client){
     return  clientService.updateClient(client);

    }


}