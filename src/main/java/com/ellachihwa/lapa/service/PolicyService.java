package com.ellachihwa.lapa.service;

import com.ellachihwa.lapa.dto.NotificationDto;
import com.ellachihwa.lapa.model.Client;
import com.ellachihwa.lapa.model.Policy;
import com.ellachihwa.lapa.repository.PolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PolicyService {

    @Autowired
    private NotificationService notificationService;

    final
    PolicyRepository policyRepository;

    public PolicyService(PolicyRepository policyRepository) {
        this.policyRepository = policyRepository;
    }

    public List<Policy> getPolicies(){
        return policyRepository.findAll();
    }

    public void savePolicy(Policy policy){
        String message = policy.getName() +" " + "has been created." + policy.getDescription();
        NotificationDto notificationDto = new NotificationDto("all-devices","New Policy Alert",message);
        notificationService.sendPnsToTopic(notificationDto);
        policyRepository.save(policy);
    }

    public void deletePolicy(Long policyId){
        policyRepository.deleteById(policyId);
    }

    public Policy getPolicy(Long id){
        return policyRepository.findById(id).orElse(null);

    }
}
