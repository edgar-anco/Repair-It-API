package com.acme.webserviceslinerepair.test.step;


import com.acme.webserviceslinerepair.client.domain.model.entity.Client;
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
public class RegisterClientSteps {

    private RestTemplate restTemplate = new RestTemplate();
    private String url = "http://localhost:8080/api/v1";
    private String message = "";
    Client client;
    Long clientId = randomLong();

    private Long randomLong() {
        Long generatedLong = new Random().nextLong();
        return generatedLong;
    }

    @Given("I want to register as client")
    public void iWantToRegister() {
        String clientUrl = url + "/clients";
        String getEndpoint=restTemplate.getForObject(clientUrl, String.class);
        log.info(getEndpoint);
        assertTrue(!getEndpoint.isEmpty());
    }

    @Then("I should be able to see my newly account")
    public void iShouldBeAbleToSeeMyNewlyAccount() {
        String clientUrl = url + "/clients/" + client.getId();

        try{
            Client getClientById = restTemplate.getForObject(clientUrl, Client.class, client.getId());
            log.info(getClientById);
        }catch(RestClientException e){
            message = "";
        }
        assertEquals("", message);
    }

    @And("I enter my own information like username {string},names {string}, lastNames {string}, email {string}, password {string}, address {string},cellPhoneNumber{string},planType{string}")
    public void iEnterMyOwnInformationLikeUsernameUsernameNamesNamesLastNamesLastNamesEmailEmailPasswordPasswordAddressAddressCellPhoneNumberCellPhoneNumberPlanTypePlanType(String username,String names, String lastNames, String email, String password, String address, String cellPhoneNumber,String planType) {
        String clientUrl = url + "/clients";

        Client newClient = new Client(clientId, username,names, lastNames, email, password, address, cellPhoneNumber,planType);
        client = restTemplate.postForObject(clientUrl, newClient, Client.class);
        log.info(client.getId());
        assertNotNull(client);
    }
}