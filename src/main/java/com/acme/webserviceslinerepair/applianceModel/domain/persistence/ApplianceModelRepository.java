package com.acme.webserviceslinerepair.applianceModel.domain.persistence;

import com.acme.webserviceslinerepair.applianceModel.domain.model.entity.ApplianceModel;
import com.acme.webserviceslinerepair.appointment.domain.model.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplianceModelRepository extends JpaRepository<ApplianceModel, Long> {
    List<ApplianceModel> findByName(String name);
    List<ApplianceModel> findByClientId(Long clientId);
}
