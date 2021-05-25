package com.ellachihwa.lapa.controller.web;

import com.ellachihwa.lapa.dto.CoverDto;
import com.ellachihwa.lapa.model.Client;
import com.ellachihwa.lapa.model.Policy;
import com.ellachihwa.lapa.model.PolicyCoverage;
import com.ellachihwa.lapa.model.PolicyCoverageKey;
import com.ellachihwa.lapa.repository.CoverageRepository;
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
    private CoverageRepository coverageRepository;

    @Autowired
    private PolicyService policyService;

    @GetMapping("/list")
    public String getCoverageList(Model model) {
        coverageRepository.updateAllCovers();
        model.addAttribute("coverages", coverageService.getCoverageList());
        return "admin/coverage/list";
    }


    @GetMapping("/add")
    public String addPage(Model model) {

        List<Client> clientList = clientService.getClients();
        List<Policy> policies = policyService.getPolicies();


        CoverDto coverage = new CoverDto();
        model.addAttribute("coverage", coverage);
        model.addAttribute("coverageStatuses", CoverageStatus.values());
        model.addAttribute("clientList", clientList);
        model.addAttribute("policies", policies);

        return "admin/coverage/add";
    }


    @PostMapping("/save")
    public String saveCoverage(@ModelAttribute("coverage") CoverDto coverDto) {
        // save payment to database
        coverageService.saveCoverage(coverDto);
        return "redirect:/coverages/list";
    }

    @GetMapping("/delete/{clientId}/{policyId}")
    public String deleteCoverage(@PathVariable(value = "clientId") Long clientId, @PathVariable(value = "policyId") Long policyId) {

        PolicyCoverageKey id = new PolicyCoverageKey(clientId, policyId);

        // call delete coverage methods
        coverageService.deleteCoverage(id);
        return "redirect:/coverages/list";
    }


}
