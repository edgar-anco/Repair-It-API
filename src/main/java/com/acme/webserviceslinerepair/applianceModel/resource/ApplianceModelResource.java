package com.acme.webserviceslinerepair.applianceModel.resource;

import com.acme.webserviceslinerepair.client.resource.ClientResource;
import lombok.*;

@Setter
@Getter
@With
@NoArgsConstructor
@AllArgsConstructor
public class ApplianceModelResource {
    private Long id;
    private String name;
    private String model;
    private String urlToImage;
    private ClientResource client;
}

