package com.bree.reportmanagement.Model;

import lombok.*;
import org.springframework.data.relational.core.mapping.Table;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@With
@Data
@Table(name = "reports")
public class ReportEntry {

    private long id;
    private Order order;
    private double totalAmount;
    private int totalQuantity;

    public ReportEntry(Order order, double totalAmount, int totalQuantity) {
        this.order = order;
        this.totalAmount = totalAmount;
        this.totalQuantity = totalQuantity;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }




}
