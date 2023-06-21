package com.acme.webserviceslinerepair.security.domain.service.communication;
import com.acme.webserviceslinerepair.security.resource.AuthenticateResource;
import com.acme.webserviceslinerepair.shared.domain.service.communication.BaseResponse;
public class AuthenticateResponse extends BaseResponse<AuthenticateResource> {
    public AuthenticateResponse(String message) {
        super(message);
    }

    public AuthenticateResponse(AuthenticateResource resource) {
        super(resource);
    }
}