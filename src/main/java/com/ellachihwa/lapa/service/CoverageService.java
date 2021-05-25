package com.ellachihwa.lapa.service;

import com.ellachihwa.lapa.dto.CoverDto;
import com.ellachihwa.lapa.model.Client;
import com.ellachihwa.lapa.model.NotificationEntity;
import com.ellachihwa.lapa.model.PolicyCoverage;
import com.ellachihwa.lapa.model.PolicyCoverageKey;
import com.ellachihwa.lapa.repository.CoverageRepository;
import com.ellachihwa.lapa.repository.NotificationRepository;
import com.ellachihwa.lapa.utils.CoverageStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.List;

@Service
public class CoverageService {

    @Autowired
    private ClientService clientService;

    @Autowired
    private NotificationRepository notificationRepository;

 final
 CoverageRepository coverageRepository;

    public CoverageService(CoverageRepository coverageRepository) {
        this.coverageRepository = coverageRepository;
    }


    public List<PolicyCoverage> getCoverageList(){

        return coverageRepository.findAll(Sort.by(Sort.Direction.DESC, "date"));
    }

    public PolicyCoverage saveCoverage(CoverDto coverDto){


        PolicyCoverage coverage = new PolicyCoverage(coverDto.getClient(),coverDto.getPolicy());
        String policyNumber = "CP" + coverage.getClient().getId() + coverage.getPolicy().getId();
        NotificationEntity notificationEntity = new NotificationEntity("New Policy cover created: " + policyNumber,new Date(),"insurance_claim",false,true);

        coverage.setPolicyNumber(policyNumber);
        coverage.setNewEntry(true);
        coverage.setDate(new Date());
        coverage.setStatus(CoverageStatus.PENDING);
        notificationRepository.save(notificationEntity);
        return coverageRepository.save(coverage);
    }

    public void deleteCoverage(PolicyCoverageKey id){
        coverageRepository.deleteById(id);
    }

    public void updateCoverage(PolicyCoverage coverage){
        coverageRepository.save(coverage);
    }


    public List<PolicyCoverage> getCoverageListByClient(Long id){

        return coverageRepository.findPolicyCoveragesByClient_Id(id);
    }

    public List<PolicyCoverage> getByUserId(Long id){

        Client client = clientService.getClientByUserId(id);
        if(client == null)
            return null;
        return coverageRepository.findPolicyCoveragesByClient_Id(client.getId());
    }

    public PolicyCoverage getByPolicyNumber(String policyNmuber){
        return coverageRepository.findPolicyCoverageByPolicyNumber(policyNmuber);
    }

}
