package com.acme.webserviceslinerepair.applianceModel.resource;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
@Getter
@Setter
public class UpdateApplianceModelResource {
    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    private String model;

    @NotNull
    @NotBlank
    private String urlToImage;

}
