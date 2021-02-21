package com.ellachihwa.lapa.controller.web;

import com.ellachihwa.lapa.model.Client;
import com.ellachihwa.lapa.model.Payment;
import com.ellachihwa.lapa.service.ClientService;
import com.ellachihwa.lapa.service.PaymentTypeService;
import com.ellachihwa.lapa.service.PaymentService;
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

    @Autowired
    private ClientService clientService;

    @Autowired
    private PaymentTypeService paymentTypeService;

    @GetMapping("/list")
    public String getPayments(Model model){
        model.addAttribute("payments", paymentService.getPayments());
        return "admin/payment/list";
    }


    @GetMapping("add")
    public String addPage(Model model) {
        List<Client> clients = clientService.getClients();



        Payment payment = new Payment();
        model.addAttribute("payment", payment);
        model.addAttribute("paymentType", paymentTypeService.getPaymentTypes());
        model.addAttribute("clients",clients);

        return "admin/payment/add";
    }

    @PostMapping("save")
    public String savePayment(@ModelAttribute("payment") Payment payment) {
        // save payment to database
        paymentService.savePayment(payment);
        return "redirect:/payments/list";
    }

    @GetMapping("/delete/{id}")
    public String deletePayment(@PathVariable(value = "id") long id) {

        // call delete payment methods
        paymentService.deletePayment(id);
        return "redirect:/";
    }
}
