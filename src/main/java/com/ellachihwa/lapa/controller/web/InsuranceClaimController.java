package com.ellachihwa.lapa.controller.web;

import com.ellachihwa.lapa.model.Client;
import com.ellachihwa.lapa.model.InsuranceClaim;
import com.ellachihwa.lapa.model.Policy;
import com.ellachihwa.lapa.repository.PolicyRepository;
import com.ellachihwa.lapa.service.ClientService;
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


    @GetMapping("list")
    public String getAllClaims(Model model){
        model.addAttribute("claims",claimService.getClaims());
        return "/admin/claim/list";
    }

    @PostMapping("save")
    public String saveClaim(@ModelAttribute("claim") InsuranceClaim claim) {
        // save claim to database
        claimService.saveClaim(claim);
        return "redirect:/claims/list";
    }

    @GetMapping("delete/{id}")
    public String deleteClaim(@PathVariable(value = "id") long id) {

        // call delete policy payment-type
        claimService.deleteClaimById(id);
        return "redirect:/admin/claim/list";
    }

    @GetMapping("add")
    public String addPage(Model model) {


        InsuranceClaim claim = new InsuranceClaim();
        List<Client> clients = clientService.getClients();
        List<Policy> policies = policyService.getPolicies();
        model.addAttribute("claim", claim);
        model.addAttribute("clients",clients);
        model.addAttribute("policies",policies);
        model.addAttribute("statuses", ClaimStatus.values());

        return "admin/claim/add";
    }

}
