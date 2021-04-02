package com.ellachihwa.lapa.controller.mobile;

import com.ellachihwa.lapa.model.*;
import com.ellachihwa.lapa.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

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
    @GetMapping("/{id}/coverages")
    public List<PolicyCoverage> getClientPolicyCoverages(@PathVariable("id") Long id){
        System.out.println("======coverages" + clientService.getClientCoverages(id));
        return clientService.getClientCoverages(id);
    }

    @GetMapping("/{id}/payments")
    public List<Payment> getClientPaymentHistory(@PathVariable("id") Long id){
        return clientService.getPaymentHistory(id);
    }

    @GetMapping("/{id}/claims")
    public List<InsuranceClaim> getClientClaims(@PathVariable("id") Long id){
        return clientService.getClaims(id);

    }

    @GetMapping("/user/{id}")
    public Client getClientByUser(@PathVariable("id") Long id){
        return clientService.getClientByUserId(id);
    }



}