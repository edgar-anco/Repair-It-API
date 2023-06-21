package com.acme.webserviceslinerepair.technician.service;

import com.acme.webserviceslinerepair.shared.exception.ResourceNotFoundException;
import com.acme.webserviceslinerepair.shared.exception.ResourceValidationException;
import com.acme.webserviceslinerepair.technician.domain.model.entity.Technician;
import com.acme.webserviceslinerepair.technician.domain.persistence.TechnicianRepository;
import com.acme.webserviceslinerepair.technician.domain.service.TechnicianService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;
@Service
public class TechnicianServiceImpl implements TechnicianService {
    private final static String ENTITY = "Technician";

    private final TechnicianRepository technicianRepository;

    private final Validator validator;

    public TechnicianServiceImpl(TechnicianRepository technicianRepository, Validator validator) {
        this.technicianRepository = technicianRepository;
        this.validator = validator;
    }

    @Override
    public List<Technician> getAll(){return technicianRepository.findAll();}

    @Override
    public Technician getById(Long technicianId){
        return technicianRepository.findById(technicianId)
                .orElseThrow(()-> new ResourceNotFoundException(ENTITY, technicianId));
    }

    @Override
    public Page<Technician> getAll(Pageable pageable) {
        return technicianRepository.findAll(pageable);
    }

    @Override
    public Technician getByUsername(String username){
        return technicianRepository.findByUsername(username);
    }


    @Override
    public List<Technician> getByNameAndLastName(String names, String lastNames){
        return technicianRepository.findByNamesAndLastNamesContaining(names, lastNames);
    }

    @Override
    public Technician create(Technician request){
        Set<ConstraintViolation<Technician>> violations = validator.validate(request);
        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        try{
            return technicianRepository.save(request);
        }
        catch (Exception e){
            throw new ResourceValidationException(ENTITY, "An error occurred while saving technician");
        }
    }

    @Override
    public Technician update(Long technicianId, Technician request){
        Set<ConstraintViolation<Technician>> violations = validator.validate(request);
        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        try{
            return technicianRepository.findById(technicianId)
                    .map(technician->
                            technicianRepository.save(
                                    technician.withUsername(request.getUsername())
                                            .withNames(request.getNames())
                                            .withLastNames(request.getLastNames())
                                            .withEmail(request.getEmail())
                                            .withPassword(request.getPassword())
                                            .withAddress(request.getAddress())
                                            .withCellPhoneNumber(request.getCellPhoneNumber())
                            )).orElseThrow(()-> new ResourceNotFoundException(ENTITY, technicianId));
        }
        catch (Exception e){
            throw new ResourceValidationException(ENTITY, "An error occurred while updating technician");
        }
    }

    @Override
    public ResponseEntity<?> delete(Long technicianId) {
        return technicianRepository.findById(technicianId).map(technician -> {
            technicianRepository.delete(technician);
                    return ResponseEntity.ok().build();
                }
        ).orElseThrow(() -> new ResourceNotFoundException(ENTITY, technicianId));
    }

}

