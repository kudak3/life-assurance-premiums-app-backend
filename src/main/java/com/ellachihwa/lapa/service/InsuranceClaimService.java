package com.ellachihwa.lapa.service;


import com.ellachihwa.lapa.model.InsuranceClaim;
import com.ellachihwa.lapa.repository.InsuranceClaimRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InsuranceClaimService {

    final
    InsuranceClaimRepository insuranceClaimRepository;

    public InsuranceClaimService(InsuranceClaimRepository insuranceClaimRepository) {
        this.insuranceClaimRepository = insuranceClaimRepository;
    }

    public List<InsuranceClaim> getClaims(){
        return insuranceClaimRepository.findAll();
    }

    public void saveClaim(InsuranceClaim claim){
        System.out.println("====================");
        System.out.println(claim);
        insuranceClaimRepository.save(claim);
    }

    public void deleteClaimById(Long id){
        insuranceClaimRepository.deleteById(id);
    }

    public void updateClaim(InsuranceClaim claim){
        insuranceClaimRepository.save(claim);
    }

}
