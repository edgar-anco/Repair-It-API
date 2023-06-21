package com.acme.webserviceslinerepair.technician.domain.persistence;

import com.acme.webserviceslinerepair.client.domain.model.entity.Client;
import com.acme.webserviceslinerepair.technician.domain.model.entity.Technician;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TechnicianRepository extends JpaRepository<Technician, Long> {

    Technician findByUsername(String username);
    List<Technician> findByNamesAndLastNamesContaining(String names, String lastNames);

}
