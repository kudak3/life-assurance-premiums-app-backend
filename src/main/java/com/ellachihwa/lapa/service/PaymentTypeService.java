package com.ellachihwa.lapa.service;

import com.ellachihwa.lapa.model.PaymentType;
import com.ellachihwa.lapa.repository.PaymentTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentTypeService {
    private final PaymentTypeRepository paymentTypeRepository;

    public PaymentTypeService(PaymentTypeRepository paymentTypeRepository) {
        this.paymentTypeRepository = paymentTypeRepository;
    }

    public List<PaymentType> getPaymentTypes(){
        return paymentTypeRepository.findAll();
    }

    public PaymentType savePaymentType(PaymentType payment){

        return paymentTypeRepository.save(payment);
    }

    public void deletePaymentType(Long id){
        paymentTypeRepository.deleteById(id);
    }

    public void updatePaymentType(PaymentType payment){
        paymentTypeRepository.save(payment);
    }

    public PaymentType getPaymentTypes(Long id) {
        return paymentTypeRepository.findById(id).orElse(null);
    }



}
