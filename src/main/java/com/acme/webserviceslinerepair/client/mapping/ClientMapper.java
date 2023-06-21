package com.acme.webserviceslinerepair.client.mapping;

import com.acme.webserviceslinerepair.client.domain.model.entity.Client;
import com.acme.webserviceslinerepair.client.resource.ClientResource;
import com.acme.webserviceslinerepair.client.resource.CreateClientResource;
import com.acme.webserviceslinerepair.client.resource.UpdateClientResource;
import com.acme.webserviceslinerepair.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class ClientMapper implements Serializable {
    @Autowired
    private EnhancedModelMapper mapper;

    public ClientResource toResource(Client model){
        return mapper.map(model, ClientResource.class);
    }

    public java.util.List<ClientResource> toResource(java.util.List<Client> model){
        return mapper.mapList(model, ClientResource.class);
    }

    public Client toModel(CreateClientResource resource){
        return mapper.map(resource, Client.class);
    }

    public Client toModel(UpdateClientResource resource){
        return mapper.map(resource, Client.class);
    }

    public Page<ClientResource> modelListToPage(List<Client> modelList, Pageable pageable){
        return new PageImpl<>(mapper.mapList(modelList, ClientResource.class), pageable, modelList.size());
    }
}
