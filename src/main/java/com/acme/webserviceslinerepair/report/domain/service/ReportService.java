package com.acme.webserviceslinerepair.report.domain.service;

import com.acme.webserviceslinerepair.report.domain.model.entity.Report;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ReportService {
    List<Report> getAll();
    Report getById(Long reportId);
    Page<Report> getAll(Pageable pageable);
    List<Report> getByTechnicianId(Long technicianId);
    List<Report> getByAppointmentId(Long appointmentId);
    Report create(Report report, Long technicianId, Long appointmentId);
    Report update(Long reportId, Report report);
    ResponseEntity<?> delete(Long reportId);
}
