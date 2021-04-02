package com.ellachihwa.lapa.controller.mobile;

import com.ellachihwa.lapa.dto.CoverDto;
import com.ellachihwa.lapa.model.Policy;
import com.ellachihwa.lapa.model.PolicyCoverage;
import com.ellachihwa.lapa.service.CoverageService;
import com.ellachihwa.lapa.service.PolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/policies")
public class PolicyMobileController {


    @Autowired
    private PolicyService policyService;

    @Autowired
    private CoverageService coverageService;

    @GetMapping
    private List<Policy> getPolicies(){
        return policyService.getPolicies();
    }
    
    @PostMapping
    private PolicyCoverage savePolicyCover(@RequestBody CoverDto coverDto){
        return coverageService.saveCoverage(coverDto);

    }

    @GetMapping("/{userId}")
    private List<PolicyCoverage> getPolicyCover(@PathVariable("userId") Long userId){
        return coverageService.getByUserId(userId);
    }



}
