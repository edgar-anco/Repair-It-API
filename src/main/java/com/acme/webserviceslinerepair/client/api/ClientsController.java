package com.acme.webserviceslinerepair.client.api;

import com.acme.webserviceslinerepair.client.domain.service.ClientService;
import com.acme.webserviceslinerepair.client.mapping.ClientMapper;
import com.acme.webserviceslinerepair.client.resource.ClientResource;
import com.acme.webserviceslinerepair.client.resource.CreateClientResource;
import com.acme.webserviceslinerepair.client.resource.UpdateClientResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@SecurityRequirement(name = "acme")
@Tag(name = "Client")
@RestController
@RequestMapping("api/v1/clients")
@CrossOrigin(origins = "*" , maxAge = 3600)
public class ClientsController {

    private final ClientService clientService;

    private final ClientMapper mapper;

    public ClientsController(ClientService clientService, ClientMapper mapper) {
        this.clientService = clientService;
        this.mapper = mapper;
    }


    @Operation(summary = "Get All Clients", description = "Get All Clients")
    @PreAuthorize("hasRole('CLIENT') or hasRole('TECHNICIAN') or hasRole('ADMIN')")
    @GetMapping
    public List<ClientResource> getAll(){
        return mapper.toResource(clientService.getAll());
    }

    @Operation(summary = "Get Client by Complete Name", description = "Get Client by Complete Name")
    @GetMapping("names/{names}/lastNames/{lastNames}")
    @PreAuthorize("hasRole('CLIENT') or hasRole('ADMIN')")
    public List<ClientResource> getClientByNameAndLastName(@PathVariable String names, @PathVariable String lastNames){
        return mapper.toResource(clientService.getByNameAndLastName(names, lastNames));
    }
    @Operation(summary = "Get Client by Username", description = "Get Client by Username")
    @GetMapping("username/{username}")
    @PreAuthorize("hasRole('CLIENT') or hasRole('ADMIN')")
    public ClientResource getClientByUsername(@PathVariable String username){
        return mapper.toResource(clientService.getByUsername(username));
    }
    @Operation(summary = "Get Client by Id", description = "Get Client by Id")
    @GetMapping("{clientId}")
    @PreAuthorize("hasRole('CLIENT') or hasRole('TECHNICIAN')or hasRole('ADMIN')")
    public ClientResource getClientById(@PathVariable Long clientId){
        return mapper.toResource(clientService.getById(clientId));
    }

    @Operation(summary = "Create New Client", description = "Create New Client")
    @PostMapping
    @PreAuthorize("hasRole('CLIENT') or hasRole('ADMIN')")
    public ClientResource createClient(@RequestBody CreateClientResource resource) {
        return mapper.toResource(clientService.create(mapper.toModel(resource)));
    }
    @Operation(summary = "Update Client", description = "Update Client")
    @PutMapping("{clientId}")
    @PreAuthorize("hasRole('CLIENT') or hasRole('ADMIN')")
    public ClientResource updateClient(@PathVariable Long clientId,
                                       @RequestBody UpdateClientResource resource){
        return mapper.toResource(clientService.update(clientId, mapper.toModel(resource)));
    }

    @Operation(summary = "Delete Client", description = "Delete Client")
    @DeleteMapping("{clientId}")
    @PreAuthorize("hasRole('CLIENT') or hasRole('ADMIN')")
    public ResponseEntity<?> deleteClient(@PathVariable Long clientId){
        return clientService.delete(clientId);
    }

}
