package com.acme.webserviceslinerepair.technician.api;


import com.acme.webserviceslinerepair.technician.domain.service.TechnicianService;
import com.acme.webserviceslinerepair.technician.mapping.TechnicianMapper;
import com.acme.webserviceslinerepair.technician.resource.CreateTechnicianResource;
import com.acme.webserviceslinerepair.technician.resource.TechnicianResource;
import com.acme.webserviceslinerepair.technician.resource.UpdateTechnicianResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SecurityRequirement(name = "acme")
@Tag(name = "Technician")
@RestController
@RequestMapping("api/v1/technicians")
@CrossOrigin(origins = "*" , maxAge = 3600)
public class TechniciansController {

    private final TechnicianService technicianService;

    private final TechnicianMapper mapper;

    public TechniciansController(TechnicianService technicianService, TechnicianMapper mapper) {
        this.technicianService = technicianService;
        this.mapper = mapper;
    }

    @Operation(summary = "Get All Technicians", description = "Get All Technicians")
    @GetMapping
    @PreAuthorize("hasRole('CLIENT') or hasRole('TECHNICIAN') or hasRole('ADMIN')")
    public List<TechnicianResource> getAll(){
        return mapper.toResource(technicianService.getAll());
    }

    @Operation(summary = "Get Technician by Complete Name", description = "Get Technician by Complete Name")
    @GetMapping("{names}/{lastNames}")
    @PreAuthorize("hasRole('TECHNICIAN') or hasRole('ADMIN')")
    public List<TechnicianResource> getTechnicianByNameAndLastName(@PathVariable String names, @PathVariable String lastNames){
        return mapper.toResource(technicianService.getByNameAndLastName(names, lastNames));
    }
    @Operation(summary = "Get Technician by Id", description = "Get Technician by Id")
    @GetMapping("{technicianId}")
    @PreAuthorize("hasRole('TECHNICIAN') or hasRole('ADMIN')")
    public TechnicianResource getTechnicianById(@PathVariable Long technicianId){
        return mapper.toResource(technicianService.getById(technicianId));
    }
    @Operation(summary = "Get Technician by Username", description = "Get Technician by Username")
    @GetMapping("username/{username}")
    @PreAuthorize("hasRole('TECHNICIAN') or hasRole('ADMIN')")
    public TechnicianResource getTechnicianByUsername(@PathVariable String username){
        return mapper.toResource(technicianService.getByUsername(username));
    }
    @Operation(summary = "Create New Technician", description = "Create New Technician")
    @PostMapping
    @PreAuthorize("hasRole('TECHNICIAN') or hasRole('ADMIN')")
    public TechnicianResource createTechnician(@RequestBody CreateTechnicianResource resource){
        return mapper.toResource(technicianService.create(mapper.toModel(resource)));
    }
    @Operation(summary = "Update Technician", description = "Update Technician")
    @PutMapping("{technicianId}")
    @PreAuthorize("hasRole('TECHNICIAN') or hasRole('ADMIN')")
    public TechnicianResource updateTechnician(@PathVariable Long technicianId, @RequestBody UpdateTechnicianResource model){
        return mapper.toResource(technicianService.update(technicianId, mapper.toModel(model)));
    }

    @Operation(summary = "Delete Technician", description = "Delete Technician")
    @DeleteMapping("{technicianId}")
    @PreAuthorize("hasRole('TECHNICIAN') or hasRole('ADMIN')")
    public ResponseEntity<?> deleteTechnician(@PathVariable Long technicianId){
        return technicianService.delete(technicianId);
    }

}

