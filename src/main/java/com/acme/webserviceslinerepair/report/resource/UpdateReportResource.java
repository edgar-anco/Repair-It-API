package com.acme.webserviceslinerepair.report.resource;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Setter
@Getter
public class UpdateReportResource {
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
}
