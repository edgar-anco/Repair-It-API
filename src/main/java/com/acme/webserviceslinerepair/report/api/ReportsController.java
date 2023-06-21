package com.acme.webserviceslinerepair.report.api;

import com.acme.webserviceslinerepair.report.domain.service.ReportService;
import com.acme.webserviceslinerepair.report.mapping.ReportMapper;
import com.acme.webserviceslinerepair.report.resource.CreateReportResource;
import com.acme.webserviceslinerepair.report.resource.ReportResource;
import com.acme.webserviceslinerepair.report.resource.UpdateReportResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SecurityRequirement(name = "acme")
@Tag(name = "Report")
@RestController
@RequestMapping("api/v1/reports")
@CrossOrigin(origins = "*" , maxAge = 3600)
public class ReportsController {

    private final ReportService reportService;
    private final ReportMapper mapper;

    public ReportsController(ReportService reportService, ReportMapper mapper) {
        this.reportService = reportService;
        this.mapper = mapper;
    }

    @Operation(summary = "Get All Reports", description = "Get All Reports")
    @GetMapping
    @PreAuthorize("hasRole('CLIENT') or hasRole('TECHNICIAN') or hasRole('ADMIN')")
    public List<ReportResource> getAll(){
        return mapper.toResource(reportService.getAll());
    }
    @Operation(summary = "Get Report by Id", description = "Get Report by Id")
    @GetMapping("{reportId}")
    @PreAuthorize("hasRole('CLIENT') or hasRole('TECHNICIAN') or hasRole('ADMIN')")
    public ReportResource getReportById(@PathVariable Long reportId){
        return mapper.toResource(reportService.getById(reportId));
    }
    @Operation(summary = "Get Report by TechnicianId", description = "Get All Report by TechnicianId")
    @GetMapping("{technicianId}/technicians/reports")
    @PreAuthorize("hasRole('CLIENT') or hasRole('TECHNICIAN') or hasRole('ADMIN')")
    public List<ReportResource> getReportsByTechnicianId(@PathVariable Long technicianId){
        return mapper.toResource(reportService.getByTechnicianId(technicianId));
    }

    @Operation(summary = "Get Report by AppointmentId", description = "Get All Report by AppointmentId")
    @GetMapping("{appointmentId}/appointments/reports")
    @PreAuthorize("hasRole('CLIENT') or hasRole('TECHNICIAN') or hasRole('ADMIN')")
    public List<ReportResource> getReportsByAppointmentId(@PathVariable Long appointmentId){
        return mapper.toResource(reportService.getByAppointmentId(appointmentId));
    }

    @Operation(summary = "Create New Report", description = "Create New Report")
    @PostMapping("{technicianId}/{appointmentId}")
    @PreAuthorize("hasRole('CLIENT') or hasRole('TECHNICIAN') or hasRole('ADMIN')")
    public ReportResource createReport(@RequestBody CreateReportResource resource, @PathVariable Long technicianId, @PathVariable Long appointmentId){
        return mapper.toResource(reportService.create(mapper.toModel(resource), technicianId,appointmentId));
    }

    @Operation(summary = "Update Report", description = "Update Report")
    @PutMapping("{reportId}")
    @PreAuthorize("hasRole('CLIENT') or hasRole('TECHNICIAN') or hasRole('ADMIN')")
    public ReportResource updateReport(@PathVariable Long reportId, @RequestBody UpdateReportResource model){
        return mapper.toResource(reportService.update(reportId, mapper.toModel(model)));
    }

    @Operation(summary = "Delete Report", description = "Delete Report")
    @DeleteMapping("{reportId}")
    @PreAuthorize("hasRole('CLIENT') or hasRole('TECHNICIAN') or hasRole('ADMIN')")
    public ResponseEntity<?> deleteReport(@PathVariable Long reportId){
        return reportService.delete(reportId);
    }

}
