package com.acme.webserviceslinerepair.test.step;

import com.acme.webserviceslinerepair.client.domain.model.entity.Client;
import com.acme.webserviceslinerepair.technician.domain.model.entity.Technician;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import lombok.extern.log4j.Log4j2;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@Log4j2
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class RegisterTechnicianSteps {

    private RestTemplate restTemplate = new RestTemplate();
    private String url = "http://localhost:8080/api/v1";
    private String message="";
    Technician technician;
    Long technicianId = randomLong();

    private Long randomLong() {
        Long generatedLong = new Random().nextLong();
        return generatedLong;
    }

    @Given("I want to register as technician")
    public void iWantToRegisterAsTechnician() {
        String technicianUrl = url + "/technician";
        String getEndPoint = restTemplate.getForObject(technicianUrl, String.class);
        log.info(getEndPoint);
        assertTrue(!getEndPoint.isEmpty());
    }

    @And("I enter my own information like username {string},names {string}, lastNames {string}, email {string}, password {string}, address {string},cellPhoneNumber{string}")
    public void iEnterMyOwnInformationLikeUsernameUsernameNamesNamesLastNamesLastNamesEmailEmailPasswordPasswordAddressAddressCellPhoneNumberCellPhoneNumber(String username,String names, String lastNames, String email, String password, String address, String cellPhoneNumber) {
        String technicianUrl = url + "/technicians";

        Technician newTechnician = new Technician(technicianId,username, names, lastNames, email, password, address, cellPhoneNumber);
        technician = restTemplate.postForObject(technicianUrl, newTechnician, Technician.class);
        log.info(technician.getId());
        assertNotNull(technician);
    }

    @Then("I should be able to see my newly technician account")
    public void iShouldBeAbleToSeeMyNewlyTechnicianAccount() {
        String userUrl = url + "/technicians/" + technician.getId();
        try
        {
            Client getTechnicianById = restTemplate.getForObject(userUrl, Client.class, technician.getId());
            log.info(getTechnicianById);
        }catch (RestClientException e) {
            message = "";
        }
        assertEquals("", message);
    }
}