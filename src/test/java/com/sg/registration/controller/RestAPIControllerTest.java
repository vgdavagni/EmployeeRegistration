package com.sg.registration.controller;

import com.sg.registration.RegistrationApp;
import com.sg.registration.model.Employee;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RegistrationApp.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestAPIControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    private String getRootUrl() {
        return "http://localhost:" + port;
    }

    @Test
    public void contextLoads() {

    }

    @Test
    public void testGetAllEmployees() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/employee",
                HttpMethod.GET, entity, String.class);

        Assert.assertNotNull(response.getBody());
    }

    @Test
    public void testGetEmployeeById() {
        Employee employee = new Employee();
        employee.setGender("Male");
        employee.setFirstName("admin");
        employee.setLastName("admin");

        ResponseEntity<Employee> postResponse = restTemplate.postForEntity(getRootUrl() + "/api/employee", employee, Employee.class);

        Employee employee1 = restTemplate.getForObject(getRootUrl() + "/api/employee/1", Employee.class);
        System.out.println(employee1.getFirstName());
        Assert.assertNotNull(employee1);
    }

    @Test
    public void testCreateEmployee() {
        Employee employee = new Employee();
        employee.setGender("Male");
        employee.setFirstName("admin");
        employee.setLastName("admin");

        ResponseEntity<Employee> postResponse = restTemplate.postForEntity(getRootUrl() + "/employee", employee, Employee.class);
        Assert.assertNotNull(postResponse);
        Assert.assertNotNull(postResponse.getBody());
    }

}
