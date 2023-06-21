package com.acme.webserviceslinerepair.security.domain.service;
import com.acme.webserviceslinerepair.security.domain.model.entity.Role;

import java.util.List;
public interface RoleService {
    void seed();

    List<Role> getAll();
}