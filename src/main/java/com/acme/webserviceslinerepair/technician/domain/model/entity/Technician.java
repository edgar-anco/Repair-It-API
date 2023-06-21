package com.acme.webserviceslinerepair.technician.domain.model.entity;

import com.acme.webserviceslinerepair.shared.domain.model.AuditModel;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@With
@Entity
@Table(name = "technicians", uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
public class Technician extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Size(max=50)
    private String username;

    @NotNull
    @NotBlank
    @Size(max=50)
    private String names;

    @NotNull
    @NotBlank
    @Size(max=50)
    private String lastNames;

    @NotNull
    @NotBlank
    @Size(max=50)
    private String address;

    @NotNull
    @NotBlank
    @Size(max=50)
    @Email
    private String email;

    @NotNull
    @NotBlank
    @Size(min=5,max=20)
    private String password;

    @NotNull
    @NotBlank
    @Size(max=9)
    private String cellPhoneNumber;

}
