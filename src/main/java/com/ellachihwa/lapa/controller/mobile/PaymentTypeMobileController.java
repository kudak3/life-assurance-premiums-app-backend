package com.ellachihwa.lapa.controller.mobile;

import com.ellachihwa.lapa.model.PaymentType;
import com.ellachihwa.lapa.service.PaymentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/payment-methods")
public class PaymentTypeMobileController {
    @Autowired
    private PaymentTypeService paymentTypeService;

    @GetMapping
 public List<PaymentType> getPaymentMethods(){
     return paymentTypeService.getPaymentTypes();
 }
}
