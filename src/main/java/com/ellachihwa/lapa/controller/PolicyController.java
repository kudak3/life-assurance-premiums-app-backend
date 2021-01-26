package com.ellachihwa.lapa.controller;

import com.ellachihwa.lapa.model.Client;
import com.ellachihwa.lapa.model.Policy;
import com.ellachihwa.lapa.repository.PolicyRepository;
import com.ellachihwa.lapa.service.PolicyService;
import com.ellachihwa.lapa.utils.Gender;
import com.ellachihwa.lapa.utils.Plan;
import com.ellachihwa.lapa.utils.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("policy/")
public class PolicyController {
    @Autowired
    PolicyService policyService;

    @GetMapping("list")
    public String getAllPolicies(Model model){
        model.addAttribute("policies",policyService.getPolicies());
        return "/admin/policy/list";
    }

    @PostMapping("save")
    public String savePolicy(@ModelAttribute("policy") Policy policy) {
        // save employee to database
        policyService.savePolicy(policy);
        return "redirect:/";
    }

    @GetMapping("delete/{id}")
    public String deleteEm(@PathVariable(value = "id") long id) {

        // call delete policy method
        policyService.deletePolicy(id);
        return "redirect:/admin/policy/list";
    }

    @GetMapping("add")
    public String addPage(Model model) {


        Policy policy = new Policy();
        model.addAttribute("policy", policy);
        model.addAttribute("statuses", Status.values());
        model.addAttribute("plans", Plan.values());
        return "admin/policy/add";
    }

}
