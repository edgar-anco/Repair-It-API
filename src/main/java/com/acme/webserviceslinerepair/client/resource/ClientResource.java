package com.acme.webserviceslinerepair.client.resource;

import lombok.*;


@Setter
@Getter
@With
@NoArgsConstructor
@AllArgsConstructor
public class ClientResource {
    private Long id;
    private String username;
    private String names;
    private String lastNames;
    private String address;
    private String email;
    private String password;
    private String cellPhoneNumber;
    private String planType;
}
