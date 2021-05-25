package com.ellachihwa.lapa.service;

import com.ellachihwa.lapa.model.*;
import com.ellachihwa.lapa.repository.ClientRepository;
import com.ellachihwa.lapa.repository.CoverageRepository;
import com.ellachihwa.lapa.repository.InsuranceClaimRepository;
import com.ellachihwa.lapa.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class ClientService {
private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Autowired
    private CoverageRepository coverageRepository;

    @Autowired
    private InsuranceClaimRepository claimRepository;

    @Autowired
    private NotificationRepository notificationRepository;

    public List<Client> getClients(){
        return clientRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    public Client saveClient(Client client){
        NotificationEntity notificationEntity = new NotificationEntity("New client created",new Date(),"client",false,true);
        notificationRepository.save(notificationEntity);
        return clientRepository.save(client);
    }

    public void deleteClient(Long id){
        NotificationEntity notificationEntity = new NotificationEntity("Client:" + id + "deleted",new Date(),"client",false,true);
        notificationRepository.save(notificationEntity);
        clientRepository.deleteById(id);
    }

    public Client updateClient(Client updatedClient){
        NotificationEntity notificationEntity = new NotificationEntity(  updatedClient.getFirstName() + " " + updatedClient.getLastName() + " updated",new Date(),"client",false,true);

        return clientRepository.findById(updatedClient.getId())
                .map(client -> {

                    client.setFirstName(updatedClient.getFirstName());
                    client.setLastName(updatedClient.getLastName());
                    client.setEmail(updatedClient.getEmail());
                    notificationRepository.save(notificationEntity);
                    return clientRepository.save(client);
                })
                .orElse( null);

    }

    public Client getClient(Long id){
      return clientRepository.findById(id).orElse(null);

    }

    public List<PolicyCoverage> getClientCoverages(Long id){

      return  coverageRepository.findPolicyCoveragesByClient_Id(id);
            }

    public List<Payment> getPaymentHistory(Long id){
        return clientRepository.findById(id).map(client -> client.getPayments()).orElse(null);
    }

    public List<InsuranceClaim> getClaims(Long id){
        return clientRepository.findById(id).map(client -> claimRepository.findInsuranceClaimsByPolicyCoverageClient(client)).orElse(null);
    }

    public Client getClientByUserId(Long id){
        System.out.println("===========");
        System.out.println(clientRepository.findClientByUserId(id).getPolicyCoverageList().get(0).getClient());
     return clientRepository.findClientByUserId(id);
    }


}
