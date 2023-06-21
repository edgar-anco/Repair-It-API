package com.acme.webserviceslinerepair.appointment.resource;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UpdateAppointmentResource {
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

}
