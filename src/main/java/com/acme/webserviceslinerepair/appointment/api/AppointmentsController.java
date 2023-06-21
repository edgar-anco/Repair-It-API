package com.acme.webserviceslinerepair.appointment.api;

import com.acme.webserviceslinerepair.appointment.domain.service.AppointmentService;
import com.acme.webserviceslinerepair.appointment.mapping.AppointmentMapper;
import com.acme.webserviceslinerepair.appointment.resource.AppointmentResource;
import com.acme.webserviceslinerepair.appointment.resource.CreateAppointmentResource;
import com.acme.webserviceslinerepair.appointment.resource.UpdateAppointmentResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SecurityRequirement(name = "acme")
@Tag(name = "Appointment")
@RestController
@RequestMapping("api/v1/appointments")
@CrossOrigin(origins = "*" , maxAge = 3600)
public class AppointmentsController {

    private final AppointmentService appointmentService;
    private final AppointmentMapper mapper;
    public AppointmentsController(AppointmentService appointmentService, AppointmentMapper mapper){
        this.appointmentService = appointmentService;
        this.mapper = mapper;
    }
    @Operation(summary = "Get All Appointments", description = "Get All Appointments")
    @GetMapping
    @PreAuthorize("hasRole('CLIENT') or hasRole('TECHNICIAN') or hasRole('ADMIN')")
    public List<AppointmentResource> getAll(){
        return mapper.toResource(appointmentService.getAll());
    }

    @Operation(summary = "Get Appointment by Id", description = "Get Appointment by Id")
    @GetMapping("{appointmentId}")
    @PreAuthorize("hasRole('CLIENT') or hasRole('TECHNICIAN') or hasRole('ADMIN')")
    public AppointmentResource getAppointmentById(@PathVariable Long appointmentId){
        return mapper.toResource(appointmentService.getById(appointmentId));
    }
    @Operation(summary = "Get Appointments by ClientId", description = "Get All Appointments by ClientId")
    @GetMapping("{clientId}/clients/appointments")
    @PreAuthorize("hasRole('CLIENT') or hasRole('TECHNICIAN') or hasRole('ADMIN')")
    public List<AppointmentResource> getAppointmentsByClientId(@PathVariable Long clientId){
        return mapper.toResource(appointmentService.getByClientId(clientId));
    }
    @Operation(summary = "Get Appointments by TechnicianId", description = "Get All Appointments by TechnicianId")
    @GetMapping("{technicianId}/technicians/appointments")
    @PreAuthorize("hasRole('CLIENT') or hasRole('TECHNICIAN') or hasRole('ADMIN')")
    public List<AppointmentResource> getAppointmentsByTechnicianId(@PathVariable Long technicianId){
        return mapper.toResource(appointmentService.getByTechnicianId(technicianId));
    }

    @Operation(summary = "Create New Appointment", description = "Create New Appointment")
    @PostMapping("{clientId}/{technicianId}/{applianceModelId}")
    @PreAuthorize("hasRole('CLIENT')or hasRole('TECHNICIAN') or hasRole('ADMIN')")
    public AppointmentResource createAppointment(@RequestBody CreateAppointmentResource model, @PathVariable Long clientId,@PathVariable Long technicianId, @PathVariable Long applianceModelId){
        return mapper.toResource(appointmentService.create(mapper.toModel(model), clientId, technicianId, applianceModelId));
    }

    @Operation(summary = "Update Appointment", description = "Update Appointment")
    @PutMapping("{appointmentId}")
    @PreAuthorize("hasRole('CLIENT')or hasRole('TECHNICIAN') or hasRole('ADMIN')")
    public AppointmentResource updateAppointment(@PathVariable Long appointmentId, @RequestBody UpdateAppointmentResource model){
        return mapper.toResource(appointmentService.update(appointmentId, mapper.toModel(model)));
    }

    @Operation(summary = "Delete Appointment", description = "Delete Appointment")
    @DeleteMapping("{appointmentId}")
    @PreAuthorize("hasRole('CLIENT') or hasRole('TECHNICIAN')  or hasRole('ADMIN')")
    public ResponseEntity<?> deleteAppointment(@PathVariable Long appointmentId){
        return appointmentService.delete(appointmentId);
    }

}
