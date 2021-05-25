package com.ellachihwa.lapa.service;

import com.ellachihwa.lapa.model.Client;
import com.ellachihwa.lapa.model.NotificationEntity;
import com.ellachihwa.lapa.model.Payment;
import com.ellachihwa.lapa.repository.ClientRepository;
import com.ellachihwa.lapa.repository.NotificationRepository;
import com.ellachihwa.lapa.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PaymentService {

    @Autowired
    private NotificationRepository notificationRepository;

    final
    PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }


    public List<Payment> getPayments() {
        return paymentRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    public Payment savePayment(Payment payment) {
        NotificationEntity notificationEntity = new NotificationEntity("New Payment created by " + payment.getAccountNumber(), new Date(), "insurance_claim", false, true);

        payment.setNewEntry(true);
        payment.setDate(new Date());
        notificationRepository.save(notificationEntity);
        return paymentRepository.save(payment);
    }

    public void deletePayment(Long id) {
        paymentRepository.deleteById(id);
    }

    public void updatePayment(Payment payment) {
        paymentRepository.save(payment);
    }

    public Payment getPayment(Long id) {
        return paymentRepository.findById(id).orElse(null);
    }


}
