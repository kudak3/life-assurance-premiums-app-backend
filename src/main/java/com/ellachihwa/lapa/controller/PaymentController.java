package com.ellachihwa.lapa.controller;

import com.ellachihwa.lapa.model.Client;
import com.ellachihwa.lapa.model.Payment;
import com.ellachihwa.lapa.service.PaymentService;
import com.ellachihwa.lapa.service.PolicyService;
import com.ellachihwa.lapa.utils.Gender;
import com.ellachihwa.lapa.utils.PaymentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/payments")
public class PaymentController {

    final
    PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/list")
    public String getPayments(Model model){
        model.addAttribute("payments", paymentService.getPayments());
        return "admin/payment/list";
    }


    @GetMapping("add")
    public String addPage(Model model) {


        Payment payment = new Payment();
        model.addAttribute("payment", payment);
        model.addAttribute("paymentType", PaymentType.values());

        return "admin/payment/add";
    }

    @PostMapping("save")
    public String savePayment(@ModelAttribute("payment") Payment payment) {
        // save payment to database
        paymentService.savePayment(payment);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deletePayment(@PathVariable(value = "id") long id) {

        // call delete payment methods
        paymentService.deletePayment(id);
        return "redirect:/";
    }
}
