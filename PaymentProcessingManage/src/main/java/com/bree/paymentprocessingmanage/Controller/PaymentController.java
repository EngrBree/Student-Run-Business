package com.bree.paymentprocessingmanage.Controller;

import com.bree.paymentprocessingmanage.Entity.Pay;
import com.bree.paymentprocessingmanage.Service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/payments")
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }
    @PostMapping
    public Mono<Pay> createPay(@RequestBody Pay pay){
        return paymentService.processPay(pay);
    }
    @GetMapping("payments/{paymentId}")
    public  Mono<Pay> getPay(@PathVariable String paymentId){
        return paymentService.getPaymentById(paymentId);
    }
}
