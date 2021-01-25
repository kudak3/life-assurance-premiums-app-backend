package com.ellachihwa.lapa.service;

import com.ellachihwa.lapa.model.Client;
import com.ellachihwa.lapa.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }


    public List<Client> getClients(){
        return clientRepository.findAll();
    }

    public void saveClient(Client client){
        clientRepository.save(client);
    }

    public void deleteClient(Long id){
        clientRepository.deleteById(id);
    }

    public void updateClient(Client client){
        clientRepository.save(client);
    }


}
