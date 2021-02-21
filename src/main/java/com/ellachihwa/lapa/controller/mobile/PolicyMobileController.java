package com.ellachihwa.lapa.controller.mobile;

import com.ellachihwa.lapa.model.Policy;
import com.ellachihwa.lapa.service.PolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/policies")
public class PolicyMobileController {


    @Autowired
    private PolicyService policyService;

    @GetMapping
    private List<Policy> getPolicies(){
        return policyService.getPolicies();
    }
}
