package com.avis.payment.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avis.payment.model.Payment;
import com.avis.payment.repository.PaymentRepository;

import lombok.extern.slf4j.Slf4j;

import javax.transaction.Transactional;

@Service
@Slf4j
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;
    @Transactional
    public Payment savePayment(Payment payment){
        payment.setPaymentStatus("SUCCESS");
        return paymentRepository.save(payment);
    }
    public Optional<Payment> getPayment(Long paymentId){
        return paymentRepository.findById(paymentId);
    }

}
