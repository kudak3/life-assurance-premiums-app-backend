package com.ellachihwa.lapa.service;


import com.ellachihwa.lapa.dto.ClaimDto;
import com.ellachihwa.lapa.dto.NotificationDto;
import com.ellachihwa.lapa.model.*;
import com.ellachihwa.lapa.repository.InsuranceClaimRepository;
import com.ellachihwa.lapa.repository.NotificationRepository;
import com.ellachihwa.lapa.utils.ClaimStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class InsuranceClaimService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private NotificationService notificationService;

    @Autowired ClientService clientService;

    @Autowired
    private CoverageService coverageService;

    final
    InsuranceClaimRepository insuranceClaimRepository;

    public InsuranceClaimService(InsuranceClaimRepository insuranceClaimRepository) {
        this.insuranceClaimRepository = insuranceClaimRepository;
    }

    public List<InsuranceClaim> getClaims(){
        List<InsuranceClaim> claims = insuranceClaimRepository.findAll();
        System.out.println(claims);
        return insuranceClaimRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    public void saveClaim(ClaimDto claimDto){
        System.out.println(claimDto);

        PolicyCoverage cover = coverageService.getByPolicyNumber(claimDto.getPolicyNumber());
        String notificationMessage = "New claim created by " + cover.getClient().getName() + " " + "for on policy " + cover.getPolicyNumber();
        NotificationEntity notificationEntity = new NotificationEntity( notificationMessage,new Date(),"insurance_claim",false,true);
        InsuranceClaim claim = new InsuranceClaim();
        claim.setPolicyCoverage(cover);
        claim.setDescription(claimDto.getDescription());
        claim.setAmount(claimDto.getAmount());



        claim.setNewEntry(true);
        claim.setClaimStatus(ClaimStatus.PENDING);
        claim.setDate(new Date());
        insuranceClaimRepository.save(claim);
        notificationRepository.save(notificationEntity);
    }

    public void deleteClaimById(Long id){
        insuranceClaimRepository.deleteById(id);
    }

    public InsuranceClaim approveClaim(Long id){
        return insuranceClaimRepository.findById(id)
                .map(claim -> {

                    claim.setClaimStatus(ClaimStatus.APPROVED);
                    String message = "Claim on policy " + claim.getPolicyCoverage().getPolicyNumber() + " has been approved.";
                    NotificationEntity notificationEntity = new NotificationEntity(message,new Date(),"insurance_claim",false,false);
                    NotificationDto notificationDto = new NotificationDto(claim.getPolicyCoverage().getClient().getDeviceToken(),"Insurance Claim Alert",message);
                    notificationService.sendPnsToDevice(notificationDto);
                    notificationRepository.save(notificationEntity);

                    return insuranceClaimRepository.save(claim);
                })
                .orElse( null);

    }

    public InsuranceClaim declineClaim(Long id){
        return insuranceClaimRepository.findById(id)
                .map(claim -> {

                    claim.setClaimStatus(ClaimStatus.DECLINED);
                    String message = "Claim on policy " + claim.getPolicyCoverage().getPolicyNumber() + " has been declined.";
                    NotificationEntity notificationEntity = new NotificationEntity(message,new Date(),"insurance_claim",false,false);
                    NotificationDto notificationDto = new NotificationDto(claim.getPolicyCoverage().getClient().getDeviceToken(),"Insurance Claim Alert",message);
                    notificationService.sendPnsToDevice(notificationDto);
                    notificationRepository.save(notificationEntity);

                    return insuranceClaimRepository.save(claim);
                })
                .orElse( null);

    }

}
