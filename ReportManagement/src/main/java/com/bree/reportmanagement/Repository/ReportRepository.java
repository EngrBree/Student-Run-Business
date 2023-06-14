package com.bree.reportmanagement.Repository;

import com.bree.reportmanagement.Model.Report;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ReportRepository extends ReactiveCrudRepository<Report,String> {
}
