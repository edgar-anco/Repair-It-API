package com.acme.webserviceslinerepair.technician.resource;

import lombok.*;

@Setter
@Getter
@With
@NoArgsConstructor
@AllArgsConstructor
public class TechnicianResource {
    private Long id;
    private String username;
    private String names;
    private String lastNames;
    private String address;
    private String email;
    private String password;
    private String cellPhoneNumber;
}
