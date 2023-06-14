package com.bree.customermanage.Dto;

import com.bree.customermanage.Model.Customer;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Setter
@Getter
public class CustomerRequestDto {

    private Long id;
    private String requestId;
    private String orderId;
    private Customer customer;
    private String requestType;
    private String description;
}
