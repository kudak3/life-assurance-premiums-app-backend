package com.ellachihwa.lapa.controller.mobile;

import com.ellachihwa.lapa.model.Payment;
import com.ellachihwa.lapa.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentsMobileController {
    @Autowired
    private PaymentService paymentService;

    @GetMapping("/{id}")
    public Payment getPayment(@PathVariable("id") Long id){
        return paymentService.getPayment(id);
    }

    @PostMapping
    public Payment makePayment(@RequestBody Payment payment){
        return paymentService.savePayment(payment);

    }
}
