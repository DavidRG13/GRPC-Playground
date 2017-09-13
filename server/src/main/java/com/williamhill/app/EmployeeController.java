package com.williamhill.app;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    @GetMapping(value = "employee/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public EmployeeJson employeeById(@PathVariable("id") final int employeeId) {
        return new EmployeeJson("Pepe", "Pepe", employeeId, 1000.0);
    }

    @PostMapping(value = "employee", consumes = MediaType.APPLICATION_JSON_VALUE)
    public EmployeeJson addEmployee(@RequestBody final EmployeeJson employeeJson) {
        System.out.println("employeeJson = " + employeeJson);
        return employeeJson;
    }
}
