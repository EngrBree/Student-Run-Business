package com.bree.ordermanagement.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDto {
    private Long id;
    private String CustomerId;
    private double amount;
    private String PaymentStatus;
    private String PaymentGateway;
}
