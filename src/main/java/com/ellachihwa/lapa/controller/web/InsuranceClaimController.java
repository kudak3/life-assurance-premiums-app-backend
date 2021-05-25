package com.ellachihwa.lapa.controller.web;

import com.ellachihwa.lapa.dto.ClaimDto;
import com.ellachihwa.lapa.model.*;
import com.ellachihwa.lapa.repository.InsuranceClaimRepository;
import com.ellachihwa.lapa.repository.PolicyRepository;
import com.ellachihwa.lapa.service.ClientService;
import com.ellachihwa.lapa.service.CoverageService;
import com.ellachihwa.lapa.service.InsuranceClaimService;
import com.ellachihwa.lapa.service.PolicyService;
import com.ellachihwa.lapa.utils.ClaimStatus;
import com.ellachihwa.lapa.utils.Gender;
import com.ellachihwa.lapa.utils.Plan;
import com.ellachihwa.lapa.utils.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("claims/")
public class InsuranceClaimController {
    @Autowired
    private InsuranceClaimService claimService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private PolicyService policyService;

    @Autowired
    private CoverageService coverageService;

    @Autowired
    private InsuranceClaimRepository claimRepository;




    @GetMapping("list")
    public String getAllClaims(Model model){
       claimRepository.updateAllClaims();
        model.addAttribute("claims",claimService.getClaims());
        return "/admin/claim/list";
    }

    @PostMapping("save")
    public String saveClaim(@ModelAttribute("claim") ClaimDto claim) {
        // save claim to database
        claimService.saveClaim(claim);
        return "redirect:/claims/list";
    }



        @GetMapping("/delete/{id}")
        public String deleteClaim(@PathVariable(value = "id") Long id) {

        // call delete claim
        claimService.deleteClaimById(id);
        return "redirect:/claims/list";
    }

    @GetMapping("add")
    public String addPage(Model model) {


        ClaimDto claim = new ClaimDto();
        List<Client> clients = clientService.getClients();
        List<PolicyCoverage> policyCovers = coverageService.getCoverageList();
        model.addAttribute("claim", claim);
        model.addAttribute("clients",clients);
        model.addAttribute("policyCovers",policyCovers);
        model.addAttribute("statuses", ClaimStatus.values());

        return "admin/claim/add";
    }

    @GetMapping("approve/{id}")
    public String approve(@PathVariable(value = "id") Long id) {


        // call approve method
        claimService.approveClaim(id);
        return "redirect:/claims/list";
    }

    @GetMapping("decline/{id}")
    public String decline(@PathVariable(value = "id") Long id) {


        // call decline method
        claimService.declineClaim(id);
        return "redirect:/claims/list";
    }

}
