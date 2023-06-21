package com.acme.webserviceslinerepair.report.domain.model.entity;

import com.acme.webserviceslinerepair.appointment.domain.model.entity.Appointment;
import com.acme.webserviceslinerepair.shared.domain.model.AuditModel;
import com.acme.webserviceslinerepair.technician.domain.model.entity.Technician;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@With
@Entity
@Table(name = "reports")
public class Report extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Size(max=600)
    private String observation;

    @NotNull
    @NotBlank
    @Size(max=700)
    private String diagnosis;

    @NotNull
    @NotBlank
    @Size(max=700)
    private String repairDescription;

    @NotNull
    @NotBlank
    @Size(max=40)
    private String date;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "technician_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Technician technician;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "appointment_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Appointment appointment;

}

