package com.acme.webserviceslinerepair.appointment.resource;

import com.acme.webserviceslinerepair.applianceModel.resource.ApplianceModelResource;
import com.acme.webserviceslinerepair.client.resource.ClientResource;
import com.acme.webserviceslinerepair.technician.resource.TechnicianResource;
import lombok.*;


@Setter
@Getter
@With
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentResource {
    private Long id;
    private String dateReserve;
    private String dateAttention;
    private String hour;
    private Boolean status;
    private ClientResource client;
    private TechnicianResource technician;
    private ApplianceModelResource applianceModel;
}

