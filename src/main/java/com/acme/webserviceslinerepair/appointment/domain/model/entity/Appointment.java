package com.acme.webserviceslinerepair.appointment.domain.model.entity;

import com.acme.webserviceslinerepair.applianceModel.domain.model.entity.ApplianceModel;
import com.acme.webserviceslinerepair.client.domain.model.entity.Client;
import com.acme.webserviceslinerepair.shared.domain.model.AuditModel;
import com.acme.webserviceslinerepair.technician.domain.model.entity.Technician;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@With
@Entity
@Table(name = "appointments")
public class Appointment extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    private String dateReserve;

    @NotNull
    @NotBlank
    private String dateAttention;

    @NotNull
    @NotBlank
    private String hour;

    @NotNull
    @AssertTrue
    private Boolean status;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "client_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "technician_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Technician technician;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "appliance_model_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private ApplianceModel applianceModel;

}
