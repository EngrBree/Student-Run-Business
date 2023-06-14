package com.bree.paymentprocessingmanage.Service;

import com.bree.paymentprocessingmanage.Entity.Pay;
import com.bree.paymentprocessingmanage.Repository.PaymentRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }
    public Mono<Pay> processPay(Pay pay){
        return paymentRepository.save(pay);
    }
    public Mono<Pay> getPaymentById(String paymentId) {
        return paymentRepository.findById(paymentId);


/*}

    public Mono<Payment> processPayment(PaymentRequest paymentRequest) {
        Payment payment = new Payment(paymentRequest.getOrderId(), paymentRequest.getAmount());

        return paymentRepository.save(payment);
    }


    }*/
}}
