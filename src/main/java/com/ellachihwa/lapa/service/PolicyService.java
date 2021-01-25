package com.ellachihwa.lapa.service;

import com.ellachihwa.lapa.model.Policy;
import com.ellachihwa.lapa.repository.PolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PolicyService {

    final
    PolicyRepository policyRepository;

    public PolicyService(PolicyRepository policyRepository) {
        this.policyRepository = policyRepository;
    }

    public List<Policy> getPolicies(){
        return policyRepository.findAll();
    }

    public void savePolicy(Policy policy){
        policyRepository.save(policy);
    }

    public void deletePolicy(Long policyId){
        policyRepository.deleteById(policyId);
    }
}
