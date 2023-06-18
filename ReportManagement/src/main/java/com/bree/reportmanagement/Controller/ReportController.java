package com.bree.reportmanagement.Controller;

import com.bree.reportmanagement.Model.Report;
import com.bree.reportmanagement.Service.ReportService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/reported")
public class ReportController {
    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Report> newReport(@RequestBody Report report){
        return reportService.createReport(report);
    }
}
