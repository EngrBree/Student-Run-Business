package com.bree.ordermanagement.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
    private String orderId;
    private String status;
    private List<OrderedItemsDto> orderedItemsDtoList;
    private PaymentDto paymentDto;
    private CustomerDto customerDto;

    public PaymentDto getPaymentDto() {
        return paymentDto;
    }

    public void setPaymentDto(PaymentDto paymentDto) {
        this.paymentDto = paymentDto;
    }

    public List<OrderedItemsDto> getOrderedItemsDtoList() {
        return orderedItemsDtoList;
    }


    public void setOrderedItemsDtoList(List<OrderedItemsDto> orderedItemsDtoList) {
        this.orderedItemsDtoList = orderedItemsDtoList;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
