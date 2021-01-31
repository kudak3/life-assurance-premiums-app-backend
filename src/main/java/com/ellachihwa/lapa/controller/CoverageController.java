package com.ellachihwa.lapa.controller;

import com.ellachihwa.lapa.model.PolicyCoverage;
import com.ellachihwa.lapa.model.PolicyCoverageKey;
import com.ellachihwa.lapa.service.CoverageService;
import com.ellachihwa.lapa.utils.PaymentType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/coverages")
public class CoverageController {

    final
    CoverageService coverageService;

    public CoverageController(CoverageService coverageService) {
        this.coverageService = coverageService;
    }

    @GetMapping("/list")
    public String getCoverageList(Model model){
        model.addAttribute("coverages", coverageService.getCoverageList());
        return "admin/coverage/list";
    }


    @GetMapping("add")
    public String addPage(Model model) {


        PolicyCoverage coverage = new PolicyCoverage();
        model.addAttribute("coverage", coverage);
        model.addAttribute("paymentType", PaymentType.values());

        return "admin/coverage/add";
    }



    @PostMapping("save")
    public String saveCoverage(@ModelAttribute("coverage") PolicyCoverage coverage) {
        // save payment to database
        coverageService.saveCoverage(coverage);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteCoverage(@PathVariable(value = "id") PolicyCoverageKey id) {

        // call delete coverage methods
        coverageService.deleteCoverage(id);
        return "redirect:/";
    }


}
