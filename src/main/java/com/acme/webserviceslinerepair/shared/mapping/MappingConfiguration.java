package com.acme.webserviceslinerepair.shared.mapping;

import com.acme.webserviceslinerepair.applianceModel.mapping.ApplianceModelMapper;
import com.acme.webserviceslinerepair.appointment.mapping.AppointmentMapper;
import com.acme.webserviceslinerepair.client.mapping.ClientMapper;
import com.acme.webserviceslinerepair.report.mapping.ReportMapper;
import com.acme.webserviceslinerepair.security.mapping.RoleMapper;
import com.acme.webserviceslinerepair.security.mapping.UserMapper;
import com.acme.webserviceslinerepair.technician.mapping.TechnicianMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("linerepairModelMapperConfiguration")
public class MappingConfiguration {
    @Bean
    public EnhancedModelMapper modelMapper() {
        return new EnhancedModelMapper();
    }

    @Bean
    public UserMapper userMapper() {
        return new UserMapper();
    }

    @Bean
    public RoleMapper roleMapper() {
        return new RoleMapper();
    }

    @Bean
    public ClientMapper clientMapper() { return new ClientMapper(); }

    @Bean
    public TechnicianMapper technicianMapper() { return new TechnicianMapper(); }

    @Bean
    public  ReportMapper reportMapper() {return new ReportMapper(); }

    @Bean
    public ApplianceModelMapper applianceModelMapper() {return new ApplianceModelMapper();}

    @Bean
    public AppointmentMapper appointmentMapper() { return new AppointmentMapper(); }
}