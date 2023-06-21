package com.acme.webserviceslinerepair.report.service;

import com.acme.webserviceslinerepair.appointment.domain.model.entity.Appointment;
import com.acme.webserviceslinerepair.appointment.domain.persistence.AppointmentRepository;
import com.acme.webserviceslinerepair.report.domain.model.entity.Report;
import com.acme.webserviceslinerepair.report.domain.persistence.ReportRepository;
import com.acme.webserviceslinerepair.report.domain.service.ReportService;
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
public class ReportServiceImpl implements ReportService {
    private final static String ENTITY = "Report";
    private final static String ENTITY2 = "Technician";
    private final static String ENTITY3 = "Appointment";
    @Autowired
    private final Validator validator;
    @Autowired
    private final ReportRepository reportRepository;

    @Autowired
    private final TechnicianRepository technicianRepository;

    @Autowired
    private final AppointmentRepository appointmentRepository;

    public ReportServiceImpl(Validator validator, ReportRepository reportRepository, TechnicianRepository technicianRepository, AppointmentRepository appointmentRepository) {
        this.validator = validator;
        this.reportRepository = reportRepository;
        this.technicianRepository = technicianRepository;
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public List<Report> getAll(){
        return reportRepository.findAll();
    }

    @Override
    public Report getById(Long reportId){
        return reportRepository.findById(reportId)
                .orElseThrow(()-> new ResourceNotFoundException(ENTITY, reportId));
    }

    @Override
    public Page<Report> getAll(Pageable pageable) {
        return reportRepository.findAll(pageable);
    }

    @Override
    public List<Report> getByTechnicianId(Long technicianId){
        var technician = technicianRepository.findById(technicianId);
        if(technician==null)
            throw new ResourceNotFoundException(ENTITY2, technicianId);

        return reportRepository.findByTechnicianId(technicianId);
    }

    @Override
    public List<Report> getByAppointmentId(Long appointmentId){
        var appointment = appointmentRepository.findById(appointmentId);
        if(appointment==null)
            throw new ResourceNotFoundException(ENTITY3, appointmentId);

        return reportRepository.findByAppointmentId(appointmentId);
    }
    @Override
    public Report create(Report request, Long technicianId, Long appointmentId){
        Set<ConstraintViolation<Report>> violations = validator.validate(request);
        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        var technician = technicianRepository.findById(technicianId);
        if(technician==null)
            throw new ResourceNotFoundException(ENTITY2, technicianId);

        var appointment = appointmentRepository.findById(appointmentId);
        if(appointment==null)
            throw new ResourceNotFoundException(ENTITY3, appointmentId);

        request.setTechnician(technician.get());
        request.setAppointment(appointment.get());
        return reportRepository.save(request);
    }

    @Override
    public Report update(Long reportId, Report request){
        Set<ConstraintViolation<Report>> violations = validator.validate(request);
        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);
        try{
            return reportRepository.findById(reportId)
                    .map(report -> reportRepository.save(
                            report.withObservation(request.getObservation())
                                    .withDiagnosis(request.getDiagnosis())
                                    .withRepairDescription(request.getRepairDescription())
                                    .withDate(request.getDate())
                    )).orElseThrow(()-> new ResourceNotFoundException(ENTITY, reportId));
        }catch (Exception e){
            throw new ResourceValidationException(ENTITY, "An error occurred while updating Report");
        }
    }
    @Override
    public ResponseEntity<?> delete(Long reportId) {
        return reportRepository.findById(reportId).map(report -> {
            reportRepository.delete(report);
                    return ResponseEntity.ok().build();
                }
        ).orElseThrow(() -> new ResourceNotFoundException(ENTITY, reportId));
    }

}
