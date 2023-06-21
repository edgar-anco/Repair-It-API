package com.acme.webserviceslinerepair.applianceModel.domain.model.entity;

import com.acme.webserviceslinerepair.client.domain.model.entity.Client;
import com.acme.webserviceslinerepair.shared.domain.model.AuditModel;
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
@Table(name = "appliance_models")
public class ApplianceModel extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Size(max=300)
    private String name;

    @NotNull
    @NotBlank
    @Size(max=300)
    private String model;

    @NotNull
    @NotBlank
    @Size(max=1000)
    private String urlToImage;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "client_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Client client;
}
