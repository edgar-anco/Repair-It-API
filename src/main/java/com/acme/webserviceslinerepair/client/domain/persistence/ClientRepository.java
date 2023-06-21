package com.acme.webserviceslinerepair.client.domain.persistence;

import com.acme.webserviceslinerepair.client.domain.model.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    Client findByUsername(String username);
    List<Client> findByNamesAndLastNamesContaining(String names, String lastNames);

}
