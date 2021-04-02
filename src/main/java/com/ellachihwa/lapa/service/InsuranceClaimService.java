package com.ellachihwa.lapa.service;


import com.ellachihwa.lapa.model.InsuranceClaim;
import com.ellachihwa.lapa.repository.InsuranceClaimRepository;
import com.ellachihwa.lapa.utils.ClaimStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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

        claim.setNewEntry(true);
        claim.setClaimStatus(ClaimStatus.PENDING);
        claim.setDate(new Date());
        insuranceClaimRepository.save(claim);
    }

    public void deleteClaimById(Long id){
        insuranceClaimRepository.deleteById(id);
    }

    public InsuranceClaim approveClaim(Long id){
        return insuranceClaimRepository.findById(id)
                .map(claim -> {

                    claim.setClaimStatus(ClaimStatus.APPROVED);


                    return insuranceClaimRepository.save(claim);
                })
                .orElse( null);

    }

    public InsuranceClaim declineClaim(Long id){
        return insuranceClaimRepository.findById(id)
                .map(claim -> {

                    claim.setClaimStatus(ClaimStatus.DECLINED);


                    return insuranceClaimRepository.save(claim);
                })
                .orElse( null);

    }

}
