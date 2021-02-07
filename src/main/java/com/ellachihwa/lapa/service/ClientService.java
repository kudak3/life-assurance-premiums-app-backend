package com.ellachihwa.lapa.service;

import com.ellachihwa.lapa.model.Client;
import com.ellachihwa.lapa.repository.ClientRepository;
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

    public Client saveClient(Client client){
       return clientRepository.save(client);
    }

    public void deleteClient(Long id){
        clientRepository.deleteById(id);
    }

    public Client updateClient(Client updatedClient){
        return clientRepository.findById(updatedClient.getId())
                .map(client -> {

                    client.setFirstName(updatedClient.getFirstName());
                    client.setLastName(updatedClient.getLastName());
                    client.setEmail(updatedClient.getEmail());

                    return clientRepository.save(client);
                })
                .orElseGet(() -> null);

    }

    public Client getClient(Long id){
        Client client = clientRepository.findById(id).orElse(null);

        if (client==null) {

            throw new RuntimeException("Cannot find Contact with id: " + id);

        }

        else return client;
    }


}
