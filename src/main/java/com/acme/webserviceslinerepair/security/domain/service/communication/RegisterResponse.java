package com.acme.webserviceslinerepair.security.domain.service.communication;
import com.acme.webserviceslinerepair.security.resource.UserResource;
import com.acme.webserviceslinerepair.shared.domain.service.communication.BaseResponse;

public class RegisterResponse extends BaseResponse<UserResource> {
    public RegisterResponse(String message) {
        super(message);
    }

    public RegisterResponse(UserResource resource) {
        super(resource);
    }
}