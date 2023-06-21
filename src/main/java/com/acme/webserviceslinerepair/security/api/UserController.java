package com.acme.webserviceslinerepair.security.api;

import com.acme.webserviceslinerepair.security.domain.service.UserService;
import com.acme.webserviceslinerepair.security.domain.service.communication.AuthenticateRequest;
import com.acme.webserviceslinerepair.security.domain.service.communication.RegisterRequest;
import com.acme.webserviceslinerepair.security.mapping.UserMapper;
import com.acme.webserviceslinerepair.security.resource.UserResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@SecurityRequirement(name = "acme")
@Tag(name="Users")
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    private final UserMapper mapper;

    public UserController(UserService userService, UserMapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    @Operation(summary = "Login User", description = "Login User", tags = {"Users"})
    @PostMapping("/auth/sign-in")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody AuthenticateRequest request) {
        return userService.authenticate(request);
    }

    @Operation(summary = "Create User", description = "Create User", tags = {"Users"})
    @PostMapping("/auth/sign-up")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest request) {
        return userService.register(request);
    }

    @Operation(summary = "Get All Users", description = "Get All Users", tags = {"Users"})
    @GetMapping("/auth/get-all")
    public List<UserResource> getAllUsers(){
        return mapper.toResource(userService.getAll());
    }
    @GetMapping("/auth/verify-token-client")
    @PreAuthorize("hasRole('CLIENT')")
    public boolean verifyTokenUser(){
        return true;
    }
    @GetMapping("/auth/verify-token-technician")
    @PreAuthorize("hasRole('TECHNICIAN')")
    public boolean verifyTokenTechnician(){
        return true;
    }
}
