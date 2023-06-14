package com.bree.ordermanagement.Entity;

import com.bree.ordermanagement.Dto.CustomerDto;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;

@AllArgsConstructor
@Data
@With
@Builder
@NoArgsConstructor
@Table(name = "ordered")
public class Order {
    @Id
    @Column(value ="orderId")
    private String orderId;
    private Long id;
    private List<OrderedItems> orderedItemsList;
    private String status;
    private Customer customer;
    private String customerId;
    private String customerAddress;
    private String shipmentId;
    private  ShippingStatus shippingStatus;


    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(String shipmentId) {
        this.shipmentId = shipmentId;
    }

    public ShippingStatus getShippingStatus() {
        return shippingStatus;
    }

    public void setShippingStatus(ShippingStatus shippingStatus) {
        this.shippingStatus = shippingStatus;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<OrderedItems> getOrderedItemsList() {
        return orderedItemsList;
    }

    public void setOrderedItemsList(List<OrderedItems> orderedItemsList) {
        this.orderedItemsList = orderedItemsList;
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


    public void setCustomer(com.bree.customermanage.Model.Customer customer) {
    }

    public String getCustomerId() {
        this.customerId=customerId;
        return customerId;
    }

    public String getCustomerAddress() {
        this.customerAddress=customerAddress;
        return customerAddress;
    }
}
