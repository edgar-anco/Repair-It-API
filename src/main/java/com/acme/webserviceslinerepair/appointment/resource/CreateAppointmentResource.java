package com.acme.webserviceslinerepair.appointment.resource;

import lombok.*;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Setter
@Getter
@With
@NoArgsConstructor
@AllArgsConstructor
public class CreateAppointmentResource {
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
