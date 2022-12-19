package com.avis.payment.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.avis.payment.model.Payment;

@Repository
public interface PaymentRepository extends CrudRepository<Payment, Long> {

}
