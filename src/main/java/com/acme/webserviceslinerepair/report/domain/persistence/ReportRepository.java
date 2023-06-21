package com.acme.webserviceslinerepair.report.domain.persistence;

import com.acme.webserviceslinerepair.report.domain.model.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {
    List<Report> findByTechnicianId(Long technicianId);

    List<Report> findByAppointmentId(Long appointmentId);

}
