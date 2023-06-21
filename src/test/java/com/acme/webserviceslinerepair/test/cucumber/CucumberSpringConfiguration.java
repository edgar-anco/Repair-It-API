package com.acme.webserviceslinerepair.test.cucumber;

import com.acme.webserviceslinerepair.WebServicesLinerepairApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@CucumberContextConfiguration
@SpringBootTest(
        classes = WebServicesLinerepairApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(
        classes = WebServicesLinerepairApplication.class,
        loader = SpringBootContextLoader.class)
public class CucumberSpringConfiguration {
}