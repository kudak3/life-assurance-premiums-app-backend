package com.ellachihwa.lapa.controller.mobile;

import com.ellachihwa.lapa.dto.ClaimDto;
import com.ellachihwa.lapa.model.Client;
import com.ellachihwa.lapa.model.InsuranceClaim;
import com.ellachihwa.lapa.service.InsuranceClaimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/claims")
public class ClaimsMobileController {
    @Autowired
    private InsuranceClaimService claimService;

    @GetMapping
    public List<InsuranceClaim> getClaims() {
        return claimService.getClaims();
    }

    @PostMapping
    public void registerClaim(@RequestBody ClaimDto claimDto) {
        System.out.println("====" + claimDto);

        claimService.saveClaim(claimDto);
    }
}
