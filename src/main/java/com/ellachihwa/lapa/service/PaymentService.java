package com.ellachihwa.lapa.service;

import com.ellachihwa.lapa.model.Client;
import com.ellachihwa.lapa.model.Payment;
import com.ellachihwa.lapa.repository.ClientRepository;
import com.ellachihwa.lapa.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {


final
PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }


    public List<Payment> getPayments(){
        return paymentRepository.findAll();
    }

    public void savePayment(Payment payment){
        paymentRepository.save(payment);
    }

    public void deletePayment(Long id){
        paymentRepository.deleteById(id);
    }

    public void updatePayment(Payment payment){
        paymentRepository.save(payment);
    }


}
