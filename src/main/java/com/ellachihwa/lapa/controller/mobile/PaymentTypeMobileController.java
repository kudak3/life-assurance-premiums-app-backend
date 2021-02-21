package com.ellachihwa.lapa.controller.mobile;

import com.ellachihwa.lapa.model.PaymentType;
import com.ellachihwa.lapa.service.PaymentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/api/paymentMethod")
public class PaymentTypeMobileController {
    @Autowired
    private PaymentTypeService paymentTypeService;

 public List<PaymentType> getPaymentMethods(){
     return paymentTypeService.getPaymentTypes();
 }
}
