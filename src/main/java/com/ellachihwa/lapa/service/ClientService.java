package com.ellachihwa.lapa.service;

import com.ellachihwa.lapa.model.*;
import com.ellachihwa.lapa.repository.ClientRepository;
import com.ellachihwa.lapa.repository.CoverageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ClientService {
private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Autowired
    private CoverageRepository coverageRepository;

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
                .orElse( null);

    }

    public Client getClient(Long id){
      return clientRepository.findById(id).orElse(null);

    }

    public List<PolicyCoverage> getClientCoverages(Long id){

      return  coverageRepository.findPolicyCoveragesByClient_Id(id).orElse(null);
            }

    public List<Payment> getPaymentHistory(Long id){
        return clientRepository.findById(id).map(client -> client.getPayments()).orElse(null);
    }

    public List<InsuranceClaim> getClaims(Long id){
        return clientRepository.findById(id).map(client -> client.getClaims()).orElse(null);
    }

//    public List<Policy> getPolicies(){
//        return clientRepository.findById(id).map(client -> client.getP)
//    }


}
