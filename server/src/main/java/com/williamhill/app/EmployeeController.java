package com.williamhill.app;

import com.williamhill.protobuf.EmployeeService.Employee;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    @GetMapping(value = "employee/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public EmployeeJson employeeByIdJson(@PathVariable("id") final int employeeId) {
        return new EmployeeJson("Pepe", "Pepe", employeeId, 1000.0);
    }

    @PostMapping(value = "employee", consumes = MediaType.APPLICATION_JSON_VALUE)
    public EmployeeJson addEmployee(@RequestBody final EmployeeJson employeeJson) {
        System.out.println("employeeJson = " + employeeJson);
        return employeeJson;
    }

    @GetMapping(value = "employee/{id}", produces = "application/x-protobuf")
    public Employee employeeById(@PathVariable("id") final int employeeId) {
        return Employee.newBuilder().setFirstname("Pepe").setLastname("Pepe").setId(employeeId).setSalary(1000.0).build();
    }

    @PostMapping(value = "employee", consumes = "application/x-protobuf")
    public Employee addEmployee(@RequestBody final Employee employee) {
        System.out.println("employeeJson = " + employee);
        return employee;
    }
}
