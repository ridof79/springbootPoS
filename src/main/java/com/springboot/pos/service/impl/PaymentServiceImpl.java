package com.springboot.pos.service.impl;

import com.springboot.pos.model.Payment;
import com.springboot.pos.repository.PaymentRepository;
import com.springboot.pos.service.PaymentService;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public void save(Payment payment) {
        paymentRepository.save(payment);
    }
}
