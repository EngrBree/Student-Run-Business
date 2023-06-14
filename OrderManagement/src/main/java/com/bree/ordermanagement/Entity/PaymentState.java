package com.bree.ordermanagement.Entity;

import lombok.*;

@AllArgsConstructor
@Data
@With
@Builder
@NoArgsConstructor
public class PaymentState {
    private Long id;
    private String CustomerId;
    private double amount;
    private String PaymentStatus;
    private String PaymentGateway;
}
