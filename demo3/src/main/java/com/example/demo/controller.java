package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@RestController
public class controller {

   @Autowired
   RestTemplate restTemplate;

    @GetMapping("/getall")
    public String getEmployees() {
        String uri = "http://dummy.restapiexample.com/api/v1/employees";
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);
        return result;
    }

    @DeleteMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable Integer id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Employee> entity = new HttpEntity<Employee>(headers);

        return restTemplate.exchange(
                "http://dummy.restapiexample.com/api/v1/delete/" + id, HttpMethod.DELETE, entity, String.class).getBody();
    }


    @PutMapping("/update/{id}")
    public String updateProduct(@PathVariable("id") String id, @RequestBody Employee employee) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept((Arrays.asList(MediaType.APPLICATION_JSON)));
        HttpEntity<Employee> entity = new HttpEntity<Employee>(employee, headers);

        return restTemplate.exchange("http://dummy.restapiexample.com/api/v1/update/" + id, HttpMethod.PUT, entity, String.class).getBody();
    }


    @PostMapping("/add")
    public String addEmployee(@RequestBody Employee employee) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Employee> entity = new HttpEntity<Employee>(employee, headers);
        return restTemplate.exchange("http://dummy.restapiexample.com/api/v1/create", HttpMethod.POST, entity, String.class).getBody();
    }

    @GetMapping("/get/{id}")
    public String findEmployeeById(@PathVariable Integer id) {
        String uri = "http://dummy.restapiexample.com/api/v1/employee/" + id;
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);
        return result;
    }


}
