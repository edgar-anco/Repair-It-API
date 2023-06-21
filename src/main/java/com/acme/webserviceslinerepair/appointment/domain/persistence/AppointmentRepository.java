package com.acme.webserviceslinerepair.appointment.domain.persistence;

import com.acme.webserviceslinerepair.appointment.domain.model.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByClientId(Long clientId);
    List<Appointment> findByTechnicianId(Long technicianId);
}
