package com.bree.customermanage.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Customerdto {
    private String name;
    private String email;
    private  String adress;
    private  String orderId;
    private String customerId;

}
