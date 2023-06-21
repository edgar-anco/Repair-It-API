package com.acme.webserviceslinerepair.applianceModel.resource;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@With
@NoArgsConstructor
@AllArgsConstructor
public class CreateApplianceModelResource{
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
