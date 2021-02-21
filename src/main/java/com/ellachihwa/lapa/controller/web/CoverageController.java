package com.ellachihwa.lapa.controller.web;

import com.ellachihwa.lapa.model.Client;
import com.ellachihwa.lapa.model.Policy;
import com.ellachihwa.lapa.model.PolicyCoverage;
import com.ellachihwa.lapa.model.PolicyCoverageKey;
import com.ellachihwa.lapa.service.ClientService;
import com.ellachihwa.lapa.service.CoverageService;
import com.ellachihwa.lapa.service.PolicyService;
import com.ellachihwa.lapa.utils.CoverageStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/coverages")
public class CoverageController {

    final
    CoverageService coverageService;

    public CoverageController(CoverageService coverageService) {
        this.coverageService = coverageService;
    }

    @Autowired
    private ClientService clientService;

    @Autowired
    private PolicyService policyService;

    @GetMapping("/list")
    public String getCoverageList(Model model){
        model.addAttribute("coverages", coverageService.getCoverageList());
        return "admin/coverage/list";
    }


    @GetMapping("add")
    public String addPage(Model model) {

        List<Client> clientList = clientService.getClients();
        List<Policy> policies = policyService.getPolicies();


        PolicyCoverage coverage = new PolicyCoverage();
        model.addAttribute("coverage", coverage);
        model.addAttribute("coverageStatuses", CoverageStatus.values());
        model.addAttribute("clientList",clientList);
        model.addAttribute("policies",policies);

        return "admin/coverage/add";
    }



    @PostMapping("save")
    public String saveCoverage(@ModelAttribute("coverage") PolicyCoverage coverage) {
        // save payment to database
        coverageService.saveCoverage(coverage);
        return "redirect:/coverages/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteCoverage(@PathVariable(value = "id") PolicyCoverageKey id) {

        // call delete coverage methods
        coverageService.deleteCoverage(id);
        return "redirect:/";
    }


}
