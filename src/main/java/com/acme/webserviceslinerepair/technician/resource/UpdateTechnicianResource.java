package com.acme.webserviceslinerepair.technician.resource;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
@Getter
@Setter
public class UpdateTechnicianResource {
    @NotNull
    @NotBlank
    private String username;

    @NotNull
    @NotBlank
    private String names;

    @NotNull
    @NotBlank
    private String lastNames;

    @NotNull
    @NotBlank
    private String address;

    @NotNull
    @NotBlank
    @Size(max=50)
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

