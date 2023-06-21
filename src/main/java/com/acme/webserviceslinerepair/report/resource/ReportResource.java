package com.acme.webserviceslinerepair.report.resource;

import com.acme.webserviceslinerepair.appointment.resource.AppointmentResource;
import com.acme.webserviceslinerepair.technician.resource.TechnicianResource;
import lombok.*;

@Setter
@Getter
@With
@NoArgsConstructor
@AllArgsConstructor
public class ReportResource {
    private Long id;
    private String observation;
    private String diagnosis;
    private String repairDescription;
    private String date;
    private TechnicianResource technician;
    private AppointmentResource appointment;
}
