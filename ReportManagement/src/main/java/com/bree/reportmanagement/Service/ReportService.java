package com.bree.reportmanagement.Service;

import com.bree.reportmanagement.Client.OrderManageClient;
import com.bree.reportmanagement.Client.OrderManageImpl;
import com.bree.reportmanagement.Model.Order;
import com.bree.reportmanagement.Model.OrderItem;
import com.bree.reportmanagement.Model.Report;
import com.bree.reportmanagement.Model.ReportEntry;
import com.bree.reportmanagement.Repository.ReportRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportService {
    private final ReportRepository reportRepository;
    private final OrderManageClient orderManageClient;

    public ReportService(ReportRepository reportRepository, OrderManageClient orderManageClient) {
        this.reportRepository = reportRepository;
        this.orderManageClient = orderManageClient;
    }
    public Mono<Report>createReport(){
        return orderManageClient.getAllOrders()
                .flatMap(this::populateOrderItems)
                .collectList()
                .map(this::generateReportFromOrders)
                .flatMap(reportRepository::save);

    }
    private Mono<Order> populateOrderItems(Order order) {
        return orderManageClient.getOrderItems()
                .collectList()
                .map(orderItems -> {
                    order.setItems(orderItems);
                    return order;
                });
    }

    private Report generateReportFromOrders(List<Order> orders) {
        List<ReportEntry> reportEntries = orders.stream()
                .map(this::createReportEntry)
                .collect(Collectors.toList());

        int totalOrders = orders.size();
        double totalAmount = reportEntries.stream()
                .mapToDouble(ReportEntry::getTotalAmount)
                .sum();
        int totalQuantity = reportEntries.stream()
                .mapToInt(ReportEntry::getTotalQuantity)
                .sum();

        return new Report(reportEntries, totalOrders, totalAmount, totalQuantity);
    }

    private ReportEntry createReportEntry(Order order) {
        double totalAmount = order.getItems().stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();
        int totalQuantity = order.getItems().stream()
                .mapToInt(OrderItem::getQuantity)
                .sum();

        return new ReportEntry(order, totalAmount, totalQuantity);
    }
}
