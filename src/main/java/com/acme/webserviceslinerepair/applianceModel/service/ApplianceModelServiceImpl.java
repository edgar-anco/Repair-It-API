package com.acme.webserviceslinerepair.applianceModel.service;

import com.acme.webserviceslinerepair.applianceModel.domain.model.entity.ApplianceModel;
import com.acme.webserviceslinerepair.applianceModel.domain.persistence.ApplianceModelRepository;
import com.acme.webserviceslinerepair.applianceModel.domain.service.ApplianceModelService;
import com.acme.webserviceslinerepair.client.domain.persistence.ClientRepository;
import com.acme.webserviceslinerepair.shared.exception.ResourceNotFoundException;
import com.acme.webserviceslinerepair.shared.exception.ResourceValidationException;
import lombok.var;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Service
public class ApplianceModelServiceImpl implements ApplianceModelService {
    private final static String ENTITY = "ApplianceModel";
    private final static String ENTITY2 = "Client";

    private final Validator validator;

    private final ApplianceModelRepository applianceModelRepository;

    private final ClientRepository clientRepository;
    public ApplianceModelServiceImpl(Validator validator, ClientRepository clientRepository, ApplianceModelRepository applianceModelRepository) {
        this.validator = validator;
        this.clientRepository = clientRepository;
        this.applianceModelRepository = applianceModelRepository;
    }

    @Override
    public List<ApplianceModel> getAll() {
        return applianceModelRepository.findAll();
    }

    @Override
    public ApplianceModel getById(Long applianceModelId) {
        return applianceModelRepository.findById(applianceModelId)
                .orElseThrow(()-> new ResourceNotFoundException(ENTITY, applianceModelId));
    }
    @Override
    public ApplianceModel create(ApplianceModel request, Long clientId) {
        Set<ConstraintViolation<ApplianceModel>> violations = validator.validate(request);
        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        var client = clientRepository.findById(clientId);
        if(client==null)
            throw new ResourceNotFoundException(ENTITY2, clientId);

        request.setClient(client.get());
        return applianceModelRepository.save(request);
    }

    @Override
    public ApplianceModel update(Long applianceModelId, ApplianceModel request) {
        Set<ConstraintViolation<ApplianceModel>> violations = validator.validate(request);
        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);
        try{
        return applianceModelRepository.findById(applianceModelId)
                .map(applianceModel -> applianceModelRepository.save(
                        applianceModel.withName(request.getName())
                                .withModel(request.getModel())
                                .withUrlToImage(request.getUrlToImage())
                )).orElseThrow(()-> new ResourceNotFoundException(ENTITY, applianceModelId));
    }catch (Exception e){
            throw new ResourceValidationException(ENTITY, "An error occurred while updating applianceModel");
        }
}

    @Override
    public ResponseEntity<?> delete(Long applianceModelId) {
        return applianceModelRepository.findById(applianceModelId).map(applianceModel -> {
            applianceModelRepository.delete(applianceModel);
                    return ResponseEntity.ok().build();
                }
        ).orElseThrow(() -> new ResourceNotFoundException(ENTITY, applianceModelId));
    }

    @Override
    public List<ApplianceModel> getByName(String name) {
        return applianceModelRepository.findByName(name);
    }

    @Override
    public List<ApplianceModel> getByClientId(Long applianceModelId) {
        var client = clientRepository.findById(applianceModelId);
        if(client==null)
            throw new ResourceNotFoundException(ENTITY2, applianceModelId);

        return applianceModelRepository.findByClientId(applianceModelId);
    }
    @Override
    public Page<ApplianceModel> getAllByClientId(Long clientId, Pageable pageable) {
        return applianceModelRepository.findAll(pageable);
    }

    @Override
    public Page<ApplianceModel> getAll(Pageable pageable) {
        return applianceModelRepository.findAll(pageable);
    }

}
