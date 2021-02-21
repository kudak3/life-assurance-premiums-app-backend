package com.ellachihwa.lapa.controller.web;

import com.ellachihwa.lapa.model.PaymentType;
import com.ellachihwa.lapa.service.PaymentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/payment-types/")
public class PaymentTypeController {
    @Autowired
    private PaymentTypeService paymentTypeService;

    @GetMapping("list")
    public String getPaymentMethods(Model model){
        model.addAttribute("paymentTypes", paymentTypeService.getPaymentTypes());
        return "admin/payment-type/list";
    }


    @GetMapping("add")
    public String addPage(Model model) {




        PaymentType paymentType = new PaymentType();

        model.addAttribute("paymentType", paymentType);


        return "admin/payment-type/add";
    }

    @PostMapping("save")
    public String savePayment(@ModelAttribute("paymentType") PaymentType payment) {
        // save payment to database
        paymentTypeService.savePaymentType(payment);
        return "redirect:/payment-types/list";
    }

    @GetMapping("/delete/{id}")
    public String deletePayment(@PathVariable(value = "id") long id) {

        // call delete payment methods
        paymentTypeService.deletePaymentType(id);
        return "redirect:/payment-types/list";
    }
}
