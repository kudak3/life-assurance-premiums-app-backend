package com.ellachihwa.lapa.controller.web;

import com.ellachihwa.lapa.model.PolicyCoverage;
import com.ellachihwa.lapa.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private InsuranceClaimRepository claimRepository;

    @Autowired
    private CoverageRepository coverageRepository;

    @Autowired
    private PolicyRepository policyRepository;

    @Autowired
    private NotificationRepository notificationRepository;


    @GetMapping
    public String viewHomePage(Model model) {
        model.addAttribute("users", userRepository.count());
        model.addAttribute("newUsers",userRepository.countUsersByNewEntryIsTrue());

        model.addAttribute("clients",clientRepository.count());
        model.addAttribute("newClients",clientRepository.countClientsByNewEntryIsTrue());

        model.addAttribute("claims",claimRepository.count());
        model.addAttribute("newClaims",claimRepository.countInsuranceClaimsByNewEntryIsTrue());

        model.addAttribute("coverages",coverageRepository.count());
        model.addAttribute("newCoverages",coverageRepository.countPolicyCoveragesByNewEntryIsTrue());

        model.addAttribute("payments",paymentRepository.count());
        model.addAttribute("newPayments",paymentRepository.countPaymentsByNewEntryTrue());

        model.addAttribute("policies",policyRepository.count());
        model.addAttribute("notifications",notificationRepository.count());


        return "index";
    }

    @GetMapping("/config")
    public String configurationPage(){
        return "admin/configurations";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

}
