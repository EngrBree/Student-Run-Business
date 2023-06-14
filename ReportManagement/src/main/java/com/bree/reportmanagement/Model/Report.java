package com.bree.reportmanagement.Model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Builder
@With
@Data
@Table(name = "reported")
public class Report {
    private Long id;
    @Id
    private String reportId;
    private List<ReportEntry> reportEntries;
    private int totalOrders;
    private double totalAmount;
    private int totalQuantity;


    public Report( List<ReportEntry> reportEntries, int totalOrders, double totalAmount, int totalQuantity) {
       // this.id = id;
        //this.reportId = reportId;
        this.reportEntries = reportEntries;
        this.totalOrders = totalOrders;
        this.totalAmount = totalAmount;
        this.totalQuantity = totalQuantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getTotalOrders() {
        return totalOrders;
    }

    public void setTotalOrders(int totalOrders) {
        this.totalOrders = totalOrders;
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

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public List<ReportEntry> getReportEntryList() {
        return reportEntries;
    }

    public void setReportEntryList(List<ReportEntry> reportEntries) {
        this.reportEntries = reportEntries;
    }
}
