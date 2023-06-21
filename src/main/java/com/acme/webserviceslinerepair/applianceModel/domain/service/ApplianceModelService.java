package com.acme.webserviceslinerepair.applianceModel.domain.service;

import com.acme.webserviceslinerepair.applianceModel.domain.model.entity.ApplianceModel;
import com.acme.webserviceslinerepair.appointment.domain.model.entity.Appointment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ApplianceModelService {
    List<ApplianceModel> getAll();
    Page<ApplianceModel> getAllByClientId(Long clientId, Pageable pageable);
    Page<ApplianceModel> getAll(Pageable pageable);
    ApplianceModel getById(Long applianceModelId);
    ApplianceModel create(ApplianceModel applianceModel, Long clientId);
    ApplianceModel update(Long applianceModelId, ApplianceModel applianceModel);
    List<ApplianceModel> getByClientId(Long clientId);
    ResponseEntity<?> delete(Long applianceModelId);
    List<ApplianceModel> getByName(String name);

}
