package com.acme.webserviceslinerepair.applianceModel.api;

import com.acme.webserviceslinerepair.applianceModel.domain.service.ApplianceModelService;
import com.acme.webserviceslinerepair.applianceModel.mapping.ApplianceModelMapper;
import com.acme.webserviceslinerepair.applianceModel.resource.ApplianceModelResource;
import com.acme.webserviceslinerepair.applianceModel.resource.CreateApplianceModelResource;
import com.acme.webserviceslinerepair.applianceModel.resource.UpdateApplianceModelResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SecurityRequirement(name = "acme")
@Tag(name = "ApplianceModel")
@RestController
@RequestMapping("api/v1/applianceModels")
@CrossOrigin(origins = "*" , maxAge = 3600)
public class ApplianceModelsController {

    private final ApplianceModelService applianceModelService;


    private final ApplianceModelMapper mapper;
    public ApplianceModelsController(ApplianceModelService applianceModelService, ApplianceModelMapper mapper){
        this.applianceModelService = applianceModelService;
        this.mapper = mapper;
    }
    @Operation(summary = "Get All ApplianceModels", description = "Get All ApplianceModels")
    @PreAuthorize("hasRole('CLIENT') or hasRole('TECHNICIAN') or hasRole('ADMIN')")
    @GetMapping
    public List<ApplianceModelResource> getAll(){
        return mapper.toResource(applianceModelService.getAll());
    }

    @Operation(summary = "Get ApplianceModel by Id", description = "Get ApplianceModel by Id")
    @GetMapping("{applianceModelId}")
    @PreAuthorize("hasRole('CLIENT') or hasRole('ADMIN')")
    public ApplianceModelResource getApplianceModelById(@PathVariable Long applianceModelId){
        return mapper.toResource(applianceModelService.getById(applianceModelId));
    }
    @Operation(summary = "Get ApplianceModels by ClientId", description = "Get All ApplianceModels by ClientId")
    @GetMapping("{clientId}/applianceModels")
    @PreAuthorize("hasRole('CLIENT') or hasRole('ADMIN')")
    public List<ApplianceModelResource> getApplianceModelsByClientId(@PathVariable Long clientId){
        return mapper.toResource(applianceModelService.getByClientId(clientId));
    }

    @Operation(summary = "Create New ApplianceModel", description = "Create New ApplianceModel")
    @PostMapping("{clientId}")
    @PreAuthorize("hasRole('CLIENT') or hasRole('ADMIN')")
    public ApplianceModelResource createApplianceModel(@RequestBody CreateApplianceModelResource resource, @PathVariable Long clientId){
        return mapper.toResource(applianceModelService.create(mapper.toModel(resource), clientId));
    }
    @Operation(summary = "Update ApplianceModel", description = "Update ApplianceModel")
    @PutMapping("{applianceModelId}")
    @PreAuthorize("hasRole('CLIENT') or hasRole('ADMIN')")
    public ApplianceModelResource updateApplianceModel(@PathVariable Long applianceModelId, @RequestBody UpdateApplianceModelResource model){
        return mapper.toResource(applianceModelService.update(applianceModelId, mapper.toModel(model)));
    }
    @Operation(summary = "Delete ApplianceModel", description = "Delete ApplianceModel")
    @DeleteMapping("{applianceModelId}")
    @PreAuthorize("hasRole('CLIENT') or hasRole('ADMIN')")
    public ResponseEntity<?> deleteApplianceModel(@PathVariable Long applianceModelId){
        return applianceModelService.delete(applianceModelId);
    }

}
