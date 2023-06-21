package com.acme.webserviceslinerepair.appointment.mapping;

import com.acme.webserviceslinerepair.appointment.domain.model.entity.Appointment;
import com.acme.webserviceslinerepair.appointment.resource.AppointmentResource;
import com.acme.webserviceslinerepair.appointment.resource.CreateAppointmentResource;
import com.acme.webserviceslinerepair.appointment.resource.UpdateAppointmentResource;
import com.acme.webserviceslinerepair.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class AppointmentMapper implements Serializable {
    @Autowired
    private EnhancedModelMapper mapper;

    //Object Mapping
    public AppointmentResource toResource(Appointment model){
        return mapper.map(model, AppointmentResource.class);
    }

    public List<AppointmentResource> toResource(List<Appointment>model){
        return mapper.mapList(model, AppointmentResource.class);
    }

    public Appointment toModel(CreateAppointmentResource resource){
        return mapper.map(resource, Appointment.class);
    }

    public Appointment toModel(UpdateAppointmentResource resource){
        return mapper.map(resource, Appointment.class);
    }

    public Page<AppointmentResource> modelListToPage(List<Appointment> modelList, Pageable pageable){
        return new PageImpl<>(mapper.mapList(modelList, AppointmentResource.class), pageable, modelList.size());
    }
}
