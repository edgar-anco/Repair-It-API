package com.acme.webserviceslinerepair.appointment.service;

import com.acme.webserviceslinerepair.applianceModel.domain.persistence.ApplianceModelRepository;
import com.acme.webserviceslinerepair.appointment.domain.model.entity.Appointment;
import com.acme.webserviceslinerepair.appointment.domain.persistence.AppointmentRepository;
import com.acme.webserviceslinerepair.appointment.domain.service.AppointmentService;
import com.acme.webserviceslinerepair.client.domain.persistence.ClientRepository;
import com.acme.webserviceslinerepair.shared.exception.ResourceNotFoundException;
import com.acme.webserviceslinerepair.shared.exception.ResourceValidationException;
import com.acme.webserviceslinerepair.technician.domain.persistence.TechnicianRepository;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    private final static String ENTITY = "Appointment";
    private final static String ENTITY2 = "Client";
    private final static String ENTITY3 = "Technician";
    private final static String ENTITY4 = "ApplianceModel";

    private final Validator validator;
    @Autowired
    private final AppointmentRepository appointmentRepository;

    @Autowired
    private final ClientRepository clientRepository;
    @Autowired
    private final TechnicianRepository technicianRepository;
    @Autowired
    private final ApplianceModelRepository applianceModelRepository;

    public AppointmentServiceImpl(Validator validator, AppointmentRepository appointmentRepository, ClientRepository clientRepository, TechnicianRepository technicianRepository, ApplianceModelRepository applianceModelRepository) {
        this.validator = validator;
        this.appointmentRepository = appointmentRepository;
        this.clientRepository = clientRepository;
        this.technicianRepository = technicianRepository;
        this.applianceModelRepository = applianceModelRepository;
    }

    @Override
    public List<Appointment> getAll() {
        return appointmentRepository.findAll();
    }

    @Override
    public Appointment getById(Long appointmentId) {
        return appointmentRepository.findById(appointmentId)
                .orElseThrow(()-> new ResourceNotFoundException(ENTITY, appointmentId));
    }

    @Override
    public Page<Appointment> getAll(Pageable pageable) {
        return appointmentRepository.findAll(pageable);
    }

    @Override
    public Appointment create(Appointment request, Long clientId,Long technicianId, Long applianceModelId) {
        Set<ConstraintViolation<Appointment>> violations = validator.validate(request);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        var client = clientRepository.findById(clientId);
        if(client==null)
            throw new ResourceNotFoundException(ENTITY2, clientId);

        var technician = technicianRepository.findById(technicianId);
        if(technician==null)
            throw new ResourceNotFoundException(ENTITY3, technicianId);

        var applianceModel = applianceModelRepository.findById(applianceModelId);
        if(applianceModel==null)
            throw new ResourceNotFoundException(ENTITY4, applianceModelId);

        request.setClient(client.get());
        request.setTechnician(technician.get());
        request.setApplianceModel(applianceModel.get());
        return appointmentRepository.save(request);
    }

    @Override
    public Appointment update(Long appointmentId, Appointment request) {
        Set<ConstraintViolation<Appointment>> violations = validator.validate(request);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return appointmentRepository.findById(appointmentId)
                .map(appointment -> appointmentRepository.save(
                        appointment.withDateReserve(request.getDateReserve())
                                .withDateAttention(request.getDateAttention())
                                .withHour(request.getHour())
                                .withStatus(request.getStatus())
                )).orElseThrow(()-> new ResourceNotFoundException(ENTITY, appointmentId));
    }

    @Override
    public ResponseEntity<?> delete(Long appointmentId) {
        return appointmentRepository.findById(appointmentId).map(appointment -> {
            appointmentRepository.delete(appointment);
                    return ResponseEntity.ok().build();
                }
        ).orElseThrow(() -> new ResourceNotFoundException(ENTITY, appointmentId));
    }

    @Override
    public List<Appointment> getByClientId(Long clientId) {
        var client = clientRepository.findById(clientId);
        if(client==null)
            throw new ResourceNotFoundException(ENTITY2, clientId);

        return appointmentRepository.findByClientId(clientId);
    }

    @Override
    public List<Appointment> getByTechnicianId(Long technicianId) {
        var technician = technicianRepository.findById(technicianId);
        if(technician==null)
            throw new ResourceNotFoundException(ENTITY3, technicianId);

        return appointmentRepository.findByTechnicianId(technicianId);
    }

    @Override
    public Page<Appointment> getAllByClientId(Long clientId, Pageable pageable) {
        return appointmentRepository.findAll(pageable);
    }
}
